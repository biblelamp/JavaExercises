import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Date;

/**
A basic File Manager.  Requires 1.6+ for the Desktop & SwingWorker
classes, amongst other minor things.

Includes support classes FileTableModel & FileTreeCellRenderer.

Neither Delete nor New has been implemented.  See commented code for
hints on how to proceed for that functionality.

@TODO Still throws occasional AIOOBEs and NPEs, so some update on
the EDT must have been missed.

@author Andrew Thompson
@version 2011-05-29
@see <a href="https://stackoverflow.com/questions/6147965/change-icon-of-the-first-node-of-jtree/6153182#6153182">Change icon of the first node of JTree</a>
*/
class FileManager {

    /** Used to open/edit/print files. */
    private Desktop desktop;
    /** Provides nice icons and names for files. */
    private FileSystemView fileSystemView;
    /** Main GUI container */
    private JPanel gui;

    /** File-system tree. Built Lazily */
    private JTree tree;
    /** Directory listing */
    private JTable table;
    private JProgressBar progressBar;

    /* File controls. */
    private JButton openFile;
    private JButton printFile;
    private JButton editFile;
    private JButton deleteFile;
    private JButton newFile;
    /* File details. */
    private JLabel fileName;
    private JTextField path;
    private JLabel date;
    private JLabel size;
    private JCheckBox readable;
    private JCheckBox writable;
    private JCheckBox executable;
    private JCheckBox isDirectory;
    private JCheckBox isFile;

    /** Table model for File[]. */
    private FileTableModel fileTableModel;
    private ListSelectionListener listSelectionListener;
    private boolean cellSizesSet = false;

    /** currently selected File. */
    private File currentFile;

    private boolean safeMode = true;

    public Container getGui() {
        if (gui==null) {
            gui = new JPanel(new BorderLayout(3,3));
            gui.setBorder(new EmptyBorder(5,5,5,5));

            fileSystemView = FileSystemView.getFileSystemView();
            desktop = Desktop.getDesktop();

            DefaultMutableTreeNode root = new DefaultMutableTreeNode();

            File[] roots = fileSystemView.getRoots();
            for (File fileSystemRoot : roots) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileSystemRoot);
                root.add( node );
                File[] files = fileSystemView.getFiles(fileSystemRoot, true);
                for (File file : files) {
                    node.add(new DefaultMutableTreeNode(file));
                }
            }

            TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {
                public void valueChanged(TreeSelectionEvent tse){
                    DefaultMutableTreeNode node =
                        (DefaultMutableTreeNode)tse.getPath().getLastPathComponent();
                    addChildren(node);
                    setFileDetails((File)node.getUserObject());
                }
            };

            tree = new JTree(root);
            tree.setRootVisible(false);
            tree.addTreeSelectionListener(treeSelectionListener);
            tree.setCellRenderer(new FileTreeCellRenderer());
            tree.expandRow(0);
            JScrollPane treeScroll = new JScrollPane(tree);

            // as per trashgod tip
            tree.setVisibleRowCount(15);

            Dimension preferredSize = treeScroll.getPreferredSize();
            Dimension widePreferred = new Dimension(
                250,
                (int)preferredSize.getHeight());
            treeScroll.setPreferredSize( widePreferred );

            JPanel detailView = new JPanel(new BorderLayout(3,3));
            fileTableModel = new FileTableModel();

            table = new JTable(fileTableModel);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setAutoCreateRowSorter(true);
            table.setShowVerticalLines(false);
            // arbitrary size adjustment to better account for icons
            table.setRowHeight( (int)(table.getRowHeight()*1.3) );

            listSelectionListener = new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent lse) {
                    int row = table.getSelectionModel().getLeadSelectionIndex();
                    setFileDetails( ((FileTableModel)table.getModel()).getFile(row) );
                }
            };
            table.getSelectionModel().addListSelectionListener(listSelectionListener);
            JScrollPane tableScroll = new JScrollPane(table);
            Dimension d = tableScroll.getPreferredSize();
            tableScroll.setPreferredSize(new Dimension((int)d.getWidth(), (int)d.getHeight()/2));
            detailView.add(tableScroll, BorderLayout.CENTER);

            JPanel fileMainDetails = new JPanel(new BorderLayout(4,2));
            fileMainDetails.setBorder(new EmptyBorder(0,6,0,6));

            JPanel fileDetailsLabels = new JPanel(new GridLayout(0,1,2,2));
            fileMainDetails.add(fileDetailsLabels, BorderLayout.WEST);

            JPanel fileDetailsValues = new JPanel(new GridLayout(0,1,2,2));
            fileMainDetails.add(fileDetailsValues, BorderLayout.CENTER);

            fileDetailsLabels.add(new JLabel("File", JLabel.TRAILING));
            fileName = new JLabel();
            fileDetailsValues.add(fileName);
            fileDetailsLabels.add(new JLabel("Path/name", JLabel.TRAILING));
            path = new JTextField(5);
            path.setEditable(false);
            fileDetailsValues.add(path);
            fileDetailsLabels.add(new JLabel("Last Modified", JLabel.TRAILING));
            date = new JLabel();
            fileDetailsValues.add(date);
            fileDetailsLabels.add(new JLabel("File size", JLabel.TRAILING));
            size = new JLabel();
            fileDetailsValues.add(size);

            int count = fileDetailsLabels.getComponentCount();
            for (int ii=0; ii<count; ii++) {
                fileDetailsLabels.getComponent(ii).setEnabled(false);
            }

            JToolBar toolBar = new JToolBar();

            openFile = new JButton("Open");
            openFile.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    try {
                        desktop.open(currentFile);
                    } catch(Throwable t) {
                        JOptionPane.showMessageDialog(
                            gui,
                            t.toString(),
                            t.getMessage(),
                            JOptionPane.ERROR_MESSAGE
                            );
                    }
                    gui.repaint();
                }
            });
            toolBar.add(openFile);

            editFile = new JButton("Edit");
            editFile.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    try {
                        desktop.edit(currentFile);
                    } catch(Throwable t) {
                        showThrowable(t);
                    }
                }
            });
            toolBar.add(editFile);

            printFile = new JButton("Print");
            printFile.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    try {
                        desktop.print(currentFile);
                    } catch(Throwable t) {
                        showThrowable(t);
                    }
                }
            });
            toolBar.add(printFile);

            // Check the actions are supported on this platform!
            openFile.setEnabled(desktop.isSupported(Desktop.Action.OPEN));
            editFile.setEnabled(desktop.isSupported(Desktop.Action.EDIT));
            printFile.setEnabled(desktop.isSupported(Desktop.Action.PRINT));

            toolBar.addSeparator();

            deleteFile = new JButton("Delete");
            deleteFile.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    if (currentFile==null) {
                        JOptionPane.showMessageDialog(
                            gui,
                            "No file selected for deletion.",
                            "Select File",
                            JOptionPane.ERROR_MESSAGE
                            );
                        return;
                    }
                    if (safeMode) {
                        safeMessage();
                    } else {
                        int result = JOptionPane.showConfirmDialog(
                            gui,
                            "Are you sure you want to delete this file?",
                            "Delete File",
                            JOptionPane.ERROR_MESSAGE
                            );
                        if (result==JOptionPane.OK_OPTION) {
                            try {
                                boolean deleted = currentFile.delete();
                                if (deleted) {
                                    // delete the node..
                                } else {
                                    JOptionPane.showMessageDialog(
                                        gui,
                                        "The file '" +
                                        currentFile +
                                        "' could not be deleted.",
                                        "Delete Failed",
                                        JOptionPane.ERROR_MESSAGE
                                        );
                                }
                            } catch(Throwable t) {
                                showThrowable(t);
                            }
                        }
                    }
                    gui.repaint();
                }
            });
            toolBar.add(deleteFile);

            final JPanel newPanel = new JPanel(new GridLayout(0,1,3,3));
            final JRadioButton newTypeFile = new JRadioButton("New File");
            JRadioButton newTypeDirectory = new JRadioButton("New Directory");
            ButtonGroup bg = new ButtonGroup();
            bg.add(newTypeFile);
            bg.add(newTypeDirectory);
            final JTextField name = new JTextField(15);
            newPanel.add( name );
            newPanel.add( newTypeFile );
            newPanel.add( newTypeDirectory );

            newFile = new JButton("New");
            newFile.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    if (currentFile==null) {
                        JOptionPane.showMessageDialog(
                            gui,
                            "No location selected for new file.",
                            "Select Location",
                            JOptionPane.ERROR_MESSAGE
                            );
                        return;
                    }

                    if (safeMode) {
                        safeMessage();
                    } else {
                        int result = JOptionPane.showConfirmDialog(gui, newPanel);
                        if (result==JOptionPane.OK_OPTION) {
                            try {
                                boolean created;
                                File file = new File( currentFile, name.getText() );
                                if (newTypeFile.isSelected()) {
                                    created = file.createNewFile();
                                } else {
                                    created = file.mkdir();
                                }
                                if (created) {
                                    // add the new node..
                                } else {
                                    JOptionPane.showMessageDialog(
                                        gui,
                                        "The file '" +
                                        file +
                                        "' could not be created.",
                                        "Create Failed",
                                        JOptionPane.ERROR_MESSAGE
                                        );
                                }
                            } catch(Throwable t) {
                                showThrowable(t);
                            }
                        }
                    }
                    gui.repaint();
                }
            });
            toolBar.add(newFile);

            JPanel flags = new JPanel(new GridLayout(1,0,4,4));
            readable = new JCheckBox("Read");
            readable.setEnabled(false);
            flags.add(readable);

            writable = new JCheckBox("Write");
            writable.setEnabled(false);
            flags.add(writable);

            executable = new JCheckBox("Execute");
            executable.setEnabled(false);
            flags.add(executable);

            isDirectory = new JCheckBox("Directory");
            isDirectory.setEnabled(false);
            flags.add(isDirectory);

            isFile = new JCheckBox("File");
            isFile.setEnabled(false);
            flags.add(isFile);

            //flags.setBorder(new TitledBorder("Flags"));

            JPanel fileView = new JPanel(new BorderLayout(3,3));

            fileView.add(toolBar,BorderLayout.NORTH);
            fileView.add(fileMainDetails,BorderLayout.CENTER);
            fileView.add(flags,BorderLayout.SOUTH);

            detailView.add(fileView, BorderLayout.SOUTH);

            JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                treeScroll,
                detailView);
            gui.add(splitPane, BorderLayout.CENTER);

            JPanel simpleOutput = new JPanel(new BorderLayout(3,3));
            progressBar = new JProgressBar();
            simpleOutput.add(progressBar, BorderLayout.EAST);
            progressBar.setVisible(false);

            gui.add(simpleOutput, BorderLayout.SOUTH);
        }
        return gui;
    }

    private void safeMessage() {
        String safe =
            "This is a test program!  " +
            "Delete/New have not been enabled.  " +
            "Recompile with safeMode=false; to enable.";
        JOptionPane.showMessageDialog( gui, safe, "Not Enabled", JOptionPane.ERROR_MESSAGE );
    }

    private void showThrowable(Throwable t) {
        JOptionPane.showMessageDialog(
            gui,
            t.toString(),
            t.getMessage(),
            JOptionPane.ERROR_MESSAGE
            );
        gui.repaint();
    }

    /** Update the table on the EDT */
    private void setTableDate(final File[] files) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                table.getSelectionModel().removeListSelectionListener(listSelectionListener);
                fileTableModel.setFiles(files);
                table.getSelectionModel().addListSelectionListener(listSelectionListener);
                int max = table.getRowCount();
                if (!cellSizesSet) {

                    setColumnWidth(0,-1);
                    setColumnWidth(3,60);
                    table.getColumnModel().getColumn(3).setMaxWidth(120);
                    setColumnWidth(4,-1);
                    setColumnWidth(5,-1);
                    setColumnWidth(6,-1);
                    setColumnWidth(7,-1);

                    cellSizesSet = true;
                }
            }
        });
    }

    private void setColumnWidth(int column, int width) {
        TableColumn tableColumn = table.getColumnModel().getColumn(column);
        if (width<0) {
            // use the preferred width of the header..
            JLabel label = new JLabel( (String)tableColumn.getHeaderValue() );
            Dimension preferred = label.getPreferredSize();
            width = (int)preferred.getWidth()+10;
        }
        tableColumn.setPreferredWidth(width);
        tableColumn.setMaxWidth(width);
        tableColumn.setMinWidth(width);
    }

    /** Add the files that are contained within the directory of this node. */
    private void addChildren(final DefaultMutableTreeNode node) {
        SwingWorker worker = new SwingWorker() {
            @Override
            public String doInBackground() {
                tree.setEnabled(false);
                progressBar.setVisible(true);
                progressBar.setIndeterminate(true);
                File file = (File)node.getUserObject();
                if ( file.isDirectory() ) {
                    File[] files = fileSystemView.getFiles(file, true);
                    if (node.isLeaf()) {
                        for (File child : files) {
                            node.add( new DefaultMutableTreeNode(child) );
                        }
                    }
                    setTableDate(files);
                }
                progressBar.setIndeterminate(false);
                progressBar.setVisible(false);
                tree.setEnabled(true);
                return "done";
            }
        };
        worker.execute();
    }

    /** Update the File details view with the details of this File. */
    private void setFileDetails(File file) {
        currentFile = file;
        fileName.setIcon(fileSystemView.getSystemIcon(file));
        fileName.setText(fileSystemView.getSystemDisplayName(file));
        path.setText(file.getPath());
        date.setText(new Date(file.lastModified()).toString());
        size.setText(file.length() + " bytes");
        readable.setSelected(file.canRead());
        writable.setSelected(file.canWrite());
        executable.setSelected(file.canExecute());
        isDirectory.setSelected(file.isDirectory());

        isFile.setSelected(file.isFile());

        gui.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Significantly improves the look of the output in
                    // terms of the file names returned by FileSystemView!
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch(Exception weTried) {
                }
                JFrame f = new JFrame("File Manager");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                FileManager fileManager = new FileManager();
                f.setContentPane(fileManager.getGui());

                f.pack();
                f.setLocationRelativeTo(null);
                f.setLocationByPlatform(true);
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        });
    }
}

/** A TableModel to hold File[]. */
class FileTableModel extends AbstractTableModel {

    private File[] files;
    private FileSystemView fileSystemView = FileSystemView.getFileSystemView();
    private String[] columns = {
        "Icon",
        "File",
        "Path/name",
        "Size",
        "Last Modified",
        "R",
        "W",
        "E"
    };

    FileTableModel() {
        this(new File[0]);
    }

    FileTableModel(File[] files) {
        this.files = files;
    }

    public Object getValueAt(int row, int column) {
        File file = files[row];
        switch (column) {
            case 0:
                return fileSystemView.getSystemIcon(file);
            case 1:
                return fileSystemView.getSystemDisplayName(file);
            case 2:
                return file.getPath();
            case 3:
                return file.length();
            case 4:
                return file.lastModified();
            case 5:
                return file.canRead();
            case 6:
                return file.canWrite();
            case 7:
                return file.canExecute();
            default:
                System.err.println("Logic Error");
        }
        return "";
    }

    public int getColumnCount() {
        return columns.length;
    }

    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0:
                return ImageIcon.class;
            case 3:
                return Long.class;
            case 4:
                return Date.class;
            case 5:
            case 6:
            case 7:
                return Boolean.class;
        }
        return String.class;
    }

    public String getColumnName(int column) {
        return columns[column];
    }

    public int getRowCount() {
        return files.length;
    }

    public File getFile(int row) {
        return files[row];
    }

    public void setFiles(File[] files) {
        this.files = files;
        fireTableDataChanged();
    }
}

/** A TreeCellRenderer for a File. */
class FileTreeCellRenderer extends DefaultTreeCellRenderer {

    private FileSystemView fileSystemView;

    private JLabel label;

    FileTreeCellRenderer() {
        label = new JLabel();
        label.setOpaque(true);
        fileSystemView = FileSystemView.getFileSystemView();
    }

    @Override
    public Component getTreeCellRendererComponent(
        JTree tree,
        Object value,
        boolean selected,
        boolean expanded,
        boolean leaf,
        int row,
        boolean hasFocus) {

        DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
        File file = (File)node.getUserObject();
        label.setIcon(fileSystemView.getSystemIcon(file));
        label.setText(fileSystemView.getSystemDisplayName(file));
        label.setToolTipText(file.getPath());

        if (selected) {
            label.setBackground(backgroundSelectionColor);
        } else {
            label.setBackground(backgroundNonSelectionColor);
        }
        return label;
    }
}
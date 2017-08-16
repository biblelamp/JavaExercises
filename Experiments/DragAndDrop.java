/**
 * Simple drag and drop file example
 * from 
 * http://java-demos.blogspot.ru/2013/06/drag-and-drop-file-in-jtextarea.html
 * with small changes
 */
import java.awt.dnd.*;
import java.awt.*;
import javax.swing.*;
import java.awt.datatransfer.*;
import java.io.*;

class DragAndDrop extends JFrame {
    private JTextArea jt;

    public DragAndDrop() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        setTitle("Drag and Drop Example");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null); // to the center of the screen
        setLayout(new BorderLayout());

        // Create JTextArea
        jt = new JTextArea();
        JScrollPane sb = new JScrollPane(jt);
        add(sb);

        enableDragAndDrop();

        setVisible(true);
    }

    private void enableDragAndDrop() {
        DropTarget target = new DropTarget(jt, new DropTargetListener() {
            public void dragEnter(DropTargetDragEvent e) {
            }
            public void dragExit(DropTargetEvent e) {
            }
            public void dragOver(DropTargetDragEvent e) {
            }
            public void dropActionChanged(DropTargetDragEvent e) {
            }

            public void drop(DropTargetDropEvent e) {
                try {
                    // Accept the drop first, important!
                    e.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);

                    // Get the files that are dropped as java.util.List
                    java.util.List list = (java.util.List) e.getTransferable().     getTransferData(DataFlavor.javaFileListFlavor);

                    // Now get the first file from the list,
                    File file = (File) list.get(0);
                    jt.read(new FileReader(file), null);

                } catch (Exception ex) {}
            }
        });
    }

    static public void main(String args[]) {
        new DragAndDrop();
    }
}
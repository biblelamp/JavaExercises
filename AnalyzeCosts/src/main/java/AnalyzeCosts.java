/**
 * Java. The program helps to understand: whither are funneling money
 *
 * @author Sergey Iryupin
 * @version 0.5.7 dated Jun 13, 2018
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.table.*;
import java.util.*;
import java.io.*;

import org.jopendocument.dom.spreadsheet.*; // www.jopendocument.org/
import com.toedter.calendar.*;              // toedter.com/jcalendar/
import org.jfree.chart.*;                   // www.jfree.org/jfreechart/
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.*;
import org.jfree.data.general.*;

public class AnalyzeCosts extends JFrame implements ComponentListener {

    final String TITLE_OF_PROGRAM = "Analyze private costs";
    final int WINDOW_WIDTH = 500;
    final int WINDOW_HEIGHT = 450;
    final String CLICK_ME = "Click me...";
    final String TOOL_TIP_FILE_NAME = "Click to select the data file *.ods";
    final String SET_FILE_FILTER = "Spreadsheet files (*.ods)";
    final String SET_FILE_EXT = "ods";
    final String PATH_IMG_BUTTON = "src\\main\\resources\\go.png";
    final String TOOL_TIP_BUTTON = "Click to calculate the results";
    final String SETTINGS_FILE = "settings.ini";
    final String TOTAL_TITLE = "Total";
    final String CHART_TITLE = "Chart";
    final String TYPE_COL_NAME = "Cost type";
    final String SUM_COL_NAME = "Cost sum";
    final String PRCNT_COL_NAME = "%";
    final String BOLD_BEGIN = "<html><b>";
    final String BOLD_END = "</b></html>";

    JDateChooser startDate;
    JDateChooser endDate;
    JTextField fileName;
    JTabbedPane tabbedPane;
    DefaultTableModel model;
    DefaultPieDataset dataset;
    JFreeChart chart;
    ChartPanel chartPanel;

    public static void main(String[] args) {
        new AnalyzeCosts();
    }

    AnalyzeCosts() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setLocationRelativeTo(null); // to the center

        startDate = new JDateChooser(new Date());
        endDate = new JDateChooser(new Date());

        fileName = new JTextField(CLICK_ME, 22);
        fileName.setToolTipText(TOOL_TIP_FILE_NAME);
        fileName.setEditable(false);
        fileName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                JFileChooser open = new JFileChooser();
                open.setFileSelectionMode(JFileChooser.FILES_ONLY);
                open.setFileFilter(new FileNameExtensionFilter(SET_FILE_FILTER, SET_FILE_EXT));
                int result = open.showOpenDialog(getParent());
                if (result == JFileChooser.APPROVE_OPTION) {
                    fileName.setText(open.getSelectedFile().getAbsolutePath());
                }
            }
        });

        ImageIcon icon = null;
        try {
            icon = new ImageIcon(PATH_IMG_BUTTON);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JButton getFileName = new JButton();
        getFileName.setPreferredSize(new Dimension(45, 30));
        getFileName.setIcon(icon);
        getFileName.setToolTipText(TOOL_TIP_BUTTON);
        getFileName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!fileName.getText().equals(CLICK_ME))
                    readAndAnalize("");
                saveSettings();
            }
        });

        JPanel upPanel = new JPanel();
        upPanel.add(startDate);
        upPanel.add(endDate);
        upPanel.add(fileName);
        upPanel.add(getFileName);

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            @Override
            public Class getColumnClass(int col) {
              if (col == 1 || col == 2)
                 return Integer.class;
              else
                 return String.class;
            }
        };
        model.addColumn(TYPE_COL_NAME);
        model.addColumn(SUM_COL_NAME);
        model.addColumn(PRCNT_COL_NAME);
        JTable table = new JTable(model);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    int col = table.getSelectedColumn();
                    if (col == 0)
                        readAndAnalize((String)table.getValueAt(row, col));
                }
            }
        });

        dataset = new DefaultPieDataset();
        chart = ChartFactory.createPieChart(
            "",        // chart title
            dataset,   // data
            false,     // include legend
            true,
            false);
        chartPanel = new ChartPanel(chart);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab(TOTAL_TITLE, new JScrollPane(table));
        tabbedPane.addTab(CHART_TITLE, chartPanel);

        add(BorderLayout.NORTH, upPanel);
        add(BorderLayout.CENTER, tabbedPane);
        addComponentListener(this);
        setVisible(true);
        readSettings();
    }

    // for override abstract methods
    public void componentHidden(ComponentEvent ce) {};
    public void componentShown(ComponentEvent ce) {};
    public void componentMoved(ComponentEvent ce) {};
    public void componentResized(ComponentEvent ce) {
        int height = this.getHeight();
        int width = this.getWidth();
        //System.out.println("Size: " + height + "x" + width);
    };

    void readAndAnalize(String typeOfCost) {
        SpreadSheet spreadSheet;
        Sheet sheet;
        MutableCell<SpreadSheet> cell;
        Date date = new Date();

        Map<String, Float> tm = new TreeMap<String, Float>();
        String[] arrayWords;
        float total = 0;

        File file = new File(fileName.getText());
        try {
            spreadSheet = SpreadSheet.createFromFile(file);
            for (int i = 0; i < spreadSheet.getSheetCount(); i++) {
                sheet = spreadSheet.getSheet(i);
                int row = 1;
                while (true) {

                    // get date
                    try {
                        cell = sheet.getCellAt(0, row);
                        if (!cell.isEmpty()) {
                            date = (Date) cell.getValue();
                        }
                    } catch (IllegalArgumentException e) {
                        //System.out.println("Bad address");
                    }

                    // operation
                    MutableCell<SpreadSheet> operation = sheet.getCellAt(1, row);

                    // sum
                    MutableCell<SpreadSheet> sum = sheet.getCellAt(2, row);

                    // check EOD on the page
                    if (operation.isEmpty() && (!sum.isEmpty())) {
                        break;
                    }

                    // next row
                    row++;

                    // date range check
                    if (date.before(startDate.getDate())) {
                        System.out.println(sheet.getName() + " " + date);
                        continue;
                    }
                    if (date.after(endDate.getDate())) {
                        break;
                    }

                    // collect by type of costs
                    if ((!operation.isEmpty()) && (!sum.isEmpty())) {
                        arrayWords = operation.getTextValue().split("(:|,)?( )+");
                        int idx = typeOfCost.isEmpty()? 0 : 1;
                        float add = Float.parseFloat(sum.getValue().toString());
                        float value = 0;
                        if ((idx == 0) || ((idx == 1) && arrayWords[0].equals(typeOfCost))) {
                            try {
                                value = tm.get(arrayWords[idx]);
                            } catch(NullPointerException e) { }
                            tm.put(arrayWords[idx], value + add);
                            total += add;
                        }
                    }
                }
            }
            tm.put(TOTAL_TITLE, total);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // sort by values
        ArrayList<Map.Entry<String, Float>> list = new ArrayList<Map.Entry<String, Float>>(tm.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Float>>() {
            public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
                Float v1 = o1.getValue();
                Float v2 = o2.getValue();
                return v1.compareTo(v2);
            }
        });

        // clear table and dataset
        model.setRowCount(0);
        dataset.clear();

        // show results
        //Iterator i = tm.entrySet().iterator(); // sort by name
        Iterator<Map.Entry<String, Float>> i = list.iterator();
        while (i.hasNext()) {
            Map.Entry<String, Float> obj = i.next();
            String key = obj.getKey();
            float value = (float) obj.getValue();
            // for Total panel
            String type = key;
            String strValue = String.format("%,.2f", value);
            String strPercent = String.format("%.2f%%", value/total*100);
            if (key == TOTAL_TITLE) {
                type = BOLD_BEGIN + (typeOfCost.isEmpty()? key : typeOfCost) + BOLD_END;
                strValue = BOLD_BEGIN + strValue + BOLD_END;
                strPercent = "";
            }
            String[] row = {type, strValue, strPercent};
            model.addRow(row);
            // for Chart panel
            if (key != TOTAL_TITLE) {
                dataset.setValue(key, new Double(value));
            }
        }

        // draw Chart
        final PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        //plot.setInteriorGap(0.0);
        //plot.setLabelGenerator(null);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} {2}"));
        //plot.setCircular(true);
    }

    void readSettings() {
        Object[] ini = new Object[3];
        try {
            FileInputStream fileIn = new FileInputStream(new File(SETTINGS_FILE));
            ObjectInputStream is = new ObjectInputStream(fileIn);
            ini = (Object[]) is.readObject();
            startDate.setDate((Date)ini[0]);
            endDate.setDate((Date)ini[1]);
            fileName.setText((String)ini[2]);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    void saveSettings() {
        Object[] ini = {startDate.getDate(), endDate.getDate(), fileName.getText()};
        try {
            FileOutputStream fileStream = new FileOutputStream(new File(SETTINGS_FILE));
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(ini);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
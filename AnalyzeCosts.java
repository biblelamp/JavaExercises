/**
 * Java. Simply program for analize private costs
 *
 * @author Sergey Iryupin
 * @version 0.3 dated 03 Aug 2016
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.filechooser.*;
import java.text.*;
import java.util.*;
import java.io.*;
import org.jopendocument.dom.spreadsheet.*;
import com.toedter.calendar.*; //https://www.ssec.wisc.edu/mcidas/software/v/javadoc/1.4/edu/wisc/ssec/mcidasv/data/dateChooser/JDateChooser.html

public class AnalyzeCosts {

    final String nameOfProgram = "Analyze private costs";
    final String SETTINGS_FILE = "settings.ini";
    final int WINDOW_WIDTH = 500;
    final int WINDOW_HEIGHT = 500;
    final int START_POSITION = 200;
    JFrame frame;
    JDateChooser startDate;
    JDateChooser endDate;
    JTextField fileName;
    JTextArea textArea;

    public static void main(String[] args) {
        new AnalyzeCosts().go();
    }

    void go() {
        frame = new JFrame(nameOfProgram);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLocation(START_POSITION, START_POSITION);
        frame.setResizable(false);

        startDate = new JDateChooser(new Date());
        //startDate.setDateFormatString("dd.MM.yy");

        endDate = new JDateChooser(new Date());

        fileName = new JTextField("Click me...", 22);
        fileName.setEditable(false);
        fileName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                JFileChooser open = new JFileChooser();
                open.setFileSelectionMode(JFileChooser.FILES_ONLY);
                open.setFileFilter(new FileNameExtensionFilter("Spreadsheet files (*.ods)", "ods"));
                int result = open.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    fileName.setText(open.getSelectedFile().getAbsolutePath());
                }
            }
        });

        JButton getFileName = new JButton("Go");
        getFileName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                readAndAnalize();
                saveSettings();
            }
        });

        JPanel upPanel = new JPanel();
        upPanel.add(startDate);
        upPanel.add(endDate);
        upPanel.add(fileName);
        upPanel.add(getFileName);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        frame.getContentPane().add(BorderLayout.NORTH, upPanel);
        frame.getContentPane().add(BorderLayout.CENTER, new JScrollPane(textArea));
        readSettings();
        frame.setVisible(true);
    }

    void readAndAnalize() {
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
            for (int i=0; i<spreadSheet.getSheetCount(); i++) {
                sheet = spreadSheet.getSheet(i);
                int row = 1;
                while (true) {

                    // date
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
                        continue;
                    }
                    if (date.after(endDate.getDate())) {
                        break;
                    }

                    // collect by type of costs
                    if ((!operation.isEmpty()) && (!sum.isEmpty())) {
                        arrayWords = operation.getTextValue().split(":");
                        //System.out.println(arrayWords[0] + ": " + sum.getValue());
                        float add = Float.parseFloat(sum.getValue().toString());
                        float value = 0;
                        try {
                            value = tm.get(arrayWords[0]);
                        } catch(NullPointerException e) { }
                        tm.put(arrayWords[0], value + add);
                        total += add;
                    }
                }
            }
            tm.put("Total", total);
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

        // clear textArea
        textArea.setText(null);

        // show results
        //Iterator i = tm.entrySet().iterator(); // sort by name
        Iterator<Map.Entry<String, Float>> i = list.iterator();
        while (i.hasNext()) {
            Map.Entry<String, Float> obj = i.next();
            String key = obj.getKey();
            float value = (float) obj.getValue();
            textArea.append(String.format("%s:\t%,.2f\t%.2f%%\n", key, value, value/total*100));
        }
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
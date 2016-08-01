/**
 * Java. Simply program for analize private costs
 *
 * @author Sergey Iryupin
 * @version 0.1 dated 01 Aug 2016
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

public class AnalyzeCosts {
    
    final String nameOfProgram = "Analyze own costs";
    final int WINDOW_WIDTH = 500;
    final int WINDOW_HEIGHT = 500;
    final int START_POSITION = 200;
    JFrame frame;
    JFormattedTextField startDate;
    JFormattedTextField endDate;
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

        DateFormatter dateFormatter = new DateFormatter(new SimpleDateFormat("dd.MM.yyyy"));
        dateFormatter.setAllowsInvalid(false);
        dateFormatter.setOverwriteMode(true);        
        
        startDate = new JFormattedTextField(dateFormatter);
        startDate.setColumns(6);
        startDate.setValue(new Date(116, 0, 1));

        endDate = new JFormattedTextField(dateFormatter);
        endDate.setColumns(6);
        endDate.setValue(new Date(116, 0, 31));

        fileName = new JTextField("Click me...", 25);
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
        frame.setVisible(true);
    }
    
    void readAndAnalize() {
        SpreadSheet spreadSheet;
        Sheet sheet;
        MutableCell cell;
        Date date = new Date();

        HashMap<String, Float> hm = new HashMap<String, Float>();
        Set<Map.Entry<String, Float>> set = hm.entrySet();
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
                    MutableCell operation = sheet.getCellAt(1, row);
                    
                    // sum
                    MutableCell sum = sheet.getCellAt(2, row);

                    // check EOD on the page
                    if (operation.isEmpty() && (!sum.isEmpty())) {
                        break;
                    }

                    // next row
                    row++;

                    // date range check
                    if (date.before((Date)startDate.getValue())) {
                        continue;
                    }
                    if (date.after((Date)endDate.getValue())) {
                        break;
                    }

                    // collect by type of costs
                    if ((!operation.isEmpty()) && (!sum.isEmpty())) {
                        arrayWords = operation.getTextValue().split(":");
                        //System.out.println(arrayWords[0] + ": " + sum.getValue());
                        float add = Float.parseFloat(sum.getValue().toString());
                        float value = 0;
                        try {
                            value = hm.get(arrayWords[0]);
                        } catch(NullPointerException e) { }
                        hm.put(arrayWords[0], value + add);
                        total += add;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // show results
        for (Map.Entry<String, Float> o : set) {
            textArea.append(String.format("%s:\t%,.2f\t%.2f%%\n", o.getKey(), o.getValue(), o.getValue()/total*100));
        }
    }
}
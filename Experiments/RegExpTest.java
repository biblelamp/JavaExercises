/**
 * Java. Test for regexp
 *
 * @author Sergey Iryupin
 * @version 0.1 dated October 10, 2016
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class RegExpTest extends JFrame {

    final String TITLE_OF_PROGRAM = "RegExp Testing";
    final static int WINDOW_WIDTH = 500;
    final static int WINDOW_HEIGHT = 400;
    final static int START_POSITION = 200;
    JTextField pattern;
    JTextArea textIn;
    JTextArea textOut;

    public static void main(String[] args) {
        new RegExpTest();
    }

    RegExpTest() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_POSITION, START_POSITION, WINDOW_WIDTH, WINDOW_HEIGHT);
        setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        Font font = new Font("", Font.BOLD, 22);
        pattern = new JTextField("[ {,|.}?]+", 22); // [\s\t\'\"\-{,|.|\?|\!}?]
        pattern.setFont(font);
        JButton btnGo = new JButton("Go");
        btnGo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
                Set<Map.Entry<String, Integer>> set = tm.entrySet();
                String[] lines = textIn.getText().split("\\n");
                for (String line : lines) {
                    //System.out.println(line);
                    String[] words = line.toLowerCase().split(pattern.getText());
                    for (String word : words) {
                        int value = 0;
                        try {
                            value = tm.get(word);
                        } catch(NullPointerException ex) { }
                        tm.put(word, value + 1);
                    }
                }
                textOut.setText(null);
                for (Map.Entry<String, Integer> o : set) {
                    textOut.append(o.getKey() + ": " + o.getValue() + "\n");
                }
                textOut.append("The book has " + tm.size() + " unique words.");
            }
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(pattern);
        btnPanel.add(btnGo);
        textIn = new JTextArea(10, 10);
        textIn.setLineWrap(true);
        textOut = new JTextArea(10, 10);
        JScrollPane scrollOut = new JScrollPane(textOut);
        JScrollPane scrollIn = new JScrollPane(textIn);
        add(BorderLayout.NORTH, btnPanel);
        add(BorderLayout.CENTER, scrollIn);
        add(BorderLayout.SOUTH, scrollOut);
        setVisible(true);
    }
}
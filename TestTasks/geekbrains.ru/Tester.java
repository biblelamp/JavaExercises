/**
 * Java. Simple test system
 *
 * @author Sergey Iryupin
 * @version 0.4 dated Sep 03, 2017
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import java.util.*;
import java.io.*;
import java.nio.charset.*;

class Tester extends JFrame implements ActionListener {

    final String TITLE_OF_PROGRAM = "Tester: simple test system";
    final String MENU_FILE = "File";
    final String MENU_OPEN = "Open...";
    final String MENU_EXIT = "Exit";
    final int WINDOW_WIDTH = 400;
    final int WINDOW_HEIGHT = 400;
    final String BTN_RESET = "Reset";
    final String BTN_NEXT = "Next";
    final String BTN_BACK = "Back";

    JLabel ctrlLabel;
    MainPanel mainPanel;
    JLabel mainLabel;
    JTextField msg;
    Test test; // test object

    public static void main(String[] args) {
        new Tester();
    }

    Tester() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setLocationRelativeTo(null); // to the center

        // menu/File: open and exit
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu(MENU_FILE);
        fileMenu.setMnemonic('F'); // create shortcut
        JMenuItem openItem = new JMenuItem(MENU_OPEN);
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser open = new JFileChooser(".");
                open.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = open.showDialog(null, MENU_OPEN);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = open.getSelectedFile();
                    test.readFile(file.getAbsolutePath()); //"khasang.test");
                    mainLabel.setText(test.toString());
                    mainPanel.repaint();
                }
            }
        });
        openItem.setAccelerator(KeyStroke.getKeyStroke('O', CTRL_DOWN_MASK));
        JMenuItem exitItem = new JMenuItem(MENU_EXIT);
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        exitItem.setAccelerator(KeyStroke.getKeyStroke('X', CTRL_DOWN_MASK));
        // build menu
        fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar); // set menu

        test = new Test(); // create object test

        mainLabel = new JLabel();
        mainLabel.setFont(new Font(null, Font.PLAIN, 12));

        mainPanel = new MainPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(mainLabel);

        JPanel bp = new JPanel();
        bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));
        JButton reset = new JButton(BTN_RESET);
        reset.addActionListener(this);
        msg = new JTextField();
        msg.addActionListener(this);
        JButton next = new JButton(BTN_NEXT);
        next.addActionListener(this);
        JButton back = new JButton(BTN_BACK);
        back.addActionListener(this);
        bp.add(reset);
        bp.add(msg);
        bp.add(next);
        bp.add(back);

        add(mainPanel, BorderLayout.CENTER);
        add(bp, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // reset test
        if (event.getActionCommand().equals(BTN_RESET)) {
            test.init();
            mainLabel.setText(test.toString());
            mainPanel.repaint();
        }
        // enter choice/answer and go
        if (event.getActionCommand().equals(BTN_NEXT) ||
            event.getActionCommand().equals(msg.getText()))
            if (!msg.getText().isEmpty()) {
                int choice;
                try {
                    choice = Integer.parseInt(msg.getText());
                } catch (NumberFormatException ex) {
                    choice = -1;
                }
                if (choice > 0 && test.getPointer() < test.getSize()) {
                    test.setChoice(choice);
                    test.incPointer();
                    mainLabel.setText(test.toString());
                    mainPanel.repaint();
                    msg.setText("");
                }
            }
        // go back
        if (event.getActionCommand().equals(BTN_BACK))
            if (test.getPointer() > 0) {
                test.decPointer();
                test.setChoice(0);
                mainLabel.setText(test.toString());
                mainPanel.repaint();
            }
    }

    class MainPanel extends JPanel { // for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int i = 0; i < test.getSize(); i++) {
                g.setColor(Color.black);
                g.drawOval(20 + i * 12, 15, 8, 8);
                if (test.getQuestion(i).getChoice() > 0) {
                    g.setColor(test.getQuestion(i).isNotSure()?
                        Color.lightGray :
                            (test.getQuestion(i).isRight()?
                                Color.green : Color.red));
                    g.fillOval(20 + i * 12, 15, 8, 8);
                }
            }
        }
    }
}

class Test {
    final String QUESTION_MARK = "@Question";
    final String OPTION_MARK = "@Options";
    final String KEY_MARK = "@Key";
    private ArrayList<Question> questions = new ArrayList<>();
    private int pointer;

    void init() {
        for (Question question : questions)
            question.setChoice(0);
        pointer = 0;
    }

    boolean readFile(String file_name) {
        boolean isQuestion;
        boolean isOptions;
        String line, question;
        questions.clear();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(file_name), StandardCharsets.UTF_8))) {

            isQuestion = false;
            isOptions = false;
            question = "";

            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("<", "&lt;");
                line = line.replaceAll("&lt;pre>", "<pre>");   // fix for <pre>
                line = line.replaceAll("&lt;code>", "<code>"); // fix for <code>
                line = line.replaceAll("&lt;/", "</");         // and </...
                if (line.startsWith(QUESTION_MARK)) {
                    isQuestion = true;
                    question = "";
                } else if (line.startsWith(OPTION_MARK)) {
                    isQuestion = false;
                    isOptions = true;
                    questions.add(new Question(question));
                    int idx = line.indexOf(KEY_MARK);
                    if (idx > 0)
                        questions.get(questions.size() - 1)
                            .setKey((int)line.charAt(idx + 5) - 48);
                    if (line.indexOf("?") > 0)
                        questions.get(questions.size() - 1)
                            .setNotSure();
                } else if (!line.isEmpty()) {
                    if (isQuestion)
                        question += (question.isEmpty())?
                            line : "<br />" + line;
                    if (isOptions)
                        questions.get(questions.size() - 1).addOption(line);
                } else
                    isOptions = false;
            }
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
        System.out.println(questions.size() +
            " questions are successfully read.");
        pointer = 0;
        return true;
    }

    Question getQuestion(int i) {
        return questions.get(i);
    }

    int getSize() {
        return questions.size();
    }

    boolean isRight() {
        return questions.get(pointer).isRight();
    }

    int getPointer() {
        return pointer;
    }

    void decPointer() {
        if (pointer > 0) pointer--;
    }

    void incPointer() {
        if (pointer < questions.size() - 1) pointer++;
    }

    void setChoice(int choice) {
        questions.get(pointer).setChoice(choice);
    }

    @Override
    public String toString() {
        return (pointer < questions.size())?
            questions.get(pointer).toString(pointer) : "";
    }
}

class Question {
    private String text;
    private ArrayList<String> options;
    private int key;
    private int choice;
    private boolean notSureYet;     // not sure of the correct answer

    Question(String text) {         // set text of question
        this.text = text;
        options = new ArrayList<>();
    }

    void addOption(String option) { // add item of option
        options.add(option);
    }

    void setKey(int key) {          // set right key
        this.key = key;
    }

    int getChoice() {
        return choice;
    }

    void setChoice(int choice) {    // set user choice
        this.choice = choice;
    }

    boolean isNotSure() {
        return notSureYet;
    }

    void setNotSure() {             // set flag of uncertainty
        notSureYet = true;
    }

    boolean isRight() {
        return key == choice;
    }

    public String toString(int n) {
        String result = "";
        for (int i = 0; i < options.size(); i++)
            result += "<li>" + options.get(i) + "</li>";
        return "<html><h2>" +
            (n + 1) + ". " +
            text + "</h2><ol>" +
            result + "</ol>" +
            (notSureYet? "* No confidence in the correct answer" : "") +
            "</html>";
    }
}
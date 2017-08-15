/**
 * Java. Simple test system
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Aug 15, 2017
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.nio.charset.*;

class Tester extends JFrame implements ActionListener {

    final String TITLE_OF_PROGRAM = "Tester: simple test system";
    final int START_LOCATION = 200;
    final int WINDOW_WIDTH = 400;
    final int WINDOW_HEIGTH = 400;
    final String BTN_RESET = "Reset";
    final String BTN_NEXT = "Next";

    JPanel ctrlPanel;
    JLabel ctrlLabel;
    JPanel mainPanel;
    JLabel mainLabel;
    JTextField msg;
    Test test; // test object

    public static void main(String[] args) {
        new Tester();
    }

    Tester() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_HEIGTH);

        test = new Test();
        test.readFile("khasang.test");

        ctrlLabel = new JLabel("<html>");
        ctrlLabel.setFont(new Font(null, Font.PLAIN, 14));
        ctrlPanel = new JPanel();
        ctrlPanel.add(ctrlLabel);

        mainLabel = new JLabel();
        mainLabel.setFont(new Font(null, Font.PLAIN, 12));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        mainPanel.add(mainLabel);

        mainLabel.setText(test.toString());

        JPanel bp = new JPanel();
        bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));
        JButton reset = new JButton("Reset");
        reset.addActionListener(this);
        msg = new JTextField();
        JButton next = new JButton("Next");
        next.addActionListener(this);
        bp.add(reset);
        bp.add(msg);
        bp.add(next);

        add(ctrlPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(bp, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals(BTN_RESET)) {
            test.init();
            mainLabel.setText(test.toString());
            ctrlLabel.setText("<html>");
        }
        if (event.getActionCommand().equals(BTN_NEXT))
            if (!msg.getText().isEmpty()) {
                int choice;
                try {
                    choice = Integer.parseInt(msg.getText());
                } catch (NumberFormatException ex) {
                    choice = -1;
                }
                if (choice > 0) {
                    test.setChoice(choice);
                    ctrlLabel.setText(ctrlLabel.getText() +
                        ((test.isRight())? 
                            "<font color=green>+</font>" :
                            "<font color=red>x</font>"));
                    test.incPointer();
                    mainLabel.setText(test.toString());
                    msg.setText("");
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
        pointer = 0;
    }

    boolean readFile(String file_name) {
        boolean isQuestion;
        boolean isOptions;
        String line, question;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(file_name), StandardCharsets.UTF_8))) {

            isQuestion = false;
            isOptions = false;
            question = "";

            while ((line = reader.readLine()) != null) {

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
                } else if (!line.isEmpty()) {
                    if (isQuestion)
                        question += line;
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

    boolean isRight() {
        return questions.get(pointer).isRight();
    }

    int getPointer() {
        return pointer;
    }

    void incPointer() {
        if (pointer < questions.size() - 1) pointer++;
    }

    void setChoice(int choice) {
        questions.get(pointer).setChoice(choice);
    }

    @Override
    public String toString() {
        return questions.get(pointer).toString(pointer);
    }
}

class Question {
    private String text;
    private ArrayList<String> options;
    private int key;
    private int choice;

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

    void setChoice(int choice) {    // set user choice
        this.choice = choice;
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
            result + "</ol></html>";
    }
}
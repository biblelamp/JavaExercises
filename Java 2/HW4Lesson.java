/**
 * Java. Level 2. Lesson 4. Example of homework
 *
 * @author Sergey Iryupin
 * @version 0.1.2 dated Jun 18, 2018
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

class HW4Lesson extends JFrame implements ActionListener {

    final String TITLE_OF_PROGRAM = "Client for net.chat";
    final int WINDOW_WIDTH = 350;
    final int WINDOW_HEIGHT = 450;
    final String BTN_ENTER = "Enter";
    final String LOG_FILE_NAME = "logchat.txt";

    JTextArea dialogue; // area for dialog
    JTextField message; // field for entering messages

    public static void main(String[] args) {
        new HW4Lesson();
    }

    /**
     * Constructor:
     * Creating a window and all the necessary elements on it
     */
    HW4Lesson() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null); // to the center
        // area for dialog
        dialogue = new JTextArea();
        dialogue.setEditable(false);
        JScrollPane scrollBar = new JScrollPane(dialogue);
        // panel for checkbox, message field and button
        JPanel bp = new JPanel();
        bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));
        message = new JTextField();
        message.addActionListener(this);
        JButton enter = new JButton(BTN_ENTER);
        enter.addActionListener(this);
        // adding all elements to the window
        bp.add(message);
        bp.add(enter);
        add(BorderLayout.CENTER, scrollBar);
        add(BorderLayout.SOUTH, bp);
        setVisible(true);
    }

    /**
     * Listener of events from message field and enter button
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (message.getText().trim().length() > 0) {
            dialogue.append(message.getText() + "\n");
            try (PrintWriter pw =
                    new PrintWriter(new FileWriter(LOG_FILE_NAME, true))) {
                pw.println(message.getText());
                pw.flush();
            } catch (IOException ex) { }
        }
        message.setText("");
        message.requestFocusInWindow();
    }
}
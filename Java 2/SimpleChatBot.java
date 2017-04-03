/**
 * Java. Simple Chat-Bot
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Apr 3, 2016
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import bot.*;

class SimpleChatBot extends JFrame implements ActionListener {

    final String TITLE_OF_PROGRAM = "Simple Chat-Bot";
    final int START_LOCATION = 200;
    final int WINDOW_WIDTH = 350;
    final int WINDOW_HEIGHT = 450;

    JTextArea dialogue; // area for dialog
    JCheckBox ai;       // enable/disable AI
    JTextField message; // field for entering messages
    SimpleBot sbot;     // chat-bot class (in bot package)

    public static void main(String[] args) {
        new SimpleChatBot();
    }

    /**
     * Constructor:
     * Creating a window and all the necessary elements on it
     */
    SimpleChatBot() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
        // area for dialog
        dialogue = new JTextArea();
        dialogue.setLineWrap(true);
        JScrollPane scrollBar = new JScrollPane(dialogue);
        // panel for checkbox, message field and button
        JPanel bp = new JPanel();
        bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));
        ai = new JCheckBox("AI");
        message = new JTextField();
        message.addActionListener(this);
        JButton enter = new JButton("Enter");
        enter.addActionListener(this);
        // adding all elements to the window
        bp.add(ai);
        bp.add(message);
        bp.add(enter);
        add(BorderLayout.CENTER, scrollBar);
        add(BorderLayout.SOUTH, bp);
        setVisible(true);
        sbot = new SimpleBot(); // creating bot object
    }

    /**
     * Listener of events from message field and enter button
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (message.getText().trim().length() > 0) {
            dialogue.append(message.getText() + "\n");
            dialogue.append(sbot.sayInReturn(message.getText(), ai.isSelected()) + "\n");
        }
        message.setText("");
        message.requestFocusInWindow();
    }
}
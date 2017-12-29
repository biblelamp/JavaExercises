/**
 * Java. Level 2. Lesson 4. Homework
 *  1. Create a window for client-side chat (large text field to display
 *  the correspondence, one-line text box for writing messages, to send messages
 *  button). The message should be sent either by pressing a button on the form,
 *  or by pressing the "Enter". "Sent" message should appear in the top text box.
 *  2. "Sent" message also should should be duplicated in the text file.
 *
 * @author Sergey Iryupin
 * @version 18 July 2016
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Lesson4 {

    JTextArea incoming;
    JTextField outgoing;

    public static void main(String[] args) {
        Lesson4 chat = new Lesson4();
        chat.go();
    }

    public void go() {
        JFrame frame = new JFrame("Simple Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        incoming = new JTextArea(25, 40);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outgoing = new JTextField(33);
        outgoing.addActionListener(new SendButtonListener());
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        mainPanel.add(qScroller);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setBounds(200, 200, 470, 475);
        frame.setVisible(true);
    }

    public class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            if (!outgoing.getText().trim().isEmpty()) {
                incoming.append(outgoing.getText() + "\n");
                try {
                    PrintWriter pw = new PrintWriter(new FileWriter("chat.txt", true));
                    pw.write(outgoing.getText() + "\n");
                    pw.close();
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
            }
            outgoing.setText("");
        }
    }
}
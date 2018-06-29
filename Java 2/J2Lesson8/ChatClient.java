/**
 * Java. Level 2. Lesson 8
 * Simple chat client
 * To make jar file:
 *  > create manifest.txt file with 1 line
 *      Main-Class: ChatClient
 *  > jar -cvmf manifest.txt ChatClient.jar
 *      ChatClient*.class IConstants.class
 *
 * @author Sergey Iryupin
 * @version 0.2.4 dated Jun 29, 2018
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

class ChatClient extends JFrame implements ActionListener, IConstants {

    final String TITLE_OF_PROGRAM = "Client for net.chat";
    final int WINDOW_WIDTH = 350;
    final int WINDOW_HEIGHT = 450;
    final String BTN_ENTER = "Enter";
    final String AUTH_INVITATION = "You must login using command\n"+
        "auth <login> <passwd>";

    JTextArea dialogue; // area for dialog
    JTextField message; // field for entering messages
    boolean isAuthorized; // flag of authorization

    Socket socket;
    PrintWriter writer;
    BufferedReader reader;

    public static void main(String[] args) {
        new ChatClient();
    }

    /**
     * Constructor:
     * Creating a window and all the necessary elements on it
     */
    ChatClient() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null); // to the center
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    writer.println(EXIT_COMMAND);
                    writer.flush();
                } catch (Exception ex) {}
            }
        });
        // area for dialog
        dialogue = new JTextArea();
        dialogue.setLineWrap(true);
        dialogue.setEditable(false);
        JScrollPane scrollBar = new JScrollPane(dialogue);
        // panel for connamd field and button
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
        Connect(); // connect to the Server
    }

    /**
     * Connect to the Server
     */
    void Connect() {
        dialogue.append(AUTH_INVITATION + "\n");
        isAuthorized = false;
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            new Thread(new ServerListener()).start(); // start Server listener
        } catch (Exception ex) { 
            dialogue.append(ex.getMessage() + "\n");
        }
    }

    /**
     * ServerListener: get messages from Server
     */
    class ServerListener implements Runnable {
        String message;
        public void run() {
            try {
                while ((message = reader.readLine()) != null) {
                    if (message.startsWith("Hello, ")) // check authorisation
                        isAuthorized = true;
                    if (!message.equals("\0") && isAuthorized)
                        dialogue.append(message + "\n");
                    if (message.equals(AUTH_FAIL))
                        System.exit(-1); // terminate client
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            System.exit(-1); // terminate client
        }
    }

    /**
     * Listener of events from message field and enter button
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (message.getText().trim().length() > 0) {
            try {
                writer.println(message.getText());
                writer.flush();
            } catch (Exception ex) {}
        }
        message.setText("");
        message.requestFocusInWindow();
    }
}
/**
 * Java. Client - test task from TradingView
 *
 * @author Sergey Iryupin
 * @version 0.2.3 dated November 24, 2016
 */
import java.io.*;
import java.net.*;
import java.util.*;

class Client {

    final String SERVER_IP = "127.0.0.1";
    final int SERVER_PORT = 2048;
    final String PROMPT = "$";
    final String GET_COMMAND = "get"; // get file
    final String EXIT_COMMAND = "exit"; // command for exit
    final String CONNECTION_START = "Connection to server established.";
    final String CONNECTION_CLOSED = "Connection closed.";
    Scanner scanner = new Scanner(System.in); // for keyboard input

    Socket socket;
    PrintWriter writer;
    BufferedReader reader;

    public static void main(String[] args) {
        new Client().go();
    }

    void go() {
        String message;
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.print(CONNECTION_START + "\n" + PROMPT);
            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Thread getSrvMessage = new Thread(new ServerListener());
            getSrvMessage.start();
            try {
                do {
                    message = scanner.nextLine();
                    writer.println(message);
                    writer.flush();
                } while (!message.equals(EXIT_COMMAND));
                writer.close();
                socket.close();
                System.exit(0);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(CONNECTION_CLOSED);
    }

    class ServerListener implements Runnable { // get messages from Server
        public void run() {
            String message;
            String[] command;
            boolean toFile = false;
            FileWriter fw = null;
            try {
                while ((message = reader.readLine()) != null) {
                    if (message.startsWith(GET_COMMAND)) {
                        System.out.println(message);
                        command = message.split(" ");
                        toFile = true;
                        fw = new FileWriter(command[1]);
                    } else if (message.equals("\0")) {
                        System.out.print(PROMPT);
                        if (toFile) {
                            toFile = false;
                            fw.flush();
                            fw.close();
                        }
                    } else if (toFile) {
                        fw.write(message + "\n");
                    } else {
                        System.out.println(message);
                    }
                }
            } catch(Exception ex) { 
                ex.printStackTrace();
            }
        }
    }
}
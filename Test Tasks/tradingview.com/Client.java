/**
 * Java. Client - test task from TradingView
 *
 * @author Sergey Iryupin
 * @version 0.2.2 dated November 23, 2016
 */
import java.io.*;
import java.net.*;
import java.util.*;

class Client {

    final String SERVER_IP = "127.0.0.1";
    final int SERVER_PORT = 2048;
    final String PROMPT = "\n$";
    final String EXIT_COMMAND = "exit"; // command for exit
    final String CONNECTION_START = "Connection to server established.";
    final String CONNECTION_CLOSED = "Connection closed.";
    Scanner scanner = new Scanner(System.in); // for keyboard input

    Socket sock;
    PrintWriter writer;
    BufferedReader reader;

    public static void main(String[] args) {
        new Client().go();
    }

    void go() {
        String message;
        try {
            sock = new Socket(SERVER_IP, SERVER_PORT);
            System.out.print(CONNECTION_START + PROMPT);
            writer = new PrintWriter(sock.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            Thread getSrvMessage = new Thread(new ServerListener());
            getSrvMessage.start();
            try {
                do {
                    message = scanner.nextLine();
                    writer.println(message);
                    writer.flush();
                } while (!message.toLowerCase().equals(EXIT_COMMAND));
                writer.close();
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
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.print(message);
                    System.out.print(PROMPT);
                }
            } catch(Exception ex) { 
                ex.printStackTrace();
            }
        }
    }
}
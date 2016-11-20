/**
 * Java. Client - test task from TradingView
 *
 * @author Sergey Iryupin
 * @version 0.2 dated November 20, 2016
 */
import java.io.*;
import java.net.*;
import java.util.*;

class Client {

    Scanner scanner = new Scanner(System.in); // for keyboard input
    final String IP_ADDRESS = "127.0.0.1";
    final int PORT = 2048;
    final String EXIT_COMMAND = "exit"; // command for exit

    Socket sock;
    PrintWriter writer;
    BufferedReader reader;

    public static void main(String[] args) {
        new Client().go();
    }

    void go() {
        String message;
        try {
            sock = new Socket(IP_ADDRESS, PORT);
            writer = new PrintWriter(sock.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            System.out.println("Connection with the server established:");
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
    }

    class ServerListener implements Runnable { // get messages from Server
        public void run() {
        String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("get: " + message);
                }
            } catch(Exception ex) { 
                ex.printStackTrace();
            }
        }
    }
}
/**
 * Java. Level 2. Lesson 6. Homework
 *  Simple console chat client
 *
 * @author Sergey Iryupin
 * @version 27 July 2016
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {

    final String SERVER_ADDR = "localhost";
    final int PORT = 8189;
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    String message;
    Scanner read = new Scanner(System.in);

    public static void main(String[] args) {
        new ChatClient().startClient();
    }

    public void startClient() {

        // set up networking
        try {
            sock = new Socket(SERVER_ADDR, PORT);
            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("Networking established.");
        } catch(Exception e) { e.printStackTrace(); }

        // creating a thread to read incoming messages
        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();

        // read string and send it ro server
        while (true) {
            message = read.nextLine(); // waiting line input
            if (message.equalsIgnoreCase("end")) break; // end of session
            try {
                writer.println(message); // send strings to the server
                writer.flush();
            } catch(Exception e) { e.printStackTrace(); }
        }
        System.out.println("Connection closed.");
        System.exit(0);
    }

    // reading incoming messages
    public class IncomingReader implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println(message);
                }
            } catch(Exception e) { e.printStackTrace(); }
        }
    }
}
import java.io.*;
import java.net.*;
import java.util.*;

class SimpleClient {

    final String SERVER_ADDR = "127.0.0.1"; // or "localhost"
    final int SERVER_PORT = 2048;
    final String PROMPT = "$ ";
    final String EXIT_COMMAND = "exit"; // command for exit
    final String CONNECTION_START = "Connection to server established.";
    final String CONNECTION_CLOSED = "Connection closed.";

    Socket socket;
    Scanner scanner;
    PrintWriter writer;

    public static void main(String[] args) {
        new SimpleClient();
    }

    SimpleClient() {
        scanner = new Scanner(System.in); // for keyboard input
        String message;
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            System.out.println(CONNECTION_START);
            writer = new PrintWriter(socket.getOutputStream());
            do {
                System.out.print(PROMPT);
                message = scanner.nextLine();
                writer.println(message);
                writer.flush();
            } while (!message.equals(EXIT_COMMAND));
            writer.close();
            socket.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(CONNECTION_CLOSED);
    }
}
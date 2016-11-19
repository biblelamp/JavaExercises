import java.io.*;
import java.net.*;
import java.util.*;

class Client {

    Scanner scanner = new Scanner(System.in);
    final String IP_ADDRESS = "127.0.0.1";
    final int PORT = 2048;
    final String EXIT_COMMAND = "exit";

    public static void main(String[] args) {
        new Client().go();
    }

    void go() {
        String message;
        try {
            Socket sock = new Socket(IP_ADDRESS, PORT);
            PrintWriter writer = new PrintWriter(sock.getOutputStream());
            try {
                do {
                    System.out.print(">");
                    message = scanner.nextLine();
                    writer.println(message);
                    writer.flush();
                } while (!message.toLowerCase().equals(EXIT_COMMAND));
                writer.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
/**
 * Java. Level 2. Lesson 6. Networking
 * Class HelloServer: send message to client
 *
 * @author Sergey Iryupin
 * @version dated Nov 10, 2017
 */
import java.net.*;
import java.io.*;

class HelloServer {

    public static void main(String[] args) {
        new HelloServer();
    }

    HelloServer() {
        try {
            ServerSocket server = new ServerSocket(1024); // more than 1023
            System.out.println("Server started.");
            while (true) {
                Socket socket = server.accept();
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                writer.println("Server says: Hello");
                System.out.println("Message was sent to the client.");
                writer.close();
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
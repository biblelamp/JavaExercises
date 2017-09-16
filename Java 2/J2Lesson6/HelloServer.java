import java.net.*;
import java.io.*;

class HelloServer {

    public static void main(String[] args) {
        new HelloServer();
    }

    HelloServer() {
        try {
            ServerSocket server = new ServerSocket(1024);
            System.out.println("Server started.");
            while (true) {
                Socket socket = server.accept();
                System.out.println("Client connected.");
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                writer.println("Server says: Hello");
                writer.close();
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
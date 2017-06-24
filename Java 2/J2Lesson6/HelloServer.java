import java.net.*;
import java.io.*;

class HelloServer {

    ServerSocket server;
    Socket socket;
    PrintWriter writer;
    
    public static void main(String[] args) {
        new HelloServer();
    }

    HelloServer() {
        try {
            server = new ServerSocket(2048);
            System.out.println("Server started.");
            while (true) {
                socket = server.accept();
                System.out.println("Client connected.");
                writer = new PrintWriter(socket.getOutputStream());
                writer.println("Server says: Hello");
                writer.close();
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
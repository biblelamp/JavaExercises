import java.io.*;
import java.net.*;

class SimpleServer {

    final int SERVER_PORT = 2048;
    final String SERVER_START = "Server is started...";
    final String SERVER_STOP = "Server stopped.";
    final String CLIENT_JOINED = " client joined.";
    final String CLIENT_DISCONNECTED = " disconnected";
    final String EXIT_COMMAND = "exit"; // command for exit
    int client_count = 0;

    ServerSocket server;
    Socket socket;

    public static void main(String[] args) {
        new SimpleServer().go();
    }

    void go() {
        try {
            server = new ServerSocket(SERVER_PORT);
            System.out.println(SERVER_START);
            while (true) {
                socket = server.accept();
                client_count++;
                System.out.println("#" + client_count + CLIENT_JOINED);
                new Thread(new ClientHandler(socket)).start();
            } 
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                server.close();
                System.out.println(SERVER_STOP);
                socket.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * ClientHandler: service requests of clients
     */
    class ClientHandler implements Runnable {
        BufferedReader reader;
        PrintWriter writer;
        Socket socket;
        String name;

        ClientHandler(Socket clientSocket) {
            try {
                socket = clientSocket;
                reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream());
                name = "Client #" + client_count;
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        @Override
        public void run() {
            String message;
            try {
                do {
                    message = reader.readLine();
                    System.out.println(name + ": " + message);
                    writer.println("echo: " + message);
                    writer.flush();
                } while (!message.equalsIgnoreCase(EXIT_COMMAND));
                socket.close();
                System.out.println(name + CLIENT_DISCONNECTED);
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
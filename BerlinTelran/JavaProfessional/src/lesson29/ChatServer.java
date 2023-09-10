package lesson29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

    public static int SERVER_PORT = 2048;
    public static String SERVER_IP = "localhost";
    public static String EXIT_CMD = "/exit";
    public static String RENAME_CMD = "/rename";

    private List<ClientHandler> clients;

    public static void main(String[] args) {
        new ChatServer();
    }

    public ChatServer() {
        int clientCount = 0;
        clients = new ArrayList<>();
        System.out.println("Server started...");
        try (ServerSocket server = new ServerSocket(SERVER_PORT)) {
            while (true) {
                Socket socket = server.accept();
                String name = "Client #" + (++clientCount);
                ClientHandler client = new ClientHandler(socket, name);
                clients.add(client);
                new Thread(client).start();
                System.out.println(name + ": joined.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendToAll(String name, String message) {
        for (ClientHandler client : clients) {
            if (!name.equals(client.name)) {
                client.send(name + ": " + message);
            }
        }
    }

    private class ClientHandler implements Runnable {
        private BufferedReader reader;
        private PrintWriter writer;
        private Socket socket;
        private String name;

        public ClientHandler(Socket socket, String name) {
            this.socket = socket;
            this.name = name;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void send(String message) {
            writer.println(message);
            writer.flush();
        }

        @Override
        public void run() {
            String message;
            try {
                do {
                    message = reader.readLine();
                    if (message.equalsIgnoreCase(EXIT_CMD)) {
                        send(message);
                        sendToAll(name, "disconnected");
                    } else {
                        sendToAll(name, message);
                    }
                    System.out.println(name + ": " + message);
                } while (!message.equalsIgnoreCase(EXIT_CMD));
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            clients.remove(this);
            System.out.println(name + ": disconected.");
        }
    }
}

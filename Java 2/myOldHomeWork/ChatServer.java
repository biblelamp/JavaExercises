/**
 * Java. Level 2. Lesson 6. Homework
 *  Simple chat server
 *
 * @author Sergey Iryupin
 * @version 27 July 2016
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

    ArrayList clientOutputStreams;
    final int PORT = 8189;
    static int CLIENTS_COUNT = 0;

    public static void main(String[] args) {
        new ChatServer().startServer();
    }

    public void startServer() {
        clientOutputStreams = new ArrayList();
        try {
            ServerSocket serverSock = new ServerSocket(PORT);
            System.out.println("Server is running. Waiting for clients...");
            while (true) {
                Socket clientSocket = serverSock.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);
                new Thread(new ClientHandler(clientSocket)).start();
                System.out.println("Client is connected");
            }
        } catch(Exception e) { e.printStackTrace(); }
    }

    public class ClientHandler implements Runnable {

        BufferedReader reader;
        Socket sock;
        String nameClient;

        public ClientHandler(Socket clientSocket) {
            try {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
                CLIENTS_COUNT++;
                nameClient = "Client #" + CLIENTS_COUNT;
            } catch(Exception e) { e.printStackTrace(); }
        }

        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println(nameClient + ": " + message);
                    Iterator it = clientOutputStreams.iterator();
                    while (it.hasNext()) {
                        try {
                            PrintWriter writer = (PrintWriter) it.next();
                            writer.println(nameClient + ": " + message);
                            writer.flush();
                        } catch(Exception e) { e.printStackTrace(); }
                    }
                }
            } catch(SocketException e) {
                System.out.println(nameClient + " finished");
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
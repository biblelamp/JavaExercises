/**
 * Java. Server - test task from TradingView
 *
 * @author Sergey Iryupin
 * @version 0.3 dated November 22, 2016
 */
import java.io.*;
import java.net.*;

class Server {

    final int SERVER_PORT = 2048;
    final String LS_COMMAND = "ls"; // get list of files
    final String GET_COMMAND = "get"; // get file
    final String EXIT_COMMAND = "exit"; // logoff

    final String SERVER_STARTED = "Server started.";
    final String CLIENT_JOINED = "Client joined.";
    final String CLIENT_DISCONNECTED = "Client is disconnected.";

    public static void main(String[] args) {
        new Server().go();
    }

    class ClientHandler implements Runnable {
        BufferedReader reader;
        PrintWriter writer;
        Socket sock;

        ClientHandler(Socket clientSocket) {
            try {
                sock = clientSocket;
                reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                writer = new PrintWriter(sock.getOutputStream());
            } catch(Exception ex) { 
                ex.printStackTrace(); 
            }
        }

        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("get: " + message);
                    if (message.toLowerCase().equals(EXIT_COMMAND)) // exit
                        break;
                    else {
                        writer.println(message);
                        writer.flush();
                    }
                }
            } catch(Exception ex) { 
                ex.printStackTrace();
            }
            System.out.println(CLIENT_DISCONNECTED);
        }
    }

    void go() {
        String message;
        try {
            System.out.println(SERVER_STARTED);
            ServerSocket serverSock = new ServerSocket(SERVER_PORT);
            while (true) {
                Socket clientSocket = serverSock.accept();
                Thread client = new Thread(new ClientHandler(clientSocket));
                client.start();
                System.out.println(CLIENT_JOINED);
            }
        } catch(IOException ex) {
            ex.printStackTrace();  
        }
    }
}
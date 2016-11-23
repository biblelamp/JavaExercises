/**
 * Java. Server - test task from TradingView
 *
 * @author Sergey Iryupin
 * @version 0.3.1 dated November 23, 2016
 */
import java.io.*;
import java.net.*;

class Server {

    final int SERVER_PORT = 2048;
    final String LS_COMMAND = "ls"; // get list of files
    final String GET_COMMAND = "get"; // get file
    final String EXIT_COMMAND = "exit"; // logoff
    final String UNKNOWN_COMMAND = "Unknown command. Use only: ls/get <filename>/exit";

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
            String[] command;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("get: " + message);
                    command = message.split(" ");
                    if (command[0].equals(LS_COMMAND)) { // list of files
                        message = getListOfFiles();
                    } else if (command[0].equals(GET_COMMAND)) { // get file
                        message = getFile();
                    } else if (message.toLowerCase().equals(EXIT_COMMAND)) { // logoff
                        break;
                    } else {
                        message = UNKNOWN_COMMAND;
                    }
                    writer.println(message);
                    writer.flush();
                }
            } catch(Exception ex) { 
                ex.printStackTrace();
            }
            System.out.println(CLIENT_DISCONNECTED);
        }
    }

    String getListOfFiles() {
        String message;
        return "list of file(s)";
    }

    String getFile() {
        String message;
        return "get <filename>";
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
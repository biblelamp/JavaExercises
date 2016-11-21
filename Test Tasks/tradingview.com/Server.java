/**
 * Java. Server - test task from TradingView
 *
 * @author Sergey Iryupin
 * @version 0.2 dated November 21, 2016
 */
import java.io.*;
import java.net.*;

class Server {

    final int SERVER_PORT = 2048;
    final String EXIT_COMMAND = "exit"; // command for exit

    final String SERVER_STARTED = "Server started.";
    final String CLIENT_JOINED = "Client joined.";
    final String CLIENT_DISCONNECTED = "Client is disconnected.";

    public static void main(String[] args) {
        new Server().go();
    }

    void go() {
        String message;
        try {
            System.out.println(SERVER_STARTED);
            ServerSocket serverSock = new ServerSocket(SERVER_PORT);
            while (true) {
                Socket sock = serverSock.accept();
                System.out.println(CLIENT_JOINED);
                BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                PrintWriter writer = new PrintWriter(sock.getOutputStream());
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
        } catch(IOException ex) {
            ex.printStackTrace();  
        }
    }
}
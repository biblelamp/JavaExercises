/**
 * Java. Level 2. Lesson 7
 * Simple server for chat
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Nov 18, 2017
 */
import java.io.*;
import java.net.*;
import java.sql.*;

class SimpleServer implements IConstants {

    public static void main(String[] args) {
        new SimpleServer();
    }

    SimpleServer() {
        int clientCount = 0;
        System.out.println(SERVER_START);
        try {
            ServerSocket server = new ServerSocket(SERVER_PORT);
            while (true) {
                Socket socket = server.accept();
                System.out.println("#" + (++clientCount) + CLIENT_JOINED);
                new Thread(new ClientHandler(socket, clientCount)).start();
            } 
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(SERVER_STOP);
    }

    /**
     * checkAuthentication: check login and password
     *
     * @param  login for checking
     * @param  passwd for checking
     *
     * @return if the pair login/passwd is found in the database,
     *         authentication is successful
     */
    private boolean checkAuthentication(String login, String passwd) {
        Connection connect;
        boolean result = false;
        try {
            // connect db
            Class.forName(DRIVER_NAME);
            connect = DriverManager.getConnection(SQLITE_DB);
            // looking for login && passwd in db
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_SELECT.replace("?", login));
            while (rs.next())
                result = rs.getString(PASSWD_COL).equals(passwd);
            // close all
            rs.close();
            stmt.close();
            connect.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
    }

    /**
     * ClientHandler: service requests of clients
     */
    class ClientHandler implements Runnable {
        BufferedReader reader;
        PrintWriter writer;
        Socket socket;
        String name;

        ClientHandler(Socket clientSocket, int clientCount) {
            try {
                socket = clientSocket;
                reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream());
                name = "Client #" + clientCount;
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
                    if (message != null) {
                        System.out.println(name + ": " + message);
                        if (message.startsWith(AUTH_SIGN)) {
                            String[] wds = message.split(" ");
                            if (checkAuthentication(wds[1], wds[2])) {
                                name = wds[1];
                                writer.println("Hello, " + name);
                                writer.println("\0");
                            } else {
                                System.out.println(name + ": " + AUTH_FAIL);
                                writer.println(AUTH_FAIL);
                                message = EXIT_COMMAND;
                            }
                        } else if (!message.equalsIgnoreCase(EXIT_COMMAND)) {
                            writer.println("echo: " + message);
                            writer.println("\0");
                        }
                        writer.flush();
                    }
                } while (!message.equalsIgnoreCase(EXIT_COMMAND));
                socket.close();
                System.out.println(name + CLIENT_DISCONNECTED);
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
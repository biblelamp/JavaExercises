/**
 * Java. Level 2. Lesson 7
 * Simple server for chat
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Apr, 14 2017
 */
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

class SimpleServer implements IConstants {

    int client_count = 0;
    ServerSocket server;
    Socket socket;

    public static void main(String[] args) {
        new SimpleServer();
    }

    SimpleServer() {
        System.out.println(SERVER_START);
        new Thread(new CommandHandler()).start();
        try {
            server = new ServerSocket(SERVER_PORT);
            while (true) {
                socket = server.accept();
                client_count++;
                System.out.println("#" + client_count + CLIENT_JOINED);
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(SERVER_STOP);
    }

    /**
     * checkAuthentication: check login and password
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
     * CommandHandler: processing of commands from server console
     */
    class CommandHandler implements Runnable {
        Scanner scanner = new Scanner(System.in);

        @Override
        public void run() {
            String command;
            do
                command = scanner.nextLine();
            while (!command.equals(EXIT_COMMAND));
            try {
                server.close();
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
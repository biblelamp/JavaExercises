/**
 * Java. Level 2. Lesson 7
 * Simple chat client v2
 *
 * @author Sergey Iryupin
 * @version 0.2.1 dated Jan 16, 2018
 */
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;

class SimpleClient implements IConstants {

    public static void main(String[] args) {
        new SimpleClient();
    }

    SimpleClient() {
        String message;
        try (Socket socket = new Socket(SERVER_ADDR, SERVER_PORT);
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in)) {
            System.out.println(CONNECT_TO_SERVER);
            // sending: auth <login> <passwd>
            writer.println(getLoginAndPassword(scanner));
            writer.flush();
            new Thread(new ServerListener(reader)).start();
            do {
                message = scanner.nextLine();
                writer.println(message);
                writer.flush();
            } while (!message.equals(EXIT_COMMAND));
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(CONNECT_CLOSED);
    }

    /**
     * getLoginAndPassword: read login and password from keyboard
     */
    private String getLoginAndPassword(Scanner scanner) {
        System.out.print(LOGIN_PROMPT);
        String login = scanner.nextLine();
        System.out.print(PASSWD_PROMPT);
        return AUTH_SIGN + " " + login + " " + scanner.nextLine();
    }

    /**
     * ServerListener: get messages from Server
     */
    class ServerListener implements Runnable {
        BufferedReader reader;

        ServerListener(BufferedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.print(message.equals("\0")?
                        CLIENT_PROMPT : message + "\n");
                    if (message.equals(AUTH_FAIL))
                        System.exit(-1); // terminate client
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
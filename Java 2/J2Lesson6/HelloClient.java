/**
 * Java. Level 2. Lesson 6. Networking
 * Class HelloClient: connect and get hello from server
 *
 * @author Sergey Iryupin
 * @version dated Jan 11, 2018
 */
import java.net.*;
import java.io.*;

class HelloClient {

    public static void main(String[] args) {
        new HelloClient();
    }

    HelloClient() {
        try (Socket socket = new Socket("127.0.0.1", 1024);
            BufferedReader reader =
                new BufferedReader(
                    new InputStreamReader(socket.getInputStream()))) {

            System.out.println(
                reader.readLine());

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
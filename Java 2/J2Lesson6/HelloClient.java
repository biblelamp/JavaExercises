import java.net.*;
import java.io.*;

class HelloClient {

    public static void main(String[] args) {
        new HelloClient();
    }

    HelloClient() {
        try {
            Socket socket = new Socket("127.0.0.1", 1024);
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            String str = reader.readLine();
            System.out.println(str);
            reader.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
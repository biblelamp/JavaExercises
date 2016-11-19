import java.io.*;
import java.net.*;

class Server {

    public static void main(String[] args) {
        new Server().go();
    }

    void go() {
        String message;
        try {
            ServerSocket serverSock = new ServerSocket(2048);
            while (true) {
                Socket sock = serverSock.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                try {
                    while ((message = reader.readLine()) != null) {
                        System.out.println("get: " + message);
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch(IOException ex) {
            ex.printStackTrace();  
        }
    }
}
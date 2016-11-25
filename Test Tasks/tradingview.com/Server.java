/**
 * Java. Server - test task from TradingView
 *
 * @author Sergey Iryupin
 * @version 0.3.3 dated November 25, 2016
 */
import java.io.*;
import java.net.*;
import java.util.logging.*;

class Server {

    final int SERVER_PORT = 2048;
    final String LS_COMMAND = "ls"; // get list of files
    final String GET_COMMAND = "get"; // get file
    final String EXIT_COMMAND = "exit"; // logoff
    final String UNKNOWN_COMMAND = "Unknown command. Use only: ls/get <filename>/exit";

    final String SERVER_STARTED = "Server started.";
    final String CLIENT_JOINED = "Client joined.";
    final String CLIENT_DISCONNECTED = "Client is disconnected.";

    final String SERVER_DIR;
    final String LOG_FILE_NAME = "log.txt";
    Logger logger; // for logging

    public static void main(String[] args) {
        new Server(args).go();
    }

    Server(String[] args) {
        // set up dir
        SERVER_DIR = (args.length > 0)? args[0] : ".";
        // set up logging
        logger = Logger.getLogger(Server.class.getName());
        try {
            FileHandler fh = new FileHandler(LOG_FILE_NAME);
            logger.addHandler(fh);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
                    //System.out.println("get: " + message);
                    logger.log(Level.INFO, message);
                    command = message.split(" ");
                    if (command[0].equals(LS_COMMAND)) { // list of files
                        message = getListOfFiles();
                    } else if (command[0].equals(GET_COMMAND)) { // get file
                        message = getFile(command[1]);
                    } else if (message.equals(EXIT_COMMAND)) { // logoff
                        break;
                    } else {
                        message = UNKNOWN_COMMAND;
                    }
                    writer.println(message);
                    writer.println("\0");
                    writer.flush();
                }
            } catch(Exception ex) { 
                ex.printStackTrace();
            }
            System.out.println(CLIENT_DISCONNECTED);
        }
    }

    String getListOfFiles() {
        String message = "";
        File dir = new File(SERVER_DIR);
        if (dir.exists()) {
            File[] fileList = dir.listFiles();
            for (File file : fileList)
                if(file.isFile())
                    message += "\n" + file.getName();
        }
        return "List of file(s):" + message;
    }

    String getFile(String fileName) throws IOException {
        File file = new File(SERVER_DIR + File.separator + fileName);
        if (file.exists()) {
            int length = (int) file.length();
            char[] cbuf = new char[length];
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
            int read = isr.read(cbuf); 
            String message = new String(cbuf, 0, read);
            return GET_COMMAND + " " + file.getName() + "\n" + message;
        } else {
            return "Error: no such file.";
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
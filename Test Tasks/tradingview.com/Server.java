/**
 * Java. Server - test task for TradingView
 *
 * @author Sergey Iryupin
 * @version 0.3.5 dated March 09, 2017
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;

class Server {

    final int SERVER_PORT = 2048;
    final String LS_COMMAND = "ls"; // get list of files
    final String GET_COMMAND = "get"; // get file
    final String EXIT_COMMAND = "exit"; // logoff
    final String UNKNOWN_COMMAND = "Unknown command. Use only: ls | get <filename> | exit";
    final String SERVER_PROMPT = "Type 'exit' to stop server\n> ";

    final String SERVER_STARTED = "Server started.";
    final String SERVER_STOPPED = "Server stopped.";
    final String CLIENT_JOINED = "Client joined.";
    final String CLIENT_DISCONNECTED = "Client is disconnected.";
    final String MSG_LIST_OF_FILES = "List of file(s):";
    final String MSG_NO_SUCH_FILE = "Error: no such file.";

    final String SERVER_DIR;
    final String LOG_FILE_NAME = "log.txt";
    Logger logger; // for logging

    TimerTask task;
    Timer timer = new Timer();
    final int RECORDING_PERIOD = 10; // in sec
    final String STATISTIC_FILE_NAME = "statistic.srv.txt";

    public static void main(String[] args) {
        new Server(args).go();
    }

    /**
     * Constructor: get Server dir and start logging
     */
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

    /**
     * ClientHandler: service requests of clients
     */
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

    /**
     * CommandHandler: processing of commands of console
     */
    class CommandHandler implements Runnable {
        Scanner scanner;
        String command;

        CommandHandler() {
            scanner = new Scanner(System.in);
        }

        public void run() {
            do {
                System.out.print(SERVER_PROMPT);
                command = scanner.nextLine();
            } while (!command.equals(EXIT_COMMAND));
            //System.out.println(SERVER_STOPPED);
            logger.log(Level.INFO, SERVER_STOPPED);
            System.exit(0);
        }
    }

    /**
     * SaveStatistic: save statistic to the file
     */
    class SaveStatistic extends TimerTask {
        public void run() {
            System.out.println("Save statistic...");
        }
    }

    /**
     * getListOfFiles: get list of files from server dir
     *
     * @param : no
     * @return : String (list of files)
     */
    String getListOfFiles() {
        String message = "";
        File dir = new File(SERVER_DIR);
        if (dir.exists()) {
            File[] fileList = dir.listFiles();
            for (File file : fileList)
                if(file.isFile())
                    message += "\n" + file.getName();
        }
        return MSG_LIST_OF_FILES + message;
    }

    /**
     * getFile: get file from disk to buffer
     *
     * @param : String (filename)
     * @return : String (command + filename + buffer)
     */
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
            return MSG_NO_SUCH_FILE;
        }
    }

    void go() {
        String message;
        // set timer for saving
        task = new SaveStatistic();
        timer.schedule(task, RECORDING_PERIOD * 1000, RECORDING_PERIOD * 1000);
        try {
            //System.out.println(SERVER_STARTED);
            logger.log(Level.INFO, SERVER_STARTED);
            Thread cmd = new Thread(new CommandHandler());
            cmd.start();
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
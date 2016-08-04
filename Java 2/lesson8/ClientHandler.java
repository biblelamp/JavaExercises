/**
 * Java. Level 2. Lesson 8. Homework
 * Simple network chat
 *
 * @author Sergey Iryupin
 * @version 02 Aug 2016
 */
import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {

    private final Server server;
    private final Socket socket;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    private volatile String nickName;

    public String getNickName() {
        return nickName;
    }

    public ClientHandler(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            authorize();
            if (isAuthorized()) {
                startMessageProcessing();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cleanUp(socket);
        }
    }

    public boolean isAuthorized() {
        return nickName != null;
    }

    private void startMessageProcessing() throws IOException {
        while (true) {
            String msg = inputStream.readUTF();
            if (msg.equalsIgnoreCase("END")) {
                break;
            } else {
                server.broadcast(formMsg(msg));
            }
        }
    }

    private void authorize() throws IOException {
        int errorCount = 0;
        while (!isAuthorized()) {
            String msg = inputStream.readUTF();
            try {
                String curNick = getClientFromDB(msg);
                if (server.isNickInChat(curNick)) {
                    throw new HandShakeException("user with nickname " + curNick + " is already in chat");
                }
                nickName = curNick;
                server.addClient(this);
                sendMsg("authorized successfully");
                server.broadcast(formMsg(" joined chat"));
            } catch (HandShakeException e) {
                e.printStackTrace();
                sendMsg("authorization error");
                errorCount++;
                if (errorCount > 3) {
                    break;
                }
            }
        }
    }

    public String formMsg(String msg) {
        return nickName + ": " + msg;
    }

    private String getClientFromDB(String msg) {
        // login <login> <password>
        String[] loginMessage = msg.split(" ");
        String login = loginMessage[1];
        String password = loginMessage[2];
        return new DBHandler().getNickNameByLoginAndPassword(login, password);
    }

    private void cleanUp(Socket socket) {
        if (isAuthorized()) {
            server.removeClient(this);
            nickName = null;
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMsg(String msg) {
        System.out.println("writing msg " + msg + " to " + nickName);
        try {
            outputStream.writeUTF(msg);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
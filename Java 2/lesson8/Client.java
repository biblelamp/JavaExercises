/**
 * Java. Level 2. Lesson 8. Homework
 * Simple network chat
 *
 * @author Sergey Iryupin
 * @version 02 Aug 2016
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    private Listener listener;
    private Listener handShakeErrorListener;
    private Socket socket;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;
    private boolean isAuthorized = false;

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public Client(Listener listener, Listener handShakeErrorListener) {
        this.listener = listener;
        this.handShakeErrorListener = handShakeErrorListener;
    }

    public void connect() {
        try {
            socket = new Socket("localhost", Server.PORT);
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new HandShakeException(e.getMessage(), e);
        }
    }

    public boolean connect(String login, String password) {
        connect();
        if (authorize(login, password)) {
            startProcessResponseCycle();
        } else {
            return false;
        }
        return true;
    }

    public void sendMsg(String msg) {
        System.out.println("sending... " + msg);
        try {
            outputStream.flush();
            outputStream.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void startProcessResponseCycle() {
        new Thread(() -> {
            try {
                while (true) {
                    String msg = inputStream.readUTF();
                    if (isAuthorized)
                        notifyListener(msg);
                    else notifyHandShakeErrorListener(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (socket != null)
                        socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public boolean authorize(String login, String password) {
        sendMsg(formLoginMsg(login, password));
        try {
            String msg = inputStream.readUTF();
            if ("authorized successfully".equals(msg)) {
                isAuthorized = true;
                return true;
            } else {
                handShakeErrorListener.processMessage(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String formLoginMsg(String login, String password) {
        return "login " + login + " " + password;
    }

    private void notifyHandShakeErrorListener(String msg) {
        handShakeErrorListener.notify();
    }

    private void notifyListener(String s) {
        listener.processMessage(s);
    }

    private static void startChatWithMaximAndThenEnd() {
        Client client = new Client((s) -> System.out.println(s), (s) -> System.out.println(s));

        if (client.connect("maxim", "user")) {
            client.sendMsg("Hi all");
            client.sendMsg("what's up?");
            client.sendMsg("END");
        }
    }

    private static void startChatWithOleg() {
        Client client = new Client((s) -> System.out.println(s), (s) -> System.out.println(s));

        if (client.connect("oleg", "user")) {
            client.sendMsg("Hi there");
            client.sendMsg("My name is mr. Oleg");
        }
    }

    public static void main(String[] args) {
        startChatWithOleg();
        startChatWithMaximAndThenEnd();
    }
}
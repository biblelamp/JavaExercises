/**
 * Java. Level 2. Lesson 8. Homework
 * Simple network chat
 *
 * @author Sergey Iryupin
 * @version 02 Aug 2016
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    public static final int PORT = 8189;

    private ConcurrentHashMap<String, ClientHandler> handlerMap = new ConcurrentHashMap<>();

    public void startServer(){
        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            while(true){
                initConnection(serverSocket.accept());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void initConnection(Socket socket){
        ClientHandler handler = new ClientHandler(this, socket);
        new Thread(handler).start();
    }

    public void removeClient(ClientHandler handler){
        handlerMap.remove(handler.getNickName());
        broadcast(handler.formMsg("left the chat"));
    }

    public void addClient(ClientHandler handler){
        handlerMap.put(handler.getNickName(), handler);
    }

    public void broadcast(String msg) {
        for(ClientHandler handler: handlerMap.values()){
            if(handler.isAuthorized()) {
                handler.sendMsg(msg);
            }
        }
    }

    public static void main(String[] args) {
        new Server().startServer();
    }

    public boolean isNickInChat(String curNick) {
        return handlerMap.keySet().contains(curNick);
    }
}
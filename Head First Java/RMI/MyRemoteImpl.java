/*
 * Step 2: implementation of the remote interface
 */
import java.rmi.*;
import java.rmi.server.*;

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

    public MyRemoteImpl() throws RemoteException { };

    public String sayHello() {
        return "Server says: Hello";
    }

    public static void main(String[] args) {
        try {
            MyRemote service = new MyRemoteImpl();
            Naming.bind("RemoteHello", service);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
/*
 * Step 1: my remote interface
 */
import java.rmi.*;

public interface MyRemote extends Remote {
    public String sayHello() throws RemoteException;
}
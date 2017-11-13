/**
 * Java. Level 2. Lesson 6. Example of extended homework
 *  Task: write code attacking Hello server
 *  Note: we must see in console this message
 *      java.net.ConnectExceprion: Connection refused: connect
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Nov 13, 2017
 */
import java.util.*;

class AttackHelloSrv {

    public static void main(String[] args) throws InterruptedException {
        List<AttackThread> list = new ArrayList<>();

        for (int i = 0; i < 60; i++)
            list.add(new AttackThread());

        for (AttackThread thread : list)
            thread.start();

        Thread.sleep(2000);

        for (AttackThread thread : list)
            thread.interrupt();
    }
}

class AttackThread extends Thread {
    public void run() {
        while(!interrupted())
            new HelloClient();
    }
}
import java.util.*;

class AttackHelloSrv {

    public static void main(String[] args) throws InterruptedException {
        List<AttackThread> list = new ArrayList<>();

        for (int i = 0; i < 10; i++)
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
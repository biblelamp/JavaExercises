/**
 * Java. Level 3. Lesson 4
 * An example of applying the methods wait() and notify()
 *
 * from Java: A Beginner's Guide, Herbert Schildt, pages 423-424
 */
class TickTock {
    String state;

    synchronized void tick(boolean running) {
        if (!running) {
            state = "ticked";
            notify();
            return;
        }
        System.out.print("Tick ");
        state = "ticked";
        notify();                   // to allow execution of the tock()
        try {
            while (!state.equals("tocked"))
                wait();             // wait until the tock() completes
        } catch (InterruptedException ex) {
            System.out.println("Interrupted");
        }
    }

    synchronized void tock(boolean running) {
        if (!running) {
            state = "tocked";
            notify();
            return;
        }
        System.out.println("Tock");
        state = "tocked";
        notify();                   // to allow execution of the tick()
        try {
            while (!state.equals("ticked"))
                wait();             // wait until the tick() completes
        } catch (InterruptedException ex) {
            System.out.println("Interrupted");
        }
    }
}

class MyThread implements Runnable {
    Thread thrd;
    TickTock ttOb;

    MyThread(String nare, TickTock tt) {
        thrd = new Thread(this, nare);
        ttOb = tt;
        thrd.start();
    }

    public void run() {
        if (thrd.getName().compareTo("Tick") == 0) {
            for(int i = 0; i < 5; i++)
                ttOb.tick(true);
            ttOb.tick(false);
        } else {
            for(int i = 0; i < 5; i++)
                ttOb.tock(true);
            ttOb.tock(false);
        }
    }
}

class TickTockExample {
    public static void main(String[] args) {
        TickTock tt = new TickTock();
        MyThread rtl = new MyThread("Tick", tt);
        MyThread rt2 = new MyThread("Tock", tt);
        try {
            rtl.thrd.join();
            rt2.thrd.join();
        } catch (InterruptedException ex) {
            System.out.println("Interrupted");
        }
    }
}
class Q {
    int n;
    boolean valueSet = false;
    synchronized int get() {
        while (!valueSet) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Exception");
            }
        }
        System.out.println("Received: " + n);
        valueSet = false;
        notify();
        return n;
    }
    synchronized void put(int n) {
        while (valueSet) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Exception");
            }
        }
        this.n = n;
        valueSet = true;
        System.out.println("Sent: " + n);
        notify();
    }
}
class Producer implements Runnable {
    Q q;
    Producer (Q q) {
        this.q = q;
        new Thread(this, "Provider").start();
    }
    public void run() {
        int i = 0;
        while (i < 7) {
            q.put(i++);
        }
    }
}
class Consumer implements Runnable {
    Q q;
    Consumer (Q q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }
    public void run() {
        int i = 0;
        while(i < 6) {
            i = q.get();
        }
    }
}
public class PCfixed {
    public static void main(String[] args) {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);
    }
}
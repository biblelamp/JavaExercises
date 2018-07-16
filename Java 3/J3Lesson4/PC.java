class Q {
    int n;
    synchronized int get() {
        System.out.println("Received: " + n);
        return n;
    }
    synchronized void put(int n) {
        this.n = n;
        System.out.println("Sent: " + n);
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
public class PC {
    public static void main(String[] args) {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);
    }
}
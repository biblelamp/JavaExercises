public class WaitNotifyClass {
    private final Object monitor = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {
        WaitNotifyClass w = new WaitNotifyClass();
        Thread t1 = new Thread(() -> {
            w.printA();
        });
        Thread t2 = new Thread(() -> {
            w.printB();
        });
        t1.start();
        t2.start();
    }

    void printA() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 3; i++) {
                    while (currentLetter != 'A')
                        monitor.wait();
                    System.out.print("A");
                    currentLetter = 'B';
                    monitor.notify();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    void printB() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 3; i++) {
                    while (currentLetter != 'B')
                        monitor.wait();
                    System.out.print("B");
                    currentLetter = 'A';
                    monitor.notify();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
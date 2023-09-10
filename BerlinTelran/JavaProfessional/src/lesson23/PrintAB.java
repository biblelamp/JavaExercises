package lesson23;

public class PrintAB {
    private final Object lock = new Object();
    private volatile char currentChar = 'A';

    public void printA() {
        synchronized (lock) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (currentChar != 'A') {
                        lock.wait();
                    }
                    System.out.print('A');
                    currentChar = 'B';
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (lock) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (currentChar != 'B') {
                        lock.wait();
                    }
                    System.out.print('B');
                    currentChar = 'A';
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

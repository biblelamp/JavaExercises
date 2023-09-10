package homework23;

public class PrintABC {
    private char[] abc = {'A', 'B', 'C'};
    private volatile int count = 0;
    private final Object mon = new Object();
    private volatile char currentChar = 'A';

    public void print(char ch) {
        synchronized (mon) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (currentChar != ch) {
                        mon.wait();
                    }
                    System.out.print(ch);
                    count = count == 2? 0 : count + 1;
                    currentChar = abc[count];
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (currentChar != 'B') {
                        mon.wait();
                    }
                    System.out.print('B');
                    currentChar = 'C';
                    mon.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printC() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (currentChar != 'C') {
                        mon.wait();
                    }
                    System.out.print('C');
                    currentChar = 'A';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

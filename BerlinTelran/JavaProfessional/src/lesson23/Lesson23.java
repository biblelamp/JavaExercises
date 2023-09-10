package lesson23;

public class Lesson23 {
    public static void main(String[] args) throws InterruptedException {
        CounterSync count = new CounterSync();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                count.inc();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                count.dec();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count.getValue());
    }
}

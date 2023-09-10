package lesson22;

public class ThreadExt extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(String.format("Thread #%d: %d", currentThread().getId(), i));
        }
    }
}

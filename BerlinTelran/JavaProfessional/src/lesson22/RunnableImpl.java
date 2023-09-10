package lesson22;

import static java.lang.Thread.currentThread;

public class RunnableImpl implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(String.format("Thread #%d: %d", currentThread().getId(), i));
        }
    }
}

import java.util.concurrent.CountDownLatch;

class CountDownLatchDemo {
    public static void main(String[] args) {
        final int THREADS_COUNT = 6;
        final CountDownLatch cdl = new CountDownLatch(THREADS_COUNT);
        System.out.println("Start...");
        for (int i = 0; i < THREADS_COUNT; i++) {
            final int w = i;
            new Thread(() -> {
                try {
                    Thread.sleep(500 + (int)(500 * Math.random()));
                    cdl.countDown();
                    System.out.println("Thread #" + w + " - ready");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End.");
    }
}
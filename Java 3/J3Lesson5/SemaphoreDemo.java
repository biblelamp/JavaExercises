import java.util.concurrent.Semaphore;

class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 5; i++) {
            final int w = i;
            new Thread(() -> {
                try {
                    System.out.println("Thread " + w + " before the semaphore");
                    semaphore.acquire();
                    System.out.println("Thread " + w + " accessed the resource");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Thread " + w + " freed the resource");
                    semaphore.release();
                }
            }).start();
        }
    }
}
public class RunnableDemo {
    public static void main(String[] args) {
        RunnableClass rc = new RunnableClass();
        new Thread(rc).start();
        try {
            Thread.sleep(800);
            rc.mySuspend();
            Thread.sleep(3000);
            rc.myResume();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    static class RunnableClass implements Runnable {
        boolean suspended = false;
        public void run() {
            System.out.println("Running");
            try {
                for (int i = 10; i > 0; i--) {
                    System.out.println("Thread: " + i);
                    Thread.sleep(300);
                    synchronized (this) {
                        while (suspended) {
                            wait();
                        }
                    }
                }
            } catch (InterruptedException ex) {
                System.out.println("Thread interrupted");
            }
            System.out.println("Thread exiting");
        }
        public void mySuspend() {
            suspended = true;
        }
        public synchronized void myResume() {
            suspended = false;
            notify();
        }
    }
}
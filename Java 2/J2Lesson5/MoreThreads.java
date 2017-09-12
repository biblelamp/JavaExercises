class MoreThreads { // using isAlive() method

    public static void main(String args[]) {
        System.out.println("Main thread is starting...");

        MyThread mt1 = new MyThread("Child #1"); // based on MyThread extends Thread
        MyThread mt2 = new MyThread("Child #2");
        MyThread mt3 = new MyThread("Child #3");

        do {
            System.out.print(".");
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Мain thread is interrupted.");
            }
        } while (mt1.isAlive() || // waiting for completion all threads
                 mt2.isAlive() ||
                 mt3.isAlive());
        System.out.println("Main thread is complete.");
    }
}
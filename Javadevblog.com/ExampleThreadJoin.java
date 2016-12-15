class ExampleThreadJoin {

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable(), "t1");
        Thread t2 = new Thread(new MyRunnable(), "t2");
        Thread t3 = new Thread(new MyRunnable(), "t3");

        t1.start();

        // 2nd thread start only after a 2-second wait 1st thread
        try {
            t1.join(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        t2.start();

        // start of the 3rd thread only after the finish of 1st thread
        try {
            t1.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        t3.start();

        // give the opportunity to all threads to finish execution
        // before a program (main thread) finishes its execution
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.println("All threads are done.");
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread started:::" + Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Thread finished:::" + Thread.currentThread().getName());
    }
}
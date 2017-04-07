class ImpRunnable {

    public static void main(String args[]) {
        System.out.println("Main thread is starting...");

        MyThread mt1 = new MyThread("Child #1");
        //MyThread mt2 = new MyThread("Child #2");
        //MyThread mt3 = new MyThread("Child #3");

        for (int i = 0; i < 50; i++) {
            System.out.print(".");
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Мain thread is interrupted.");
            }
        }
        System.out.println("Main thread is complete.");
    }
}

class MyThread implements Runnable {
    Thread thread;

    // creating new thread
    MyThread(String name) {
        thread = new Thread(this, name);
        thread.start();
    }

    // starting new thread
    public void run() {
        System.out.println(thread.getName() + " is starting...");
        for (int i = 0; i < 10; i++) {
            System.out.println(thread.getName() + ", counter " + i);
            try {
                Thread.sleep(400);
            } catch (InterruptedException ex) {
                System.out.println(thread.getName() + " is interrupted.");
            }
        }
        System.out.println(thread.getName() + " is complete.");
    }
}
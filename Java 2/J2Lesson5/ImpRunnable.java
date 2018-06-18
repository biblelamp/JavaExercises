class ImpRunnable {

    public static void main(String args[]) {
        System.out.println("Main thread is starting...");

        MyRunnableClass mt1 = new MyRunnableClass("Child #1");
        MyRunnableClass mt2 = new MyRunnableClass("Child #2");
        //MyRunnableClass mt3 = new MyRunnableClass("Child #3");

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

class MyRunnableClass implements Runnable {
    Thread thread;

    // creating new thread
    MyRunnableClass(String name) {
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
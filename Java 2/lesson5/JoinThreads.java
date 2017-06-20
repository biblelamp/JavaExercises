class JoinThreads { // using join() method

    public static void main(String args[]) {
        System.out.println("Main thread is starting...");

        MyThread mt1 = new MyThread("Child #1");
        MyThread mt2 = new MyThread("Child #2");
        MyThread mt3 = new MyThread("Child #3");

        try {
            mt1.thread.join();
            mt2.thread.join();
            mt3.thread.join();
            System.out.println(mt1.thread.getName() + " is joined.");
            System.out.println(mt2.thread.getName() + " is joined.");
            System.out.println(mt3.thread.getName() + " is joined.");
        } catch (InterruptedException ex) {
            System.out.println("Мain thread is interrupted.");
        }
        System.out.println("Main thread is complete.");
    }
}
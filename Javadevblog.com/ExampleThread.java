class ExampleThread {

    public static void main(String[] args){
        Thread t1 = new Thread(new HeavyWorkRunnable(), "t1");
        Thread t2 = new Thread(new HeavyWorkRunnable(), "t2");
        System.out.println("Starting runnable threads...");
        t1.start();
        t2.start();
        System.out.println("Runnable treads in work");
        Thread t3 = new MyThread("t3");
        Thread t4 = new MyThread("t4");
        System.out.println("Starting custom threads...");
        t3.start();
        t4.start();
        System.out.println("Custom threads in work");
    }
}

class HeavyWorkRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Start in thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
            doDBProcessing();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Stop in thread " + Thread.currentThread().getName());
    }
    // stub method
    private void doDBProcessing() throws InterruptedException {
        Thread.sleep(5000);
    }
}

class MyThread extends Thread {

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Start in thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
            doDBProcessing();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Stop in thread " + Thread.currentThread().getName());
    }
    // stub method
    private void doDBProcessing() throws InterruptedException {
        Thread.sleep(5000);
    }
}
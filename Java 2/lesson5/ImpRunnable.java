class ImpRunnable {
    public static void main(String args[]) {
        System.out.println("Starting main thread...");
        TestThreadClass mpObj = new TestThreadClass();
        Thread t1 = new Thread(mpObj); 
        t1.setName("t1");
        t1.start();
        for (int i=0; i < 10; i++) {
            System.out.println("main: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Exception in main thread.");
            }
        }
        System.out.println("Completion main thread.");
    }
}

class TestThreadClass implements Runnable {
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("Starting " + name + " thread...");
        for (int i=0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Exception in " + name + " thread.");
            }
            System.out.println(name + ": " + i);
        }
        System.out.println("Completion " + name + " thread.");
    }
}
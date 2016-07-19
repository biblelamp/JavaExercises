public class MyRunnable implements Runnable {
    public void run() {
        go();
    }
    public void go() {

        // using sleep to make our program m,ore predictable
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        doMore();
    }
    public void doMore() {
        System.out.println("top o' the stack");
    }
}
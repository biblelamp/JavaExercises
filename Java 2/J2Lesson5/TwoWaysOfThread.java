/**
 * Java. Level 2. Lesson 5. Multithreading
 * 1st example: two ways to create threads
 *
 * @author Sergey Iryupin
 * @version dated Nov 07, 2017
 */
class TwoWaysOfThread {

    public static void main(String args[]) {
        Thread t1 = new Thread(new MyRunnableClass());
        MyThread t2 = new MyThread();
        t1.start();
        t2.start();
        //t1.run();
        //t2.run();
    }
}

class MyRunnableClass implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++)
            try {
                Thread.sleep(10);
                System.out.print(i);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++)
            try {
                Thread.sleep(10);
                System.out.print(i);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
    }
}
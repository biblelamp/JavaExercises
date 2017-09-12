class TwoWaysOfThread {

    public static void main(String args[]) {
        new Thread(new MyRunnableClass()).start();
        new Thread(new MyRunnableClass()).start();
        //new MyThread().start();
        //new MyThread().start();
    }
}

class MyRunnableClass implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++)
            try {
                Thread.sleep(100);
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
                Thread.sleep(100);
                System.out.print(i);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
    }
}
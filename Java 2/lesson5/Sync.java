class Sync {

    public static void main(String args[]) {
        int a[] = {1, 2, 3, 4, 5};

        MyThread mt1 = new MyThread("Child #1", a);
        MyThread mt2 = new MyThread("Child #2", a);
    }
}

class MyThread implements Runnable {
    Thread thread;
    static SumArray sa = new SumArray();
    int a[];

    // creating new thread
    MyThread(String name, int[] a) {
        thread = new Thread(this, name);
        this.a = a;
        thread.start();
    }

    // starting new thread
    public void run() {
        System.out.println(thread.getName() + " is started.");
        int sum = sa.sumArray(a);
        System.out.println("Final sum for " + thread.getName() + " is " + sum);
        System.out.println(thread.getName() + " is complete.");
    }
}

class SumArray {
    private int sum;

    synchronized int sumArray(int[] nums) { // method is synchronized
        sum = 0;
        for (int i = 0; i <  nums.length; i++) {
            sum += nums[i];
            System.out.println("Current sum for " +
                Thread.currentThread().getName() + " is " + sum);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.println("Main thread is interrupted.");
            }
        }
        return sum;
    }
}
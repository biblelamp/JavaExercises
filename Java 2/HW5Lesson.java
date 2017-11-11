/**
 * Java. Level 2. Lesson 5. Example of homework
 *
 * @author Sergey Iryupin
 * @version 0.2.1 dated Nov 11, 2017
 */
import java.util.Arrays;

class HW5Lesson implements Runnable {

    final int SIZE = 10000000;
    final int HALF_SIZE = SIZE / 2;
    float[] arr = new float[SIZE];
    float[] a1 = new float[HALF_SIZE];
    float[] a2 = new float[HALF_SIZE];
    float testA65;

    public static void main(String[] args) {
        HW5Lesson hw = new HW5Lesson();
        hw.doWithoutThreads();
        hw.doWithThreads();
    }

    void doWithoutThreads() { // without threads
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis(); // note the time
        for (int i = 0; i < SIZE; i++)
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) *
                Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        System.out.println("Way 1 took " + (System.currentTimeMillis() - a) + " mc");
        testA65 = arr[HALF_SIZE + 65];
    }

    void doWithThreads() { // using two threads
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis(); // note the time
        System.arraycopy(arr, 0, a1, 0, HALF_SIZE); // split array into two parts
        System.arraycopy(arr, HALF_SIZE, a2, 0, HALF_SIZE);
        Thread one = new Thread(this, "one"); // create and
        Thread two = new Thread(this, "two"); // run two threads
        one.start();
        two.start();
        try {
            one.join();
            two.join();
        } catch (InterruptedException ex) { } // waiting
        System.arraycopy(a1, 0, arr, 0, HALF_SIZE);
        System.arraycopy(a2, 0, arr, HALF_SIZE, HALF_SIZE);
        System.out.println("Way 2 took " + (System.currentTimeMillis() - a) + " mc");
        System.out.println(testA65 == arr[HALF_SIZE + 65]);
    }

    @Override
    public void run() {
        for (int i = 0; i < HALF_SIZE; i++)
            if (Thread.currentThread().getName() == "one")
                a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) *
                    Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            else
                a2[i] = (float)(a2[i] * Math.sin(0.2f + (i + HALF_SIZE) / 5) *
                    Math.cos(0.2f + (i + HALF_SIZE) / 5) *
                    Math.cos(0.4f + (i + HALF_SIZE) / 2));
    }
}
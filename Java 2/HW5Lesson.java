/**
 * Java. Level 2. Lesson 5. Example of homework
 *
 * @author Sergey Iryupin
 * @version 0.1 dated 11 Apr 2017
 */
import java.util.Arrays;

public class HW5Lesson implements Runnable {

    final int size = 10000000;
    final int h = size / 2;
    float[] arr = new float[size];
    float[] a1 = new float[h];
    float[] a2 = new float[h];

    public static void main(String[] args) {
        HW5Lesson hw = new HW5Lesson();
        hw.doWithoutThreads();
        hw.doWithThreads();
    }

    void doWithoutThreads() { // without threads
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis(); // note the time
        for (int i = 0; i < size; i++)
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) *
                Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        System.out.println("Way 1 took " + (System.currentTimeMillis() - a));
    }

    void doWithThreads() { // using two threads
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis(); // note the time
        System.arraycopy(arr, 0, a1, 0, h); // split array into two parts
        System.arraycopy(arr, h, a2, 0, h);
        Thread one = new Thread(this, "one"); // create and
        Thread two = new Thread(this, "two"); // run two threads
        one.start();
        two.start();
        try {
            one.join();
            two.join();
        } catch (InterruptedException ex) { } // waiting
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println("Way 2 took " + (System.currentTimeMillis() - a));
    }
    
    public void run() {
        for (int i = 0; i < h; i++)
            if (Thread.currentThread().getName() == "one")
                a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) *
                    Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            else
                a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) *
                    Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }
}
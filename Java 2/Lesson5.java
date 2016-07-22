/**
 * Java. Level 2. Lesson 4. Homework
 *  1. Create two methods of array processing
 *  a) Create a large one-dimensional array
 *  b) Fill the array numbers 1
 *  c) Calculate a new value according to the formula for each cell array
 *      arr[i] = arr[i] = (float)(arr[i] * Math.sin(0.2f + i/5) *
 *      Math.cos(0.2f + i/5) * Math.cos(0.4f + i/2));
 *  d) Do this in two ways: simple brute force of array elements
 *  and division of the array into two and using two threads
 *  e) Note the time in the both ways
 *
 * @author Sergey Iryupin
 * @version 22 July 2016
 */
import java.util.Arrays;

public class Lesson5 implements Runnable {

    static final int size = 10000000;
    static final int h = size / 2;
    float[] arr = new float[size];
    float[] a1 = new float[h];
    float[] a2 = new float[h];
    
    public static void main(String[] args) {
        Lesson5 hw = new Lesson5();
        hw.doSimple();
        hw.doWithTthreads();
    }
    
    void doSimple() {
        Arrays.fill(arr, 1);
        // note the time
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Way 1 took " + (System.currentTimeMillis() - a));
    }
    
    void doWithTthreads() {
        Arrays.fill(arr, 1);
        // note the time
        long a = System.currentTimeMillis();
        // to split array into two parts
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        // to run two threads for each part of array
        Lesson5 runner = new Lesson5();
        Thread one = new Thread(runner);
        Thread two  = new Thread(runner);
        one.setName("one");
        two.setName("two");
        one.start();
        two.start();

        // to assemble an array of two parts
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        // show the time
        System.out.println("Way 2 took " + (System.currentTimeMillis() - a));
    }

    public void run() {
        for (int i = 0; i < h; i++) {
            if (Thread.currentThread().getName() == "one") {
                a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            } else {
                a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }
    }
}
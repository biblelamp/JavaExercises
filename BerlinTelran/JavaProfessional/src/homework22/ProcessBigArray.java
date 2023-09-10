package homework22;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Java professional. Homework #22
 *
 * @author Sergey
 * @version 2.2.2023
 */
public class ProcessBigArray {
    static final int SIZE = 10_000_000;

    public static void main(String[] args) {
        float[] array = new float[SIZE];
        float[] array2thread = new float[SIZE];
        float[] array2tws = new float[SIZE];
        long startTime;

        startTime = System.currentTimeMillis();
        processArray(array);
        System.out.println("One thread: " + (System.currentTimeMillis() - startTime) + " ms.");

        startTime = System.currentTimeMillis();
        processArrayTwoThread(array2thread);
        System.out.println("Two thread two parts: " + (System.currentTimeMillis() - startTime) + " ms.");

        startTime = System.currentTimeMillis();
        processArrayTwoThreadWithoutSplitting(array2tws);
        System.out.println("Two thread one part: " + (System.currentTimeMillis() - startTime) + " ms.");

        // check result
        System.out.println(Arrays.equals(array, array2thread));
        System.out.println(Arrays.equals(array, array2tws));
    }

    static void processArray(float[] array) {
        Arrays.fill(array, 1);
        processArray(array, 0);
    }

    static void processArray(float[] array, int shift) {
        IntStream.range(0, array.length)
                .forEach(i -> array[i] = (float)(array[i]
                        * Math.sin(0.2 + (i + shift)/5f) * Math.cos(0.2 + (i + shift)/5f) * Math.cos(0.4 + (i + shift)/2f)));
    }

    static void processArray(float[] array, int start, int length) {
        IntStream.range(start, start + length)
                .forEach(i -> array[i] = (float)(array[i] * Math.sin(0.2 + i/5f) * Math.cos(0.2 + i/5f) * Math.cos(0.4 + i/2f)));
    }

    static void processArrayTwoThread(float[] array) {
        Arrays.fill(array, 1);
        int halfLength = array.length / 2;

        float[] arrayHalf1 = new float[halfLength];
        float[] arrayHalf2 = new float[halfLength];
        System.arraycopy(array, 0, arrayHalf1, 0, halfLength);
        System.arraycopy(array, halfLength, arrayHalf2, 0, halfLength);

        Thread t1 = new Thread(() -> processArray(arrayHalf1));
        Thread t2 = new Thread(() -> processArray(arrayHalf2, halfLength));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.arraycopy(arrayHalf1, 0, array, 0, halfLength);
        System.arraycopy(arrayHalf2, 0, array, halfLength, halfLength);
    }

    static void processArrayTwoThreadWithoutSplitting(float[] array) {
        Arrays.fill(array, 1);
        int halfLength = array.length / 2;

        Thread t1 = new Thread(() -> processArray(array, 0, halfLength));
        Thread t2 = new Thread(() -> processArray(array, halfLength, halfLength));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

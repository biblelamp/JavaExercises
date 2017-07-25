/**
 * Book: Data Structures and Algorithms in Java, by Robert LaFore
 * Chapter 3:
 *  timingSort.java
 *  timing of different types of sorting (my homework)
 *  to compile this code: javac timingSort.java
 *  to run this program:  java TimingSortApp
 */
import java.util.*; 

class Array {
    private long[] a;                   // ref to array a

    public Array(int max) {             // constructor
        a = new long[max];              // create the array
    }

    public void fillRandom() {          // fill array randomly
        for (int i = 0; i < a.length; i++)
            a[i] = (long)(Math.random()*(a.length - 1));
    }

    public void bubbleSort() {          // bubble sort
        for (int out=a.length-1; out > 1; out--) // outer loop (backward)
            for (int in=0; in < out; in++)  // inner loop (forward)
                if (a[in] > a[in+1])    // out of order?
                    swap(in, in+1);     // swap them
    } // end bubbleSort()

    public void selectionSort() {       // selection sort
        for (int out=0; out < a.length-1; out++) {  // outer loop
            int min = out;                      // minimum
            for (int in=out+1; in < a.length; in++) // inner loop
                if (a[in] < a[min])         // if min greater,
                    min = in;               // we have a new min
            swap(out, min);                 // swap them
        }
    } // end selectionSort()

    public void insertionSort() {       // insertion sort
        for (int out=1; out < a.length; out++) { // out is dividing line
            long temp = a[out];              // remove marked item
            int in = out;                    // start shifts at out
            while (in>0 && a[in-1] >= temp) {// until one is smaller,
                a[in] = a[in-1];             // shift item to right
                --in;                        // go left one position
            }
            a[in] = temp;                  // insert marked item
        }
    } // end insertionSort()

    private void swap(int one, int two) {
        long temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }

    @Override
    public String toString() {
        return Arrays.toString(a);
    }
} // end class Array

class TimingSortApp {
    public static void main(String[] args) {
        int maxSize = 50000;            // array size
        Array arr;                      // reference to array
        long t;                         // for saving time

        arr = new Array(maxSize);       // create the array

        // timing bubble sort
        arr.fillRandom();
        t = System.currentTimeMillis(); // note the time
        arr.bubbleSort();
        System.out.println(
            "Bubble sort took " + (System.currentTimeMillis() - t) + " mc");

        // timing selection sort
        arr.fillRandom();
        t = System.currentTimeMillis(); // note the time
        arr.selectionSort();
        System.out.println(
            "Selection sort took " + (System.currentTimeMillis() - t) + " mc");

        // timing insertion sort
        arr.fillRandom();
        t = System.currentTimeMillis(); // note the time
        arr.insertionSort();
        System.out.println(
            "Insertion sort took " + (System.currentTimeMillis() - t) + " mc");
    }
}
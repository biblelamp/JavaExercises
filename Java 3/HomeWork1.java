/**
 * Java. Level 3. Lesson 1. Homework
 * 1. Write a method that changes the places of the two elements of the array.
 * (Array can be of any reference type)
 * 2. Write method which converts an array in ArrayList
 *
 * @author Sergey Iryupin
 * @version 11 Sep 2016
 */
import java.util.*;

public class HomeWork1 {

    public static void main(String[] args) {
        HomeWork1 hw = new HomeWork1();

        // stage 1

        String[] array = {"First", "Second", "Third", "Fourth"};
        hw.changeTwoElements(0, 2, array);
        for (String str : array) System.out.println(str);

        // stage 2

        int[] pi = {3, 1, 4, 1, 5, 9};
        ArrayList<Integer> al = new ArrayList<Integer>();
        hw.convertArrayToArrayList(pi, al);
        for (Integer i : al) System.out.println(i);
    }

    void changeTwoElements(int p1, int p2, String[] array) {
        String tmp = array[p1];
        array[p1] = array[p2];
        array[p2] = tmp;
    }

    void convertArrayToArrayList(int[] array, ArrayList<Integer> al) {
        for (int i : array) al.add(i);
    }

}
package lesson6;

/**
 * Algorithms. Lesson #6. Dynamic array
 *
 * @author Sergey Iryupin
 * @version 4 Aug 2023
 */

public class Lesson6 {
    public static void main(String[] args) {
        DynamicArray da = new DynamicArray(5);
        da.add(8);
        da.add(4);
        da.add(12);
        da.add(3);
        da.add(-1);
        System.out.println(da);
        //da.add(7);
        //da.add(13);
        //System.out.println(da);
        da.add(2, -5);
        System.out.println(da);
        //da.remove(3);
        da.add(8);
        da.add(4);
        da.add(12);
        da.add(3);
        da.add(-1);
        System.out.println(da);
    }
}

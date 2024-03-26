package lesson29.homework;

/**
 * AIT-TR, cohort 42.1, Java Basic, Homework #29
 *
 * @author Sergey Iryupin
 * @version 25-Mar-24
 */
public class HomeWork29 {
    public static void main(String[] args) {
        RubberList rubberList = new RubberList();
        rubberList.add(7);
        rubberList.add(4);
        rubberList.add(12);
        rubberList.add(1);
        rubberList.add(-5);
        System.out.println(rubberList);
        // check contains()
        System.out.println(rubberList.contains(7) + ", " + rubberList.contains(12) + ", " + rubberList.contains(-5));
        System.out.println(rubberList.contains(25));
        // check indexOf()
        System.out.println(rubberList.indexOf(7) + ", " + rubberList.indexOf(12) + ", " + rubberList.indexOf(-5));
        System.out.println(rubberList.indexOf(25));
        // check get() and remove()
        System.out.println(rubberList.get(2));
        rubberList.remove(2);
        System.out.println(rubberList);
        rubberList.remove(0);
        System.out.println(rubberList);
        rubberList.remove(rubberList.size() - 1);
        System.out.println(rubberList);
    }
}

package homework6;

/**
 * Java. Homework #6
 * @author Sergey Iryupin
 * @version 23 May 2023
 */
public class UseRubberArray {
    public static void main(String[] args) {
        RubberArray ra = new RubberArray();
        ra.addAll(3, 2, 1, 0);
        System.out.println(ra);
        ra.add(2, 222);
        System.out.println(ra);
        System.out.println(ra.contains(2));
        System.out.println(ra.contains(8));
        System.out.println(ra.indexOf(2));
        System.out.println(ra.indexOf(8));
        System.out.println(ra.size());
        System.out.println(ra.isEmpty());
        ra.clear();
        System.out.println(ra.isEmpty());
    }
}

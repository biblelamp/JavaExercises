package lesson33;

import java.util.*;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #33
 *
 * @version 3-Apr-24
 */
public class Lesson33 {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(25);
        integers.add(5);
        integers.add(3);
        integers.add(-4);
//        System.out.println(integers);
//        integers.remove(Integer.valueOf(3));
//        System.out.println(integers);

//        Deque<String> strings = new LinkedList<>();
//        strings.push("Java");
//        strings.push("love");
//        strings.push("I");
//        strings.push("!");
//        System.out.println(strings);
//        System.out.println(strings.pop());
//        System.out.println(strings);

//        for (Integer item : integers) {
//            System.out.println(item);
//        }

        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            Integer item = iterator.next();
            System.out.println(item);
        }

        RubberArray<Integer> integerList = new RubberArray<>();
        integerList.add(7);
        integerList.add(12);
        integerList.add(1);
        System.out.println(integerList);
//        for (Integer item : integerList) {
//            System.out.println(item);
//        }
        Iterator<Integer> itr = integerList.iterator();
        while (itr.hasNext()) {
            Integer item = itr.next();
            System.out.println(item);
        }
    }
}

package lesson33.homework;

import java.util.ArrayList;
import java.util.List;

/**
 * AIT-TR, cohort 42.1, Java Basic, hw #33
 *
 * @author Sergey Iryupin
 * @version 5-Apr-24
 */
public class HomeWork33 {
    public static void main(String[] args) {
        List<Integer> listOne = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        List<Integer> listTwo = new ArrayList<>(List.of(3, 4, 5, 6, 7));
        List<Integer> listThree = new ArrayList<>(List.of(6, 7, 8, 9, 0));

        System.out.println(getIntersectionOfLists(listOne, listTwo));
        System.out.println(getIntersectionOfLists(listOne, listThree));
        System.out.println(intersection(listOne, listTwo));
        System.out.println(intersection(listOne, listThree));
    }

    static <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> result = new ArrayList<>(list1);
        result.retainAll(list2); // пересечение
        return result;
    }

    static <T> List<T> getIntersectionOfLists(List<T> listOne, List<T> listTwo) {
        List<T> result = new ArrayList<>();
        for (T item : listOne) {
            if (listTwo.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }
}

package lesson34;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #34
 *
 * @version 5-Apr-24
 */
public class Lesson34 {
    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<>(List.of(5, 3, 6, 2, 4, 1));
        Collections.sort(ints);
        System.out.println(ints);

        List<Person> persons = new ArrayList<>(List.of(
                new Person("Mark", 21),
                new Person("Luke", 36),
                new Person("Luke", 32),
                new Person("John", 30)));
        Collections. sort(persons);
        System.out.println(persons);

        Comparator<Person> ageComparator = (o1, o2) -> (Integer.compare(o1.getAge(), o2.getAge()));
        Comparator<Person> nameAgeComparator = (o1, o2) -> {
            int nameCompare = o1.getName().compareTo(o2.getName());
            if (nameCompare == 0) {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
            return nameCompare;
        };

        Collections.sort(persons, nameAgeComparator);
        System.out.println(persons);
    }
}

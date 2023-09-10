package lesson13;

import java.util.*;

public class Lesson13 {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.addAll(Arrays.asList("hello", "welcome", "java", "bye", "day"));
        System.out.println(stringList);
        Set<String> treeSet = new TreeSet<>(stringList);
        System.out.println(treeSet);

        // impements Comparable<Person>
        List<Person> personList = new ArrayList<>();
        personList.addAll(Arrays.asList(new Person("Mike", 38),
                new Person("John", 35),
                new Person("Luke", 28),
                new Person("Mike", 23)));
        System.out.println(personList);

        Set<Person> personSet = new TreeSet<>(personList);
        System.out.println(personSet);

        // create own comporator(s)
        Comparator<Person> comparatorAge = new ComparatorAge();
        Comparator<Person> comparatorName = new ComparatorName();

        Set<Person> ageSortedSet = new TreeSet<>(comparatorAge);
        ageSortedSet.addAll(personList);
        System.out.println(ageSortedSet);

        personList.sort(comparatorName);
        System.out.println(personList);

        // concat two and more comparators
        Comparator<Person> compareNameAge = new ComparatorName().thenComparing(new ComparatorAge());
        personList.sort(compareNameAge);
        System.out.println(personList);
    }
}

package lesson34.homework;

import lesson17.homework.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * AIT-TR, cohort 42.1, Java Basic, hw #34
 *
 * @author Sergey Iryupin
 * @version 8-Apr-24
 */
public class HomeWork34 {
    public static void main(String[] args) {
        // task #1
        List<Employee> employees = new ArrayList<>(List.of(
                new Employee("name3", "position1", "email1", "phone1", 12500, 35),
                new Employee("name2", "position1", "email1", "phone1", 12500, 36),
                new Employee("name1", "position1", "email1", "phone1", 12500, 25)
        ));

        Comparator<Employee> nameComarator = (e1, e2) -> e1.getName().compareTo(e2.getName());
        Collections.sort(employees, nameComarator);
        employees.forEach(System.out::println);

        Comparator<Employee> ageComparator = (e1, e2) -> Integer.compare(e1.getAge(), e2.getAge());
        Collections.sort(employees, ageComparator);
        employees.forEach(System.out::println);
    }
}

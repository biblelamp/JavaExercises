package lesson17.homework;

/**
 * AIT-TR, cohort 42.1, Java Basic, hw #17
 *
 * @author Sergey Iryupin
 * @version 23-Feb-24
 */
public class HomeWork17 {
    public static void main(String[] args) {
        Employee[] employees = {
                new Employee("a1", "d1", "e1", "t1", 1000, 40),
                new Employee("a2", "d2", "e2", "t2", 1200, 43),
                new Employee("a3", "d3", "e3", "t3", 800, 37)
        };
        for (Employee employee : employees) {
            if (employee.getAge() > 40) {
                System.out.println(employee);
            }
        }
    }
}

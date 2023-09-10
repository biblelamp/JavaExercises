package lesson12;

import java.util.Arrays;
import java.util.List;

public class DbInit {
    public static List<Employee> init() {
        List<Employee> employees = Arrays.asList(
            new Employee("john", Position.BOSS, 1500, 45),
            new Employee("gina", Position.ASSISTANT, 650, 21),
            new Employee("bill", Position.ENGINEER, 1050, 35),
            new Employee("john", Position.ENGINEER, 1100, 35),
            new Employee("mike", Position.ENGINEER, 1150, 33)
        );
        return employees;
    }
}

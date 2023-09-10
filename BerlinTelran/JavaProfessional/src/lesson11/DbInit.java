package lesson11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DbInit {
    public static List<Employee> init() {
        List<Employee> employees = Arrays.asList(
            new Employee("john", "boss", 1500, 45),
            new Employee("gina", "assistent", 650, 21),
            new Employee("bill", "engineer", 1050, 34),
            new Employee("john", "engineer", 1100, 35)
        );
        return employees;
    }
}

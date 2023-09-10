package lesson10;

import java.util.ArrayList;
import java.util.List;

public class DbInit {
    public static List<Employee> init() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("john", "boss", 1500, 45));
        employees.add(new Employee("gina", "assistent", 650, 21));
        return employees;
    }
}

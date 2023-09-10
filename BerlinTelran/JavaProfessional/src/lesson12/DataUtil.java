package lesson12;

import java.util.List;
import java.util.Scanner;

public class DataUtil {
    private static Scanner scanner = new Scanner(System.in);

    public static void print(List<Employee> toPrint) {
        for (Employee employee : toPrint) {
            System.out.println(employee);
        }
    }

    public static String getString(String prompt) {
        System.out.print(prompt);
        String value = scanner.next();
        return value;
    }

    public static int getInt(String prompt) {
        System.out.print(prompt);
        int value = scanner.nextInt();
        return value;
    }

    public static Employee getEmployee(String prompt) {
        System.out.print(prompt);
        String name = scanner.next();
        Position position = getPosition();
        if (position == null) {
            return null;
        }
        int salary = scanner.nextInt();
        int age = scanner.nextInt();
        return new Employee(name, position, salary, age);
    }

    public static Employee getEmployeePart(String prompt) {
        System.out.print(prompt);
        Position position = getPosition();
        if (position == null) {
            return null;
        }
        int salary = scanner.nextInt();
        int age = scanner.nextInt();
        return new Employee(null, position, salary, age);
    }

    private static Position getPosition() {
        String value = scanner.next();
        try {
            Position position = Position.valueOf(value.toUpperCase());
            return position;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Position '" + value + "' not found");
            return null;
        }

    }
}

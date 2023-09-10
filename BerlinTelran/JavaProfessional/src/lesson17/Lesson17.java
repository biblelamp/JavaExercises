package lesson17;

import lesson12.DbInit;
import lesson12.Employee;
import lesson12.Position;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lesson17 {
    public static void main(String[] args) {
        List<Employee> employees = DbInit.init();

        // фильтрация списка
        List<Employee> engineers = employees.stream()
                .filter(e -> e.getPosition() == Position.ASSISTANT)
                .collect(Collectors.toList());
        System.out.println(engineers);

        // преобразование списка
        Set<Position> positions = employees.stream()
                .map(e -> e.getPosition())
                //.distinct()
                .collect(Collectors.toSet());
        System.out.println(positions);

        // преобразование списка в map
        Map<Integer, Employee> employeeMap = employees.stream()
                //.collect(Collectors.toMap(e -> e.getId(), e -> e));
                .collect(Collectors.toMap(Employee::getId, Function.identity()));
        System.out.println(employeeMap);

        // преобразование в сложную map
        Map<Position, List<Employee>> positionMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getPosition));
        System.out.println(positionMap);

        // поиск минимума
        String min = Stream.of("f10", "f15", "f2", "f4").min(String::compareTo).get();
        System.out.println(min);
    }

    static void firstMeeting() {
        List<Employee> employees = DbInit.init();

        long count = 0;
        for (Employee employee : employees) {
            if (employee.getPosition() == Position.ENGINEER) {
                count++;
            }
        }
        System.out.println(count);

        count = 0;
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getPosition() == Position.ENGINEER) {
                count++;
            }
        }
        System.out.println(count);

        count = employees.stream()
                .filter(employee -> employee.getPosition() == Position.ENGINEER)
                .count();
        System.out.println(count);

        int sum = IntStream.of(1, 2, 3, 4).reduce(0, (a, b) -> a + b);
        System.out.println(sum);
    }
}

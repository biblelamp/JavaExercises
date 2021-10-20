/**
 * Java 1. Homework 6
 *
 * @author Sergey Iryupin
 * @version 21.10.2021
 */
class Lesson5HomeWork {

    public static void main(String[] args) {
        Employee[] employees = {
            new Employee("Ivanov Ivan", "Engineer",
                "iivanov@mail.com", "2312312", 30000, 30),
            new Employee("Petrov Petr", "Lead Engineer",
                "ppenrov@mail.com", "2365113", 42000, 39),
            new Employee("Vasilev Vasil", "Head of Department",
                "vvasilev@mail.com", "2365001", 55000, 55),
            new Employee("Sidorov Sidor", "Assistant",
                "ssidorov@mail.com", "2365223", 25000, 42),
            new Employee("Sergeev Sergey", "Trainee",
                "ssergeev@mail.com", "2365113", 15000, 25)
        };

        for (Employee employee : employees) {
            if (employee.getAge() > 40) {
                System.out.println(employee);
            }
        }
    }
}

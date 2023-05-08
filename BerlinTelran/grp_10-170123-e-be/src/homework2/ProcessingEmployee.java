package homework2;

/**
 * Java Pro. Homework #2
 * @author Sergey Iryupin
 * @version 8 May 2023
 */
public class ProcessingEmployee {

    public static void main(String[] args) {
        Employee[] employees = {
                new Employee("Ivanov Ivan", Position.ENGINEER, "iivanov@mail.com", "2312312", 30000, 30),
                new Employee("Petrov Petr", Position.ENGINEER, "ppenrov@mail.com", "2365113", 42000, 39),
                new Employee("Vasilev Vasil", Position.HEAD_OF_DEPARTMENT, "vvasilev@mail.com", "2365001", 55000, 55),
                new Employee("Sidorov Sidor", Position.ASSISTENT, "ssidorov@mail.com", "2365223", 25000, 42),
                new Employee("Sergeev Sergey", Position.TRAINEE, "ssergeev@mail.com", "2365113", 15000, 25)
        };

        for (Employee employee : employees) {
            if (employee.getAge() > 40) {
                System.out.println(employee);
            }
        }
    }
}

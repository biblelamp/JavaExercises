/**
 * Java. Level 1. Lesson 5. Example of homework
 *
 * @author Sergey Iryupin
 * @version dated 04 Mar 2017
 */
public class HW5Lesson {

    public static void main(String[] args) {

        Employee[] arrEmployee = new Employee[5];
        arrEmployee[0] = new Employee("Ivanov Ivan", "Engineer",
            "iivanov@mail.com", "2312312", 30000, 30);
        arrEmployee[1] = new Employee("Petrov Petr", "Lead Engineer",
            "ppenrov@mail.com", "2365113", 42000, 39);
        arrEmployee[2] = new Employee("Vasilev Vasil", "Head of Department",
            "vvasilev@mail.com", "2365001", 55000, 55);
        arrEmployee[3] = new Employee("Sidorov Sidor", "Assistant",
            "ssidorov@mail.com", "2365223", 25000, 42);
        arrEmployee[4] = new Employee("Sergeev Sergey", "Trainee",
            "ssergeev@mail.com", "2365113", 15000, 25);

        for (Employee employee : arrEmployee)
            if (employee.getAge() > 40)
                System.out.println(employee);
    }
}

/**
 * Class Employee
 */
class Employee {
    private String name;
    private String position;
    private String email;
    private String phone;
    private int salary;
    private int age;

    public Employee(String name, String position, String email, String phone, int salary, int age) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return (name +
            "\n| Position: " + position +
            "\n| Email: " + email +
            "\n| Phone: " + phone +
            "\n| Salary: " + salary +
            "\n| Age: " + age);
    }
}
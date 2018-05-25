/**
 * Java. Level 1. Lesson 5. Example of homework
 *
 * @author Sergey Iryupin
 * @version dated May 25, 2018
 * @link https://github.com/<your_nik> || null
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

class HW5Lesson {

    public static void main(String[] args) {
        // define and fill array of employees
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

        // show all employees older than 40 year
        for (Employee employee : employees)
            if (employee.getAge() > 40)
                System.out.println(employee);

        // write array to the CSV file
        try (FileWriter file = new FileWriter("employee.txt")) {
            for (Employee employee : employees)
                file.write(employee.getDataInCSV() + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // read all lines from file
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get("employee.txt"),
                StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // parsing CSV lines
        int index = 0;
        employees = new Employee[lines.size()];
        for (String line : lines) {
            String[] field = line.split(",");
            employees[index] = new Employee(
                field[0], field[1], field[2], field[3],
                Integer.parseInt(field[4]), Integer.parseInt(field[5]));
            index++;
        }
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

    public Employee(String name, String position, String email, String phone,
            int salary, int age) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public int getAge() { // getter for age
        return age;
    }

    public String getDataInCSV() {
        return name + "," + position + "," + email + "," + phone + "," +
            salary + "," + age;
    }

    @Override
    public String toString() { // overrided method toString()
        return (name +
            "\n| Position: " + position +
            "\n| Email: " + email +
            "\n| Phone: " + phone +
            "\n| Salary: " + salary +
            "\n| Age: " + age);
    }
}
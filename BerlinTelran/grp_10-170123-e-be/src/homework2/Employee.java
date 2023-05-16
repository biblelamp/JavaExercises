package homework2;

public class Employee {
    private String name;
    private Position position;
    private String email;
    private String phone;
    private int salary;
    private int age;

    public Employee(String name, Position position, String email, String phone, int salary, int age) {
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

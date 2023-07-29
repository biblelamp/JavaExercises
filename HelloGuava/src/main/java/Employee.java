public class Employee {
    private final int id;
    private final String department;
    private final String name;

    public Employee(int id, String department, String name) {
        this.id = id;
        this.department = department;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", department='" + department + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

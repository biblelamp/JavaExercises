package lesson24.homework;

public class Student {
    private Integer id;
    private String name;
    private String groupName;

    public Student(Integer id, String name, String groupName) {
        this.id = id;
        this.name = name;
        this.groupName = groupName;
    }

    public Student(String name, String groupName) {
        this.name = name;
        this.groupName = groupName;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}

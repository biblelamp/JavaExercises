public class Student {
    private String name;
    private int age;
    private int id;
    private String language;

    @Override
    public String toString() {
        return "Student {" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                ", language='" + language + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
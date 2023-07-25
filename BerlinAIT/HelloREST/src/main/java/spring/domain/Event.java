package spring.domain;

public class Event {
    private Integer id;
    private String name;
    private String city;
    private static int count = 0;

    public Event() {
    }

    public Event(String name, String city) {
        this.id = ++count;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

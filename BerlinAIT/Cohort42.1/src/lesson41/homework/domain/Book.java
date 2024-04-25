package lesson41.homework.domain;

public class Book {
    private int id;
    private String name;
    private static int idCounter = 0;

    public Book(String name) {
        this.id = ++idCounter;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{id=" + id + ", name='" + name + "'}";
    }
}

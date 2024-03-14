package lesson23.homework;

public class Product {
    private int id;
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" + name + ", " + price + '}';
    }
}

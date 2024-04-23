package pizza.domain;

/**
 * Pizza class
 *
 * @author Sergey Iryupin
 * @version 15-Apr-24
 */
public class Pizza {
    private int id;
    private String name;
    private String composition;
    private int price;
    private static int idCounter = 0;

    public Pizza(String name, String composition, int price) {
        this.id = ++idCounter;
        this.name = name;
        this.composition = composition;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void update(String name, String composition, int price) {
        this.name = name;
        this.composition = composition;
        this.price = price;
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", name=" + name +
                " (" + composition + ")" +
                ", price=" + price +
                '}';
    }
}

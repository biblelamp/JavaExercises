package lesson44;

/**
 * Extra (additional) component to pizza
 *
 * @author Sergey Iryupin
 * @version 15-Apr-24
 */
public class ExtСomponent {
    private int id;
    private String name;
    private int price;
    private static int idCounter = 0;

    public ExtСomponent(String name, int price) {
        this.id = ++idCounter;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void update(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", name=" + name +
                ", price=" + price +
                '}';
    }
}

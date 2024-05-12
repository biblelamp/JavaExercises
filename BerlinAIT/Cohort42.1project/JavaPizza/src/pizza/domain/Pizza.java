package pizza.domain;

/**
 * Pizza class
 *
 * @author Sergey Iryupin
 * @version 12-May-24
 */
public class Pizza {
    private Integer id;
    private String name;
    private String composition;
    private int price;

    public Pizza(String name, String composition, int price) {
        this.id = null;
        this.name = name;
        this.composition = composition;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

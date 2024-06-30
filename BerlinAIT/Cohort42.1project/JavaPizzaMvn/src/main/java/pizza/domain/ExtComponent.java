package pizza.domain;

/**
 * Extra (additional) component to pizza
 *
 * @author Sergey Iryupin
 * @version 30-Jun-24
 */
public class ExtComponent {
    private Integer id;
    private String name;
    private int price;

    public ExtComponent(String name, int price) {
        this.id = null;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
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

package pizza.domain;

/**
 * Extra (additional) component to pizza
 *
 * @author Sergey Iryupin
 * @version 15-Apr-24
 */
public class ExtСomponent {
    private Integer id;
    private String name;
    private int price;
    private static int idCounter = 0;

    public ExtСomponent(String name, int price) {
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

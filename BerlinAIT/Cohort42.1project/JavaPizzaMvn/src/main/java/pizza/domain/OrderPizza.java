package pizza.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Pizza class to order: standard pizza + extras
 *
 * @author Sergey Iryupin
 * @version 12-May-24
 */
public class OrderPizza {
    private Integer id;
    private Pizza pizza;
    private List<ExtComponent> components;

    public OrderPizza(Pizza pizza) {
        this.id = null;
        this.pizza = pizza;
        this.components = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public List<ExtComponent> getComponents() {
        return components;
    }

    @Override
    public String toString() {
        return "OrderPizza{" +
                "id=" + id +
                ", pizza=" + pizza +
                ",\n components=" + components +
                '}';
    }
}

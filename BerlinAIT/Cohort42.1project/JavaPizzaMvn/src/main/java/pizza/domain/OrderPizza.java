package pizza.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Pizza class to order: standard pizza + extras
 *
 * @author Sergey Iryupin
 * @version 15-Apr-24
 */
public class OrderPizza {
    private int id;
    private Pizza pizza;
    private List<ExtСomponent> components;
    private static int idCounter = 0;

    public OrderPizza(Pizza pizza) {
        this.id = ++idCounter;
        this.pizza = pizza;
    }

    public int getId() {
        return id;
    }

    public void addComponent(ExtСomponent component) {
        if (components == null) {
            components = new ArrayList<>();
        }
        components.add(component);
    }
    public boolean deleteComponent(ExtСomponent component) {
        if (components != null) {
            return components.remove(component);
        }
        return false;
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

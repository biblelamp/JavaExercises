package pizza.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Pizza class to order: standard pizza + extras
 *
 * @author Sergey Iryupin
 * @version 15-Apr-24
 */
public class OrderPizza {
    private Pizza pizza;
    private List<ExtСomponent> components;

    public OrderPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public void addComponent(ExtСomponent component) {
        if (components == null) {
            components = new ArrayList<>();
        }
        components.add(component);
    }
}

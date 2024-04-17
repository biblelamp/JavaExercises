package pizza.service;

import pizza.base.Pizza;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Pizza service class
 * Encapsulates the list of pizzas & CRUD operations with them
 *
 * @author Sergey Iryupin
 * @version 15-Apr-24
 */
public class PizzaService {
    private List<Pizza> pizzas;
    private Map<Integer, Pizza> pizzaMap;

    public PizzaService() {
        pizzas = new ArrayList<>();
        pizzaMap = new HashMap<>();
    }

    public void init() {
        pizzas.addAll(List.of(
                new Pizza("Margherita", "tomato base, mozzarella, basil", 174),
                new Pizza("Capriciosa", "tomato base, mozzarella, ham, champignons, olives", 190),
                new Pizza("Hawai", "tomato base, mozzarella, ham, pineapple", 190),
                new Pizza("Salami", "tomato base, mozzarella, salami, grana padano", 194),
                new Pizza("Quattro Formaggi", "cream base, mozzarella, grana padano, gorgonzola, smoked cheese", 204)
        ));
        pizzaMap = pizzas.stream().collect(Collectors.toMap(Pizza::getId, p -> p));
    }

    public void add(String name, String composition, int price) {
        Pizza pizza = new Pizza(name, composition, price);
        pizzas.add(pizza);
        pizzaMap.put(pizza.getId(), pizza);
    }

    public boolean update(int id, String name, String composition, int price) {
        Pizza updPizza = pizzaMap.get(id);
        if (updPizza != null) {
            updPizza.update(name, composition, price);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        Pizza delPizza = pizzaMap.get(id);
        if (delPizza != null) {
            pizzas.remove(delPizza);
            pizzaMap.remove(id);
            return true;
        }
        return false;
    }

    public void print() {
        pizzas.forEach(System.out::println);
    }
}

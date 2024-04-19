package pizza.repository;

import pizza.data.Pizza;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Pizza repository
 * Implementation of access methods to the Pizza data source
 *
 * @author Sergey Iryupin
 * @version 18-Apr-24
 */
public class PizzaRepository {
    private Map<Integer, Pizza> pizzaMap;

    public PizzaRepository() {
        pizzaMap = new HashMap<>();
    }

    public void put(Pizza pizza) {
        pizzaMap.put(pizza.getId(), pizza);
    }

    public Pizza get(int id) {
        return pizzaMap.get(id);
    }

    public void remove(int id) {
        pizzaMap.remove(id);
    }

    public Collection<Pizza> values() {
        return pizzaMap.values();
    }

    public void init() {
        List<Pizza> pizzas = new ArrayList<>(List.of(
                new Pizza("Margherita", "tomato base, mozzarella, basil", 174),
                new Pizza("Capriciosa", "tomato base, mozzarella, ham, champignons, olives", 190),
                new Pizza("Hawai", "tomato base, mozzarella, ham, pineapple", 190),
                new Pizza("Salami", "tomato base, mozzarella, salami, grana padano", 194),
                new Pizza("Quattro Formaggi", "cream base, mozzarella, grana padano, gorgonzola, smoked cheese", 204)
        ));
        //pizzaMap = pizzas.stream().collect(Collectors.toMap(Pizza::getId, p -> p));
        pizzas.forEach(pizza -> put(pizza));
    }
}

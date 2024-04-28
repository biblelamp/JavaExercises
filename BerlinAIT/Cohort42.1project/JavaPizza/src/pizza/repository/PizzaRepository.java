package pizza.repository;

import pizza.domain.Pizza;

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
 * @version 19-Apr-24
 */
public class PizzaRepository implements CrudRepository<Integer, Pizza> {
    private Map<Integer, Pizza> pizzaMap;

    public PizzaRepository() {
        pizzaMap = new HashMap<>();
    }

    @Override
    public void save(Pizza pizza) {
        pizzaMap.put(pizza.getId(), pizza);
    }

    @Override
    public Pizza findById(Integer id) {
        return pizzaMap.get(id);
    }

    @Override
    public void remove(Integer id) {
        pizzaMap.remove(id);
    }

    @Override
    public Collection<Pizza> findAll() {
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
        pizzas.forEach(pizza -> save(pizza));
    }
}

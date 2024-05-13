package pizza.repository;

import pizza.domain.Pizza;

import java.util.*;

/**
 * Pizza repository
 * Implementation of access methods to the Pizza data source
 *
 * @author Sergey Iryupin
 * @version 12-May-24
 */
public class PizzaRepository implements CrudRepository<Integer, Pizza> {
    private Map<Integer, Pizza> pizzaMap;

    public PizzaRepository() {
        pizzaMap = new HashMap<>();
        init();
    }

    @Override
    public void save(Pizza pizza) {
        if (pizza.getId() == null) {
            int pizzaId = 0;
            for (Integer id : pizzaMap.keySet()) {
                if (pizzaId < id) {
                    pizzaId = id;
                }
            }
            pizza.setId(pizzaId + 1);
        }
        pizzaMap.put(pizza.getId(), pizza);
    }

    @Override
    public Pizza findById(Integer id) {
        return pizzaMap.get(id);
    }

    @Override
    public void deleteById(Integer id) {
        pizzaMap.remove(id);
    }

    @Override
    public void deleteAll() {
        throw new NullPointerException("Method not implemented");
    }

    @Override
    public Collection<Pizza> findAll() {
        return pizzaMap.values();
    }

    private void init() {
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

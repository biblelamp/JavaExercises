package pizza.service;

import pizza.domain.Pizza;
import pizza.repository.PizzaRepository;

/**
 * Pizza service class
 * Encapsulates the list of pizzas & CRUD operations with them
 *
 * @author Sergey Iryupin
 * @version 18-Apr-24
 */
public class PizzaService {
    private PizzaRepository repository;

    public PizzaService(PizzaRepository repository) {
        this.repository = repository;
    }

    public void add(String name, String composition, int price) {
        Pizza pizza = new Pizza(name, composition, price);
        repository.put(pizza);
    }

    public Pizza get(int id) {
        return repository.get(id);
    }

    public boolean update(int id, String name, String composition, int price) {
        Pizza updPizza = repository.get(id);
        if (updPizza != null) {
            updPizza.update(name, composition, price);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        Pizza delPizza = repository.get(id);
        if (delPizza != null) {
            repository.remove(id);
            return true;
        }
        return false;
    }

    public void print() {
        repository.values().forEach(System.out::println);
    }
}

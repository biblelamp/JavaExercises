package pizza.service;

import pizza.domain.Pizza;
import pizza.repository.CrudRepository;
import pizza.repository.PizzaRepository;

/**
 * Pizza service class
 * Encapsulates the list of pizzas & CRUD operations with them
 *
 * @author Sergey Iryupin
 * @version 18-Apr-24
 */
public class PizzaService {
    private CrudRepository<Integer, Pizza> repository;

    public PizzaService(CrudRepository<Integer, Pizza> repository) {
        this.repository = repository;
    }

    public void add(String name, String composition, int price) {
        Pizza pizza = new Pizza(null, name, composition, price);
        repository.save(pizza);
    }

    public Pizza findById(int id) {
        return repository.findById(id);
    }

    public boolean update(int id, String name, String composition, int price) {
        Pizza updPizza = repository.findById(id);
        if (updPizza != null) {
            updPizza.update(name, composition, price);
            repository.save(updPizza);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        Pizza delPizza = repository.findById(id);
        if (delPizza != null) {
            repository.remove(id);
            return true;
        }
        return false;
    }

    public void print() {
        repository.findAll().forEach(System.out::println);
    }
}

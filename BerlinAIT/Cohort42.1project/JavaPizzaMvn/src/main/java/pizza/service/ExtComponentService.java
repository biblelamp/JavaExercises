package pizza.service;

import pizza.domain.ExtComponent;
import pizza.repository.CrudRepository;

/**
 * Extras (additional) components service class
 * Encapsulates the list of extra components & CRUD operations with them
 *
 * @author Sergey Iryupin
 * @version 19-Apr-24
 */
public class ExtComponentService {
    private CrudRepository<Integer, ExtComponent> repository;

    public ExtComponentService(CrudRepository<Integer, ExtComponent> repository) {
        this.repository = repository;
    }

    public ExtComponent get(int id) {
        return repository.findById(id);
    }

    public void add(String name, int price) {
        ExtComponent component = new ExtComponent(name, price);
        repository.save(component);
    }

    public boolean update(int id, String name, int price) {
        ExtComponent updComponent = repository.findById(id);
        if (updComponent != null) {
            updComponent.update(name, price);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        ExtComponent delComponent = repository.findById(id);
        if (delComponent != null) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public void print() {
        repository.findAll().forEach(System.out::println);
    }
}

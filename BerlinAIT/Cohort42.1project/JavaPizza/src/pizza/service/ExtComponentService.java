package pizza.service;

import pizza.domain.ExtСomponent;
import pizza.repository.ExtComponentRepository;

/**
 * Extras (additional) components service class
 * Encapsulates the list of extra components & CRUD operations with them
 *
 * @author Sergey Iryupin
 * @version 19-Apr-24
 */
public class ExtComponentService {
    private ExtComponentRepository repository;

    public ExtComponentService(ExtComponentRepository repository) {
        this.repository = repository;
    }

    public ExtСomponent get(int id) {
        return repository.findById(id);
    }

    public void add(String name, int price) {
        ExtСomponent component = new ExtСomponent(name, price);
        repository.save(component);
    }

    public boolean update(int id, String name, int price) {
        ExtСomponent updComponent = repository.findById(id);
        if (updComponent != null) {
            updComponent.update(name, price);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        ExtСomponent delComponent = repository.findById(id);
        if (delComponent != null) {
            repository.remove(id);
            return true;
        }
        return false;
    }

    public void print() {
        repository.findAll().forEach(System.out::println);
    }
}

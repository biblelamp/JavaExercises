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
        return repository.get(id);
    }

    public void add(String name, int price) {
        ExtСomponent component = new ExtСomponent(name, price);
        repository.put(component);
    }

    public boolean update(int id, String name, int price) {
        ExtСomponent updComponent = repository.get(id);
        if (updComponent != null) {
            updComponent.update(name, price);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        ExtСomponent delComponent = repository.get(id);
        if (delComponent != null) {
            repository.remove(id);
            return true;
        }
        return false;
    }

    public void print() {
        repository.values().forEach(System.out::println);
    }
}

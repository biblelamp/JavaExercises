package pizza.service;

import pizza.domain.Customer;
import pizza.repository.CrudRepository;

/**
 * Customer service class
 * Encapsulates the list of customets & CRUD operations with them
 *
 * @author Sergey Iryupin
 * @version 17-Apr-24
 */
public class CustomerService {
    private CrudRepository<Integer, Customer> repository;

    public CustomerService(CrudRepository<Integer, Customer> repository) {
        this.repository = repository;
    }

    public void add(String name, String address, String phone) {
        Customer customer = new Customer(name, address, phone);
        repository.save(customer);
    }

    public Customer get(int id) {
        return repository.findById(id);
    }

    public boolean update(int id, String name, String address, String phone) {
        Customer updCustomer = repository.findById(id);
        if (updCustomer != null) {
            updCustomer.update(name, address, phone);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        Customer delCustomer = repository.findById(id);
        if (delCustomer != null) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public void print() {
        repository.findAll().forEach(System.out::println);
    }
}

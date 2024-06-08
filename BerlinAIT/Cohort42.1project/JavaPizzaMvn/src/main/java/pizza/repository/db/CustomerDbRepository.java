package pizza.repository.db;

import pizza.domain.Customer;
import pizza.repository.CrudRepository;

import java.util.Collection;

public class CustomerDbRepository implements CrudRepository<Integer, Customer> {
    @Override
    public Customer save(Customer value) {
        return null;
    }

    @Override
    public Customer findById(Integer id) {
        return null;
    }

    @Override
    public Collection<Customer> findAll() {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void deleteAll() {

    }
}

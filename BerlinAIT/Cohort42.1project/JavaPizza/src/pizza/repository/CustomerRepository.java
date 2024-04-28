package pizza.repository;

import pizza.domain.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Customer repository
 * Implementation of access methods to the Customer data source
 *
 * @author Sergey Iryupin
 * @version 19-Apr-24
 */
public class CustomerRepository implements CrudRepository<Integer, Customer> {
    private Map<Integer, Customer> customerMap;

    public CustomerRepository() {
        customerMap = new HashMap<>();
    }

    @Override
    public void save(Customer value) {
        customerMap.put(value.getId(), value);
    }

    @Override
    public Customer findById(Integer key) {
        return customerMap.get(key);
    }

    @Override
    public void remove(Integer key) {
        customerMap.remove(key);
    }

    @Override
    public Collection<Customer> findAll() {
        return customerMap.values();
    }

    public void init() {
        Customer customer = new Customer("Anonymous", null, null);
        customerMap.put(customer.getId(), customer);
    }
}

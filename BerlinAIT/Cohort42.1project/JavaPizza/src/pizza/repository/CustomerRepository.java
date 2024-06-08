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
    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            int orderId = 0;
            for (Integer id : customerMap.keySet()) {
                if (orderId < id) {
                    orderId = id;
                }
            }
            customer.setId(orderId + 1);
        }
        customerMap.put(customer.getId(), customer);
        return customer;
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

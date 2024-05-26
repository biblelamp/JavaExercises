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
        init();
    }

    @Override
    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            int customerId = 0;
            for (Integer id : customerMap.keySet()) {
                if (customerId < id) {
                    customerId = id;
                }
            }
            customer.setId(customerId + 1);
        }
        customerMap.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public Customer findById(Integer id) {
        return customerMap.get(id);
    }

    @Override
    public void deleteById(Integer id) {
        customerMap.remove(id);
    }

    @Override
    public Collection<Customer> findAll() {
        return customerMap.values();
    }

    @Override
    public void deleteAll() {
        throw new NullPointerException("Method not implemented");
    }

    private void init() {
        Customer customer = new Customer("Anonymous", null, null);
        save(customer);
    }
}

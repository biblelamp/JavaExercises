package pizza.service;

import pizza.data.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Customer service class
 * Encapsulates the list of customets & CRUD operations with them
 *
 * @author Sergey Iryupin
 * @version 17-Apr-24
 */
public class CustomerService {
    private Map<Integer, Customer> customerMap;

    public CustomerService() {
        customerMap = new HashMap<>();
    }

    public void init() {
        Customer customer = new Customer("Anonymous", null, null);
        customerMap.put(customer.getId(), customer);
    }

    public void add(String name, String address, String phone) {
        Customer customer = new Customer(name, address, phone);
        customerMap.put(customer.getId(), customer);
    }

    public boolean update(int id, String name, String address, String phone) {
        Customer updCustomer = customerMap.get(id);
        if (updCustomer != null) {
            updCustomer.update(name, address, phone);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        Customer delCustomer = customerMap.get(id);
        if (delCustomer != null) {
            customerMap.remove(id);
            return true;
        }
        return false;
    }

    public void print() {
        customerMap.values().forEach(System.out::println);
    }
}

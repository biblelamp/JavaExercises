package pizza.service;

import pizza.base.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Order service class
 * Encapsulates the list of orders & CRUD operations with them
 *
 * @author Sergey Iryupin
 * @version 15-Apr-24
 */
public class OrderService {
    private List<Order> orders;
    private Map<Integer, Order> orderMap;

    public OrderService() {
        orders = new ArrayList<>();
        orderMap = new HashMap<>();
    }

    public void create() {
        // TODO
    }

    public boolean delete(int id) {
        // TODO
        return false;
    }

    public void print() {
        // TODO
    }
}

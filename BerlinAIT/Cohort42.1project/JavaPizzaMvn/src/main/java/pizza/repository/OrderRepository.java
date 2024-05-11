package pizza.repository;

import pizza.domain.Order;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Order repository
 * Implementation of access methods to the Order data source
 *
 * @author Sergey Iryupin
 * @version 20-Apr-24
 */
public class OrderRepository implements CrudRepository<Integer, Order> {
    private Map<Integer, Order> orderMap;

    public OrderRepository() {
        orderMap = new HashMap<>();
    }

    @Override
    public void save(Order order) {
        orderMap.put(order.getId(), order);
    }

    @Override
    public Order findById(Integer key) {
        return orderMap.get(key);
    }

    @Override
    public void remove(Integer key) {
        orderMap.remove(key);
    }

    @Override
    public Collection<Order> findAll() {
        return orderMap.values();
    }

    @Override
    public void initTable() {
        // TODO
    }
}

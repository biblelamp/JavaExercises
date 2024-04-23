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
    public void put(Order order) {
        orderMap.put(order.getId(), order);
    }

    @Override
    public Order get(Integer key) {
        return orderMap.get(key);
    }

    @Override
    public void remove(Integer key) {
        orderMap.remove(key);
    }

    @Override
    public Collection<Order> values() {
        return orderMap.values();
    }
}

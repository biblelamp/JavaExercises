package pizza.repository;

import pizza.domain.OrderPizza;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OrderPizzaRepozitory implements CrudRepository<Integer, OrderPizza> {
    private Map<Integer, OrderPizza> orderPizzaMap;

    public OrderPizzaRepozitory() {
        this.orderPizzaMap = new HashMap<>();
    }

    @Override
    public OrderPizza save(OrderPizza orderPizza) {
        if (orderPizza.getId() == null) {
            int orderPizzaId = 0;
            for(Integer id : orderPizzaMap.keySet()) {
                if (orderPizzaId < id) {
                    orderPizzaId = id;
                }
            }
            orderPizza.setId(orderPizzaId + 1);
        }
        orderPizzaMap.put(orderPizza.getId(), orderPizza);
        return orderPizza;
    }

    @Override
    public Collection<OrderPizza> findAll() {
        return orderPizzaMap.values();
    }

    @Override
    public OrderPizza findById(Integer id) {
        return orderPizzaMap.get(id);
    }

    @Override
    public void deleteById(Integer id) {
        orderPizzaMap.remove(id);
    }

    @Override
    public void deleteAll() {
        throw new NullPointerException("Method not implemented");
    }
}

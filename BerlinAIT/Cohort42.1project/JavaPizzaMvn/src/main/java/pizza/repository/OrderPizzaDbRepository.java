package pizza.repository;

import pizza.domain.OrderPizza;

import java.util.Collection;

public class OrderPizzaDbRepository implements CrudRepository<Integer, OrderPizza> {

    @Override
    public void save(OrderPizza value) {

    }

    @Override
    public OrderPizza findById(Integer id) {
        return null;
    }

    @Override
    public Collection<OrderPizza> findAll() {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void deleteAll() {
        throw new NullPointerException("Method not implemented");
    }
}

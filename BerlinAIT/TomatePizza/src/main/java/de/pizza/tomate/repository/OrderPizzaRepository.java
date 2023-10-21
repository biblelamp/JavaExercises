package de.pizza.tomate.repository;

import de.pizza.tomate.domain.Order;
import de.pizza.tomate.domain.OrderPizza;
import de.pizza.tomate.domain.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderPizzaRepository extends JpaRepository<OrderPizza, Integer> {
    OrderPizza findByPizza(Pizza pizza);

    List<OrderPizza> findByOrder(Order order);

    OrderPizza findByOrderAndPizza(Order order, Pizza pizza);

    @Modifying
    int deleteByOrder(Order order);
}

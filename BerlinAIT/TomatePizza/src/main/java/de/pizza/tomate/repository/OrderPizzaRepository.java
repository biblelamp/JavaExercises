package de.pizza.tomate.repository;

import de.pizza.tomate.domain.OrderPizza;
import de.pizza.tomate.domain.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPizzaRepository extends JpaRepository<OrderPizza, Integer> {
    OrderPizza findByPizza(Pizza pizza);
}

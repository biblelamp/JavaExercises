package de.pizza.tomate.repository;

import de.pizza.tomate.domain.Order;
import de.pizza.tomate.domain.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    @Modifying
    int deleteByIdIn(List<Integer> pizzaIds);
}

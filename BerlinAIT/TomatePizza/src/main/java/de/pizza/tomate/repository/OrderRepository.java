package de.pizza.tomate.repository;

import de.pizza.tomate.domain.Order;
import de.pizza.tomate.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser(User user);
}

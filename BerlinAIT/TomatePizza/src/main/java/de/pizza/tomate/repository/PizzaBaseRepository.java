package de.pizza.tomate.repository;

import de.pizza.tomate.domain.PizzaBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaBaseRepository extends JpaRepository<PizzaBase, Integer> {
}

package de.pizza.tomate.repository;

import de.pizza.tomate.domain.PizzaSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaSizeRepository extends JpaRepository<PizzaSize, Integer> {
}

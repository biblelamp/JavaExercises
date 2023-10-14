package de.pizza.tomate.repository;

import de.pizza.tomate.domain.PizzaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaTypeRepository extends JpaRepository<PizzaType, Integer> {
}

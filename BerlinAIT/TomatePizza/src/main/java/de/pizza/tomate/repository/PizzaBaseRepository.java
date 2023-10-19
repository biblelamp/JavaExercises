package de.pizza.tomate.repository;

import de.pizza.tomate.domain.PizzaBase;
import de.pizza.tomate.domain.PizzaSize;
import de.pizza.tomate.domain.PizzaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaBaseRepository extends JpaRepository<PizzaBase, Integer> {
    @Query("SELECT p FROM PizzaBase p WHERE LOWER(p.pizzaType.name) LIKE :find OR LOWER(p.pizzaType.description) LIKE :find")
    List<PizzaBase> findByNameOrDesc(@Param("find") String findString);

    @Query("SELECT COUNT(p) FROM PizzaBase p WHERE p.pizzaSize = :pizzaSize")
    int countByPizzaSize(@Param("pizzaSize") PizzaSize pizzaSize);

    int countByPizzaType(PizzaType pizzaType);
}

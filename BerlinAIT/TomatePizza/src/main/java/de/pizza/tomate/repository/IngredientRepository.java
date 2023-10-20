package de.pizza.tomate.repository;

import de.pizza.tomate.domain.Ingredient;
import de.pizza.tomate.domain.PizzaSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    int countByPizzaSize(PizzaSize pizzaSize);
}

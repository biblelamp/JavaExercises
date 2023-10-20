package de.pizza.tomate.repository;

import de.pizza.tomate.domain.Ingredient;
import de.pizza.tomate.domain.Pizza;
import de.pizza.tomate.domain.PizzaIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaIngredientRepository extends JpaRepository<PizzaIngredient, Integer> {
    int countByIngredient(Ingredient ingredient);

    PizzaIngredient findByPizzaAndIngredient(Pizza pizza, Ingredient ingredient);
}

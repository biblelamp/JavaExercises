package de.pizza.tomate.service;

import de.pizza.tomate.controller.dto.IngredientDTO;
import de.pizza.tomate.domain.Ingredient;
import de.pizza.tomate.domain.PizzaSize;
import de.pizza.tomate.repository.IngredientRepository;
import de.pizza.tomate.repository.PizzaIngredientRepository;
import de.pizza.tomate.repository.PizzaSizeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private PizzaSizeRepository pizzaSizeRepository;

    @Autowired
    private PizzaIngredientRepository pizzaIngredientRepository;

    public List<IngredientDTO> findAll() {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        List<IngredientDTO> result = new ArrayList<>(ingredients.size());
        ingredients.forEach(i -> result.add(IngredientDTO.getInstance(i)));
        return result;
    }

    public IngredientDTO add(IngredientDTO ingredientDTO) {
        if (ingredientDTO.getPizzaSizeId() != null) {
            PizzaSize pizzaSize = pizzaSizeRepository.findById(ingredientDTO.getPizzaSizeId()).orElse(null);
            if (pizzaSize != null) {
                Ingredient ingredient = new Ingredient();
                ingredient.setPizzaSize(pizzaSize);
                ingredient.setName(ingredientDTO.getName());
                ingredient.setDescription(ingredientDTO.getDescription());
                ingredient.setPrice(ingredientDTO.getPrice());
                return IngredientDTO.getInstance(ingredientRepository.save(ingredient));
            }
        }
        return null;
    }

    public IngredientDTO update(IngredientDTO ingredientDTO) {
        Ingredient ingredient = ingredientRepository.findById(ingredientDTO.getId()).orElse(null);
        if (ingredient != null) {
            // TODO check - if update need
            // if ingredient already used - not update
            if (pizzaIngredientRepository.countByIngredient(ingredient) == 0) {
                PizzaSize pizzaSize = pizzaSizeRepository.findById(ingredientDTO.getPizzaSizeId()).orElse(null);
                if (pizzaSize != null) {
                    ingredient.setPizzaSize(pizzaSize);
                    ingredient.setName(ingredientDTO.getName());
                    ingredient.setDescription(ingredientDTO.getDescription());
                    ingredient.setPrice(ingredientDTO.getPrice());
                    return IngredientDTO.getInstance(ingredientRepository.save(ingredient));
                }
            }
        }
        return null;
    }

    public IngredientDTO delete(Integer id) {
        Ingredient ingredient = ingredientRepository.findById(id).orElse(null);
        if (ingredient != null) {
            if (pizzaIngredientRepository.countByIngredient(ingredient) == 0) {
                ingredientRepository.delete(ingredient);
                return IngredientDTO.getInstance(ingredient);
            }
        }
        return null;
    }
}

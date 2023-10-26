package de.pizza.tomate.controller.dto;

import de.pizza.tomate.domain.Ingredient;
import de.pizza.tomate.domain.Pizza;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class PizzaDTO {
    private Integer id;
    private PizzaBaseDTO pizzaBase;
    private Double priceBase;
    private Double total;
    private List<IngredientDTO> ingredients;

    public static PizzaDTO getInstance(Pizza pizza, List<Ingredient> ingredients) {
        return new PizzaDTO(pizza.getId(),
                PizzaBaseDTO.getInstance(pizza.getPizzaBase()),
                pizza.getPriceBase(),
                pizza.getTotal(),
                IngredientDTO.getInstance(ingredients));
    }

    public static List<PizzaDTO> getInstance(List<Pizza> pizzas, Map<Pizza, List<Ingredient>> ingredientMap) {
        List<PizzaDTO> result = new ArrayList<>(pizzas.size());
        pizzas.forEach(pizza -> result.add(getInstance(pizza, ingredientMap.get(pizza))));
        return result;
    }
}

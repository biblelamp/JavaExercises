package de.pizza.tomate.controller.dto;

import de.pizza.tomate.domain.Pizza;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PizzaDTO {
    private Integer id;
    private PizzaBaseDTO pizzaBase;
    private Double priceBase;
    private Double total;

    public static PizzaDTO getInstance(Pizza pizza) {
        return new PizzaDTO(pizza.getId(),
                PizzaBaseDTO.getInstance(pizza.getPizzaBase()),
                pizza.getPriceBase(),
                pizza.getTotal());
    }
}

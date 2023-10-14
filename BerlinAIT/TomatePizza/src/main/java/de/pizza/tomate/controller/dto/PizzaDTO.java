package de.pizza.tomate.controller.dto;

import de.pizza.tomate.domain.PizzaBase;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PizzaDTO {
    private Integer id;
    private String name;
    private String description;
    private String size;
    private Double price;

    public static PizzaDTO getInstance(PizzaBase pizza) {
        return new PizzaDTO(pizza.getId(),
                pizza.getPizzaType().getName(),
                pizza.getPizzaType().getDescription(),
                pizza.getPizzaSize().getName(),
                pizza.getPrice());
    }
}

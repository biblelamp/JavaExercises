package de.pizza.tomate.controller.dto;

import de.pizza.tomate.domain.PizzaBase;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PizzaBaseDTO {
    private Integer id;
    private String name;
    private String description;
    private String size;
    private Double price;
    private Boolean deleted;

    public static PizzaBaseDTO getInstance(PizzaBase pizza) {
        return new PizzaBaseDTO(pizza.getId(),
                pizza.getPizzaType().getName(),
                pizza.getPizzaType().getDescription(),
                pizza.getPizzaSize().getName(),
                pizza.getPrice(),
                pizza.getDeleted());
    }
}

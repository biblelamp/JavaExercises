package de.pizza.tomate.controller.dto;

import de.pizza.tomate.domain.PizzaType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PizzaTypeDTO {
    private Integer id;
    private String name;
    private String description;
    private String pathImage;

    public static PizzaTypeDTO getInstance(PizzaType pizzaType) {
        return new PizzaTypeDTO(pizzaType.getId(),
                pizzaType.getName(),
                pizzaType.getDescription(),
                pizzaType.getPathImage());
    }
}

package de.pizza.tomate.controller.dto;

import de.pizza.tomate.domain.PizzaSize;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PizzaSizeDTO {
    private Integer id;
    private String name;
    private String size;

    public static PizzaSizeDTO getInstance(PizzaSize size) {
        return new PizzaSizeDTO(size.getId(), size.getName(), size.getSize());
    }
}

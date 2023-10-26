package de.pizza.tomate.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PizzaBaseCreateUpdate {
    private Integer id;
    private Integer pizzaTypeId;
    private Integer pizzaSizeId;
    private Double price;
}

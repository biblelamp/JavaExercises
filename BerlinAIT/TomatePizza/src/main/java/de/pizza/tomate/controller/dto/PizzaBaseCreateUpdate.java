package de.pizza.tomate.controller.dto;

import lombok.Getter;

@Getter
public class PizzaBaseCreateUpdate {
    private Integer id;
    private Integer pizzaTypeId;
    private Integer pizzaSizeId;
    private Double price;
}

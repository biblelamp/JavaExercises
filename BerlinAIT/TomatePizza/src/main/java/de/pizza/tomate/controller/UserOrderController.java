package de.pizza.tomate.controller;

import de.pizza.tomate.controller.dto.OrderCreate;
import de.pizza.tomate.controller.dto.OrderDTO;
import de.pizza.tomate.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class UserOrderController {

    @Autowired
    private UserOrderService userOrderService;

    @PostMapping("/create")
    public OrderDTO create(@RequestBody OrderCreate order) {
        return userOrderService.create(order.getUserId());
    }

    @PutMapping("/{orderId}/add/pizza/{pizzaBaseId}")
    public OrderDTO addPizza(@PathVariable Integer orderId, @PathVariable Integer pizzaBaseId) {
        return userOrderService.addPizza(orderId, pizzaBaseId);
    }

    @DeleteMapping("/{orderId}/delete/pizza/{pizzaId}")
    public OrderDTO deletePizza(@PathVariable Integer orderId, @PathVariable Integer pizzaId) {
        return userOrderService.deletePizza(orderId, pizzaId);
    }

    @PutMapping("/{orderId}/pizza/{pizzaId}/add/ingredient/{ingredientId}")
    public OrderDTO addIngredient(@PathVariable Integer orderId, @PathVariable Integer pizzaId,
                                  @PathVariable Integer ingredientId) {
        return userOrderService.addIngredient(orderId, pizzaId, ingredientId);
    }

    @DeleteMapping("/{orderId}/pizza/{pizzaId}/delete/ingredient/{ingredientId}")
    public OrderDTO deleteIngredient(@PathVariable Integer orderId, @PathVariable Integer pizzaId,
                                     @PathVariable Integer ingredientId) {
        return userOrderService.deleteIngredient(orderId, pizzaId, ingredientId);
    }

}

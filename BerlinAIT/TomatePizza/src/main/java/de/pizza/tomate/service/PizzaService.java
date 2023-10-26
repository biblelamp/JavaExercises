package de.pizza.tomate.service;

import de.pizza.tomate.domain.Order;
import de.pizza.tomate.domain.OrderPizza;
import de.pizza.tomate.domain.Pizza;
import de.pizza.tomate.domain.PizzaIngredient;
import de.pizza.tomate.repository.OrderPizzaRepository;
import de.pizza.tomate.repository.PizzaIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaService {

    @Autowired
    private OrderPizzaRepository orderPizzaRepository;

    @Autowired
    private PizzaIngredientRepository pizzaIngredientRepository;

    public List<OrderPizza> findAllOrdersPizza() {
        return orderPizzaRepository.findAll();
    }

    public List<PizzaIngredient> findAllPizzasIngredient() {
        return pizzaIngredientRepository.findAll();
    }

    public List<Pizza> pizzasByOrder(Order order) {
        List<OrderPizza> orderPizza = orderPizzaRepository.findByOrder(order);
        return orderPizza.stream().map(i -> i.getPizza()).collect(Collectors.toList());
    }
}

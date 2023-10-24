package de.pizza.tomate.service;

import de.pizza.tomate.domain.Order;
import de.pizza.tomate.domain.OrderPizza;
import de.pizza.tomate.domain.Pizza;
import de.pizza.tomate.repository.OrderPizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaService {

    @Autowired
    private OrderPizzaRepository orderPizzaRepository;

    public List<Pizza> pizzasByOrder(Order order) {
        List<OrderPizza> orderPizza = orderPizzaRepository.findByOrder(order);
        return orderPizza.stream().map(i -> i.getPizza()).collect(Collectors.toList());
    }
}

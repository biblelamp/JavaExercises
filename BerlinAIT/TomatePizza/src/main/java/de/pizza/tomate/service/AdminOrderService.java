package de.pizza.tomate.service;

import de.pizza.tomate.controller.dto.OrderDTO;
import de.pizza.tomate.domain.Ingredient;
import de.pizza.tomate.domain.Order;
import de.pizza.tomate.domain.OrderPizza;
import de.pizza.tomate.domain.OrderState;
import de.pizza.tomate.domain.Pizza;
import de.pizza.tomate.domain.PizzaIngredient;
import de.pizza.tomate.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PizzaService pizzaService;

    public List<OrderDTO> findAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> result = new ArrayList<>(orders.size());

        List<OrderPizza> orderPizza = pizzaService.findAllOrdersPizza();
        Map<Order, List<Pizza>> orderPizzaMap = new HashMap<>();

        List<PizzaIngredient> pizzaIngredients = pizzaService.findAllPizzasIngredient();
        Map<Order, Map<Pizza, List<Ingredient>>> ingredientMap = new HashMap<>();

        for (OrderPizza op : orderPizza) {
            // fillng orderPizzaMap
            List<Pizza> pizzas = orderPizzaMap.getOrDefault(op.getOrder(), new ArrayList<>());
            pizzas.add(op.getPizza());
            orderPizzaMap.put(op.getOrder(), pizzas);

            // all ingredients by pizza
            List<Ingredient> ingredients = pizzaIngredients
                    .stream()
                    .filter(i -> i.getPizza().getId() == op.getPizza().getId())
                    .map(i -> i.getIngredient())
                    .collect(Collectors.toList());

            Map<Pizza, List<Ingredient>> pizzaIngredientMap = ingredientMap.getOrDefault(op.getOrder(), new HashMap<>());
            pizzaIngredientMap.put(op.getPizza(), ingredients);

            // filling ingredientMap
            ingredientMap.put(op.getOrder(), pizzaIngredientMap);
        }

        orders.forEach(order -> result.add(OrderDTO.getInstance(order, orderPizzaMap.get(order), ingredientMap.get(order))));
        return result;
    }

    public String changeState(Integer orderId, final String state) {
        Order order = orderRepository.findById(orderId).orElse(null);
        OrderState newState = OrderState.valueOf(state);
        if (order != null) {
            switch (order.getState()) {
                case NEW:
                    if (newState != OrderState.CONFIRMED) {
                        newState = order.getState();
                    }
                case CONFIRMED:
                    // TODO check logic
                case PAID:
                    // TODO check logic
                case DELIVERED:
                    //TODO check logic
                case CANCELED:
                    break;
            }
            if (newState != order.getState()) {
                order.setState(newState);
                orderRepository.save(order);
                return state;
            }
        }
        return null;
    }
}

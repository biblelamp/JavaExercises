package de.pizza.tomate.service;

import de.pizza.tomate.controller.dto.OrderDTO;
import de.pizza.tomate.domain.*;
import de.pizza.tomate.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class UserOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderPizzaRepository orderPizzaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PizzaBaseRepository pizzaBaseRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private PizzaIngredientRepository pizzaIngredientRepository;

    public OrderDTO create(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Order order = new Order();
            order.setUser(user);
            order.setOrderDate(OffsetDateTime.now());
            order.setState(OrderState.NEW);
            order.setService(0d);
            order.setTotal(0d);
            return OrderDTO.getInstance(orderRepository.save(order));
        }
        return null;
    }

    public OrderDTO addPizza(Integer orderId, Integer pizzaBaseId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        PizzaBase pizzaBase = pizzaBaseRepository.findById(pizzaBaseId).orElse(null);
        if (order != null && pizzaBase != null) {
            Pizza pizza = new Pizza();
            pizza.setPizzaBase(pizzaBase);
            pizza.setPriceBase(pizzaBase.getPrice());
            pizza.setTotal(pizzaBase.getPrice());
            pizza = pizzaRepository.save(pizza);

            OrderPizza orderPizza = new OrderPizza();
            orderPizza.setPizza(pizza);
            orderPizza.setOrder(order);
            orderPizzaRepository.save(orderPizza);

            order.setTotal(pizza.getTotal());
            return OrderDTO.getInstance(orderRepository.save(order));
        }
        return null;
    }

    public OrderDTO deletePizza(Integer orderId, Integer pizzaId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        Pizza pizza = pizzaRepository.findById(pizzaId).orElse(null);
        if (order != null && pizza != null) {
            OrderPizza orderPizza = orderPizzaRepository.findByPizza(pizza);
            if (orderPizza != null) {
                orderPizzaRepository.delete(orderPizza);
                pizzaRepository.delete(pizza);

                order.setTotal(order.getTotal() - pizza.getTotal());
                return OrderDTO.getInstance(orderRepository.save(order));
            }
        }
        return null;
    }

    public OrderDTO addIngredient(Integer orderId, Integer pizzaId, Integer ingredientId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        Pizza pizza = pizzaRepository.findById(pizzaId).orElse(null);
        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElse(null);
        if (order != null && pizza != null && ingredient != null) {
            // TODO if pizza is in order -> break
            // TODO if pizza_base size != ingredient size -> break
            PizzaIngredient pi = new PizzaIngredient();
            pi.setIngredient(ingredient);
            pi.setPizza(pizza);
            pizzaIngredientRepository.save(pi);

            order.setTotal(order.getTotal() + ingredient.getPrice());
            return OrderDTO.getInstance(orderRepository.save(order));
        }
        return null;
    }

    public OrderDTO deleteIngredient(Integer orderId, Integer pizzaId, Integer ingredientId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        Pizza pizza = pizzaRepository.findById(pizzaId).orElse(null);
        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElse(null);
        if (order != null && pizza != null && ingredient != null) {
            // TODO if pizza is in order -> break
            PizzaIngredient pi = pizzaIngredientRepository.findByPizzaAndIngredient(pizza, ingredient);
            if (pi != null) {
                pizzaIngredientRepository.delete(pi);

                order.setTotal(order.getTotal() - ingredient.getPrice());
                return OrderDTO.getInstance(orderRepository.save(order));
            }
        }
        return null;
    }
}

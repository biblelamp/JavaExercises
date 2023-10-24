package de.pizza.tomate.service;

import de.pizza.tomate.controller.dto.OrderDTO;
import de.pizza.tomate.domain.Ingredient;
import de.pizza.tomate.domain.Order;
import de.pizza.tomate.domain.OrderPizza;
import de.pizza.tomate.domain.OrderState;
import de.pizza.tomate.domain.Pizza;
import de.pizza.tomate.domain.PizzaBase;
import de.pizza.tomate.domain.PizzaIngredient;
import de.pizza.tomate.domain.User;
import de.pizza.tomate.repository.IngredientRepository;
import de.pizza.tomate.repository.OrderPizzaRepository;
import de.pizza.tomate.repository.OrderRepository;
import de.pizza.tomate.repository.PizzaBaseRepository;
import de.pizza.tomate.repository.PizzaIngredientRepository;
import de.pizza.tomate.repository.PizzaRepository;
import de.pizza.tomate.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
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

    @Autowired
    private PizzaService pizzaService;

    public List<OrderDTO> findByUserId(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            List<Order> orders = orderRepository.findByUser(user);
            List<OrderDTO> result = new ArrayList<>(orders.size());
            orders.forEach(order -> result.add(OrderDTO.getInstance(order, pizzaService.pizzasByOrder(order))));
            return result;
        }
        return null;
    }

    public OrderDTO findById(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            return OrderDTO.getInstance(order, pizzaService.pizzasByOrder(order));
        }
        return null;
    }

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

    public OrderDTO delete(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            Validate.isTrue(order.getState() == OrderState.NEW);
            // delete from PizzaEngridient
            List<OrderPizza> orderPizzaList = orderPizzaRepository.findByOrder(order);
            List<Pizza> pizzaList = new ArrayList<>(orderPizzaList.size());
            orderPizzaList.forEach(item -> pizzaList.add(item.getPizza()));
            int ingredientCount = pizzaIngredientRepository.deleteByPizzaIn(pizzaList);
            // delete from OrderPizza
            orderPizzaRepository.deleteByOrder(order);
            // delete Pizza
            List<Integer> pizzaIds = pizzaList.stream().map(pizza -> pizza.getId()).collect(Collectors.toList());
            int pizzaCount = pizzaRepository.deleteByIdIn(pizzaIds);
            // delete order
            orderRepository.delete(order);
            log.info("Order deleted, orderId={}, deleted {} Pizza(s), {} Ingredient(s)", orderId, pizzaCount, ingredientCount);
            return OrderDTO.getInstance(order, pizzaService.pizzasByOrder(order));
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

            order.setTotal(order.getTotal() + pizza.getTotal());
            return OrderDTO.getInstance(orderRepository.save(order), pizzaService.pizzasByOrder(order));
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
                return OrderDTO.getInstance(orderRepository.save(order), pizzaService.pizzasByOrder(order));
            }
        }
        return null;
    }

    public OrderDTO addIngredient(Integer orderId, Integer pizzaId, Integer ingredientId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        Pizza pizza = pizzaRepository.findById(pizzaId).orElse(null);
        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElse(null);
        if (order != null && pizza != null && ingredient != null) {
            // if pizza is in order -> break
            if (orderPizzaRepository.findByOrderAndPizza(order, pizza) == null) {
                log.error("Pizza is not associated with Order, pizzaId={}, orderId={}", pizzaId, orderId);
                return null;
            }
            // if pizza_base size != ingredient size -> break
            if (!Objects.equals(pizza.getPizzaBase().getPizzaSize().getId(), ingredient.getPizzaSize().getId())) {
                log.error("Ingredient size doesn't match Pizza size, pizzaSizeId={}, ingredientSizeId={}",
                        pizza.getPizzaBase().getPizzaSize().getId(),
                        ingredient.getPizzaSize().getId());
                return null;
            }
            PizzaIngredient pi = new PizzaIngredient();
            pi.setIngredient(ingredient);
            pi.setPizza(pizza);
            pizzaIngredientRepository.save(pi);

            order.setTotal(order.getTotal() + ingredient.getPrice());
            return OrderDTO.getInstance(orderRepository.save(order), pizzaService.pizzasByOrder(order));
        }
        return null;
    }

    public OrderDTO deleteIngredient(Integer orderId, Integer pizzaId, Integer ingredientId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        Pizza pizza = pizzaRepository.findById(pizzaId).orElse(null);
        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElse(null);
        if (order != null && pizza != null && ingredient != null) {
            // if pizza is in order -> break
            if (orderPizzaRepository.findByOrderAndPizza(order, pizza) == null) {
                log.error("Pizza is not associated with Order, pizzaId={}, orderId={}", pizzaId, orderId);
                return null;
            }
            PizzaIngredient pi = pizzaIngredientRepository.findByPizzaAndIngredient(pizza, ingredient);
            if (pi != null) {
                pizzaIngredientRepository.delete(pi);

                order.setTotal(order.getTotal() - ingredient.getPrice());
                return OrderDTO.getInstance(orderRepository.save(order), pizzaService.pizzasByOrder(order));
            }
        }
        return null;
    }

    public OrderDTO confirm(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            if (order.getState() == OrderState.NEW && order.getTotal() != 0) {
                order.setState(OrderState.CONFIRMED);
                return OrderDTO.getInstance(orderRepository.save(order), pizzaService.pizzasByOrder(order));
            }
        }
        return null;
    }
}

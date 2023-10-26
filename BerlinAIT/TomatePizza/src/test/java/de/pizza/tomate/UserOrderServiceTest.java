package de.pizza.tomate;

import de.pizza.tomate.controller.dto.*;
import de.pizza.tomate.repository.OrderRepository;
import de.pizza.tomate.service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserOrderServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserOrderService userOrderService;

    @Autowired
    private PizzaSizeService pizzaSizeService;

    @Autowired
    private PizzaTypeService pizzaTypeService;

    @Autowired
    private PizzaBaseService pizzaBaseService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @Order(1)
    public void testCreate() {
        UserDTO user = new UserDTO(null, "user");
        user = userService.add(user);
        Assertions.assertNotNull(user);

        OrderDTO order = userOrderService.create(user.getId());
        Assertions.assertNotNull(order);
    }

    @Test
    @Order(2)
    public void testAddPizza() {
        PizzaSizeDTO pizzaSize = new PizzaSizeDTO(null, "name", "size");
        pizzaSize = pizzaSizeService.add(pizzaSize);
        Assertions.assertNotNull(pizzaSize.getId());

        PizzaTypeDTO pizzaType = new PizzaTypeDTO(null, "name", "desc", "img");
        pizzaType = pizzaTypeService.add(pizzaType);
        Assertions.assertNotNull(pizzaType.getId());

        PizzaBaseCreateUpdate pizzaCreate = new PizzaBaseCreateUpdate(null, pizzaType.getId(), pizzaSize.getId(), 4.5);
        PizzaBaseDTO pizzaBase = pizzaBaseService.add(pizzaCreate);
        Assertions.assertNotNull(pizzaBase.getId());

        List<de.pizza.tomate.domain.Order> orders = orderRepository.findAll();
        Assertions.assertEquals(1, orders.size());

        OrderDTO order = userOrderService.addPizza(orders.get(0).getId(), pizzaBase.getId());
        Assertions.assertNotNull(order);
        Assertions.assertNotNull(order.getPizzas());
        Assertions.assertEquals(1, order.getPizzas().size());
    }

    @Test
    @Order(3)
    public void testDeletePizza() {
        List<de.pizza.tomate.domain.Order> orders = orderRepository.findAll();
        Assertions.assertEquals(1, orders.size());

        OrderDTO order = userOrderService.findById(orders.get(0).getId());
        Assertions.assertNotNull(order);
        Assertions.assertNotNull(order.getPizzas());
        Assertions.assertEquals(1, order.getPizzas().size());

        order = userOrderService.deletePizza(order.getId(), order.getPizzas().get(0).getId());
        Assertions.assertNotNull(order);
        Assertions.assertNotNull(order.getPizzas());
        Assertions.assertEquals(0, order.getPizzas().size());
    }
}

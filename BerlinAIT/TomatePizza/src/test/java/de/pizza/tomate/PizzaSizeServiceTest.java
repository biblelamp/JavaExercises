package de.pizza.tomate;

import de.pizza.tomate.controller.dto.PizzaSizeDTO;
import de.pizza.tomate.domain.PizzaSize;
import de.pizza.tomate.repository.PizzaSizeRepository;
import de.pizza.tomate.service.PizzaSizeService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PizzaSizeServiceTest {

    @Autowired
    private PizzaSizeService pizzaSizeService;

    @Autowired
    private PizzaSizeRepository pizzaSizeRepository;

    private static final String PIZZA_SIZE_NAME = "name";
    private static final String PIZZA_SIZE_SIZE = "size";
    private static final String PIZZA_SIZE_NEW_SIZE = "new_size";

    @Test
    @Order(1)
    public void testAdd() {
        Assertions.assertEquals(0, pizzaSizeRepository.count());

        PizzaSizeDTO pizzaSizeDTO = new PizzaSizeDTO(null, PIZZA_SIZE_NAME, PIZZA_SIZE_SIZE);
        pizzaSizeDTO = pizzaSizeService.add(pizzaSizeDTO);
        Assertions.assertNotNull(pizzaSizeDTO.getId());

        Assertions.assertEquals(1, pizzaSizeRepository.count());

        PizzaSize pizzaSize = pizzaSizeRepository.findById(pizzaSizeDTO.getId()).orElse(null);
        Assertions.assertNotNull(pizzaSize);
        Assertions.assertEquals(PIZZA_SIZE_NAME, pizzaSize.getName());
        Assertions.assertEquals(PIZZA_SIZE_SIZE, pizzaSize.getSize());
    }

    @Test
    @Order(2)
    public void testUpdate() {
        List<PizzaSize> pizzaSizes = pizzaSizeRepository.findAll();
        Assertions.assertEquals(1, pizzaSizes.size());

        PizzaSizeDTO pizzaSizeDTO = new PizzaSizeDTO(pizzaSizes.get(0).getId(), PIZZA_SIZE_NAME, PIZZA_SIZE_NEW_SIZE);
        pizzaSizeDTO = pizzaSizeService.update(pizzaSizeDTO);

        Assertions.assertEquals(1, pizzaSizeRepository.count());

        PizzaSize pizzaSize = pizzaSizeRepository.findById(pizzaSizeDTO.getId()).orElse(null);
        Assertions.assertNotNull(pizzaSize);
        Assertions.assertEquals(PIZZA_SIZE_NAME, pizzaSize.getName());
        Assertions.assertEquals(PIZZA_SIZE_NEW_SIZE, pizzaSize.getSize());

        // check out the negative scenario
        pizzaSizeDTO = new PizzaSizeDTO(pizzaSizes.get(0).getId() + 1, PIZZA_SIZE_NAME, PIZZA_SIZE_NEW_SIZE);
        pizzaSizeDTO = pizzaSizeService.update(pizzaSizeDTO);
        Assertions.assertNull(pizzaSizeDTO);
    }

    @Test
    @Order(3)
    public void testDelete() {
        List<PizzaSize> pizzaSizes = pizzaSizeRepository.findAll();
        Assertions.assertEquals(1, pizzaSizes.size());

        // check out the negative scenario
        PizzaSizeDTO pizzaSizeDTO = pizzaSizeService.delete(pizzaSizes.get(0).getId() + 1);
        Assertions.assertNull(pizzaSizeDTO);

        pizzaSizeDTO = pizzaSizeService.delete(pizzaSizes.get(0).getId());
        Assertions.assertNotNull(pizzaSizeDTO);

        Assertions.assertEquals(0, pizzaSizeRepository.count());
    }
}

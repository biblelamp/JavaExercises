package pizza;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pizza.domain.Pizza;
import pizza.repository.CrudRepository;
import pizza.repository.PizzaFileRepository;
import pizza.service.PizzaService;

import java.io.File;
import java.util.Collection;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PizzaServiceFileTest {
    final static String PIZZA_FILE = "C:\\temp\\java_pizza_pizza_test.txt";
    private static CrudRepository<Integer, Pizza> pizzaRepository;
    private static PizzaService pizzaService;

    @BeforeAll
    public static void init() {
        pizzaRepository = new PizzaFileRepository(PIZZA_FILE);
        pizzaService = new PizzaService(pizzaRepository);
        File file = new File(PIZZA_FILE);
        file.delete();
    }

    @Test
    @Order(1)
    public void testAdd() {
        Collection<Pizza> pizzas = pizzaRepository.findAll();
        Assertions.assertEquals(0, pizzas.size());

        pizzaService.add("Pizza", "Components...", 100);
        pizzas = pizzaRepository.findAll();
        Assertions.assertEquals(1, pizzas.size());

        Pizza pizza = pizzas.iterator().next();
        Assertions.assertEquals("Pizza", pizza.getName());
    }

    @Test
    @Order(2)
    public void testFindById() {
        Collection<Pizza> pizzas = pizzaRepository.findAll();
        Pizza pizza = pizzas.iterator().next();
        Integer pizzaId = pizza.getId();

        pizza = pizzaService.findById(pizzaId);
        Assertions.assertNotNull(pizza);
        Assertions.assertEquals(pizzaId, pizza.getId());

        pizza = pizzaService.findById(pizzaId + 1);
        Assertions.assertNull(pizza);
    }

    @Test
    @Order(3)
    public void testUpdate() {
        Collection<Pizza> pizzas = pizzaRepository.findAll();
        Pizza pizza = pizzas.iterator().next();
        Integer pizzaId = pizza.getId();

        boolean updated = pizzaService.update(pizzaId, "NewName", "new composition", 250);
        Assertions.assertTrue(updated);

        pizza = pizzaService.findById(pizzaId);
        Assertions.assertEquals("NewName", pizza.getName());
        Assertions.assertEquals("new composition", pizza.getComposition());
        Assertions.assertEquals(250, pizza.getPrice());

        updated = pizzaService.update(pizzaId + 1, "NewName", "new composition", 250);
        Assertions.assertFalse(updated);
    }

    @Test
    @Order(4)
    public void testDelete() {
        Collection<Pizza> pizzas = pizzaRepository.findAll();
        Pizza pizza = pizzas.iterator().next();
        Integer pizzaId = pizza.getId();

        boolean deleted = pizzaService.delete(pizzaId);
        Assertions.assertTrue(deleted);

        deleted = pizzaService.delete(pizzaId);
        Assertions.assertFalse(deleted);
    }
}

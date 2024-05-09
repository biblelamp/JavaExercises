package pizza;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizza.domain.Pizza;
import pizza.repository.CrudRepository;
import pizza.repository.PizzaFileRepository;
import pizza.service.PizzaService;

import java.util.Collection;

public class PizzaServiceTest {
    final String PIZZA_FILE = "C:\\temp\\java_pizza_pizza_test.txt";
    CrudRepository<Integer, Pizza> pizzaRepository;
    private PizzaService pizzaService;

    @BeforeEach
    public void init() {
        pizzaRepository = new PizzaFileRepository(PIZZA_FILE);
        pizzaService = new PizzaService(pizzaRepository);
    }

    @Test
    public void testAdd() {
        Collection<Pizza> pizzas = pizzaRepository.findAll();
        Assertions.assertEquals(0, pizzas.size());

        pizzaService.add("Pizza", "Components...", 100);
        pizzas = pizzaRepository.findAll();
        Assertions.assertEquals(1, pizzas.size());

        Pizza pizza = pizzas.iterator().next();
        Assertions.assertEquals("Pizza", pizza.getName());
    }
}

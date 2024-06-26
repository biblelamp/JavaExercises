package pizza;

import pizza.controller.AppController;
import pizza.domain.*;
import pizza.repository.*;
import pizza.repository.db.OrderDbRepository;
import pizza.repository.db.OrderPizzaDbRepository;
import pizza.service.CustomerService;
import pizza.service.ExtComponentService;
import pizza.service.OrderService;
import pizza.service.PizzaService;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project #2
 * JavaPizza based on https://www.foodora.cz/en/restaurant/uyou/saporito-pizza-and-pasta
 *
 * @author Sergey Iryupin
 * @version 25-May-24
 */
public class JavaPizzaApp {
    public static void main(String[] args) {
        // define db name && file name
        final String SQLITE_DB_NAME = "jdbc:sqlite:C:/temp/java_pizza.db";
        final String PIZZA_FILE = "C:\\temp\\java_pizza_pizza.txt";
        // create all repositories
        CrudRepository<Integer, Pizza> pizzaRepository = new PizzaRepository();
        CrudRepository<Integer, ExtComponent> extComponentRepository = new ExtComponentRepository();
        CrudRepository<Integer, Customer> customerRepository = new CustomerRepository();
        OrderPizzaDbRepository orderPizzaRepository = new OrderPizzaDbRepository(SQLITE_DB_NAME, pizzaRepository, extComponentRepository);
        OrderDbRepository orderRepository = new OrderDbRepository(SQLITE_DB_NAME, customerRepository, orderPizzaRepository);
        // create all services
        PizzaService pizzaService = new PizzaService(pizzaRepository);
        ExtComponentService extComponentService = new ExtComponentService(extComponentRepository);
        CustomerService customerService = new CustomerService(customerRepository);
        OrderService orderService = new OrderService(orderRepository, pizzaRepository, orderPizzaRepository, extComponentRepository);
        // create & run controller
        new AppController(pizzaService, extComponentService, customerService, orderService).run();
    }
}

package pizza;

import pizza.controller.AppController;
import pizza.domain.Pizza;
import pizza.repository.*;
import pizza.service.CustomerService;
import pizza.service.ExtComponentService;
import pizza.service.OrderService;
import pizza.service.PizzaService;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project #2
 * JavaPizza based on https://www.foodora.cz/en/restaurant/uyou/saporito-pizza-and-pasta
 *
 * @author Sergey Iryupin
 * @version 21-Apr-24
 */
public class JavaPizzaApp {
    public static void main(String[] args) {
        // define db name && file name
        final String SQLITE_DB_NAME = "jdbc:sqlite:C:/temp/java_pizza.db";
        final String PIZZA_FILE = "C:\\temp\\java_pizza_pizza.txt";
        // create all repositories
        PizzaFileRepository pizzaRepository = new PizzaFileRepository(PIZZA_FILE);//new PizzaRepository(SQLITE_DB_NAME);
        ExtComponentRepository extComponentRepository = new ExtComponentRepository();
        CustomerRepository customerRepository = new CustomerRepository();
        OrderPizzaRepozitory orderPizzaRepozitory = new OrderPizzaRepozitory();
        OrderRepository orderRepository = new OrderRepository();
        // create all services
        PizzaService pizzaService = new PizzaService(pizzaRepository);
        ExtComponentService extComponentService = new ExtComponentService(extComponentRepository);
        CustomerService customerService = new CustomerService(customerRepository);
        OrderService orderService = new OrderService(orderRepository, pizzaRepository, orderPizzaRepozitory, extComponentRepository);
        // init all data
        //pizzaRepository.init();
        extComponentRepository.init();
        customerRepository.init();
        // create & run controller
        new AppController(pizzaService, extComponentService, customerService, orderService).run();
    }
}

package pizza;

import pizza.controller.AppController;
import pizza.repository.PizzaRepository;
import pizza.service.CustomerService;
import pizza.service.ExtComponentService;
import pizza.service.OrderService;
import pizza.service.PizzaService;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project #1
 * JavaPizza based on https://www.foodora.cz/en/restaurant/uyou/saporito-pizza-and-pasta
 *
 * @author Sergey Iryupin
 * @version 18-Apr-24
 */
public class JavaPizza {
    public static void main(String[] args) {
        // create all repositories
        PizzaRepository pizzaRepository = new PizzaRepository();
        // create all services
        PizzaService pizzaService = new PizzaService(pizzaRepository);
        ExtComponentService extComponentService = new ExtComponentService();
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();
        // init all data
        pizzaRepository.init();
        extComponentService.init();
        customerService.init();
        // create & run controller
        new AppController(pizzaService, extComponentService, customerService, orderService)
                .run();
    }
}

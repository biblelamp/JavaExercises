package pizza.controller;

import pizza.service.CustomerService;
import pizza.service.ExtComponentService;
import pizza.service.OrderService;
import pizza.service.PizzaService;

import java.util.Scanner;

/**
 * Application controller
 * Implements a menu for working with the application
 *
 * @author Sergey Iryupin
 * @version 18-Apr-24
 */
public class AppController {
    private final PizzaService pizzaService;
    private final ExtComponentService extСomponentService;
    private final CustomerService customerService;
    private final OrderService orderService;
    private final Scanner scanner;

    public AppController(final PizzaService pizzaService,
                         final ExtComponentService extСomponentService,
                         final CustomerService customerService,
                         final OrderService orderService) {
        this.pizzaService = pizzaService;
        this.extСomponentService = extСomponentService;
        this.customerService = customerService;
        this.orderService = orderService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        char cmd;
        // create all controllers
        PizzaController pizzaController = new PizzaController(pizzaService, scanner);
        ExtComponentController extComponentController = new ExtComponentController(extСomponentService, scanner);
        CustomerController customerController = new CustomerController(customerService, scanner);
        OrderController orderController = new OrderController(orderService, customerService, pizzaService, extСomponentService, scanner);
        do {
            System.out.print("Choose service: [p]izza, [e]xtcomponent, [c]ustomer, [o]rder, e[x]it: ");
            cmd = scanner.nextLine().charAt(0);
            switch (cmd) {
                case 'p':
                    pizzaController.run();
                    break;
                case 'e':
                    extComponentController.run();
                    break;
                case 'c':
                    customerController.run();
                    break;
                case 'o':
                    orderController.run();
                    break;
                case 'x':
                    break;
                default:
                    System.out.println("Unrecognized command: " + cmd);
            }
        } while (cmd != 'x');
        System.out.println("Exit.");
    }
}

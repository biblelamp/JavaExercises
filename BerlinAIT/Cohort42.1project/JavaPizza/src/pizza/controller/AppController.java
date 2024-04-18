package pizza.controller;

import pizza.service.CustomerService;
import pizza.service.ExtComponentService;
import pizza.service.OrderService;
import pizza.service.PizzaService;

import java.util.Scanner;

public class AppController {
    private PizzaService pizzaService;
    private ExtComponentService extСomponentService;
    private CustomerService customerService;
    private OrderService orderService;
    private Scanner scanner;

    public AppController(PizzaService pizzaService,
                         ExtComponentService extСomponentService,
                         CustomerService customerService,
                         OrderService orderService) {
        this.pizzaService = pizzaService;
        this.extСomponentService = extСomponentService;
        this.customerService = customerService;
        this.orderService = orderService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        char cmd;
        do {
            System.out.print("Choose service: [p]izza, [e]xtcomponent, [c]ustomer, [o]rder, e[x]it: ");
            cmd = scanner.nextLine().charAt(0);
            switch (cmd) {
                case 'p':
                    pizzaServiceMenu();
                    break;
                case 'e':
                    // TODO Call ExtComponentService
                    break;
                case 'c':
                    // TODO Call CustomerService
                    break;
                case 'o':
                    // TODO Call OrderService
                    break;
                case 'x':
                    break;
                default:
                    System.out.println("Unrecognized command: " + cmd);
            }
        } while (cmd != 'x');
        System.out.println("Exit.");
    }

    private void pizzaServiceMenu() {
        char cmd;
        String[] input;
        String name, composition;
        int id, price;
        do {
            System.out.print("Pizza service: [a]dd, [u]pdate, [d]elete, [p]rint, [b]ack: ");
            cmd = scanner.nextLine().charAt(0);
            switch (cmd) {
                case 'a':
                    System.out.print("Pizza service: add: name & composition & price: ");
                    input = scanner.nextLine().split("&");
                    name = input[0].trim();
                    composition = input[1].trim();
                    price = Integer.valueOf(input[2].trim());
                    pizzaService.add(name, composition, price);
                    break;
                case 'u':
                    System.out.print("Pizza service: update: id & name & composition & price: ");
                    input = scanner.nextLine().split("&");
                    id = Integer.valueOf(input[0].trim());
                    name = input[1].trim();
                    composition = input[2].trim();
                    price = Integer.valueOf(input[3].trim());
                    pizzaService.update(id, name, composition, price);
                    break;
                case 'd':
                    System.out.print("Pizza service: delete: id: ");
                    id = Integer.valueOf(scanner.nextLine());
                    pizzaService.delete(id);
                    break;
                case 'p':
                    pizzaService.print();
                    break;
                case 'b':
                    break;
                default:
                    System.out.println("Unrecognized command: " + cmd);
            }
        } while (cmd != 'b');
    }
}

package pizza.controller;

import pizza.service.OrderService;

import java.util.Scanner;

/**
 * Pizza service controller
 * Implements a menu for working with order service
 *
 * @author Sergey Iryupin
 * @version 18-Apr-24
 */
public class OrderController {
    private OrderService service;
    private Scanner scanner;

    public OrderController(OrderService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void run() {
        char cmd;
        String[] input;
        int id;
        do {
            System.out.print("Order service: [c]reate, [d]elete, [e]dit, [p]rint, [b]ack: ");
            cmd = scanner.nextLine().charAt(0);
            switch (cmd) {
                case 'c':
                    // TODO
                    break;
                case 'd':
                    // TODO
                    break;
                case 'e':
                    // TODO
                    break;
                case 'p':
                    // TODO
                    break;
                case 'b':
                    break;
                default:
                    System.out.println("Unrecognized command: " + cmd);
            }
        } while (cmd != 'b');
    }
}

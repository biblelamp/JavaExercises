package pizza.controller;

import pizza.service.PizzaService;

import java.util.Scanner;

/**
 * Pizza service controller
 * Implements a menu for working with pizza service
 *
 * @author Sergey Iryupin
 * @version 18-Apr-24
 */
public class PizzaController {
    private PizzaService service;
    private Scanner scanner;

    public PizzaController(PizzaService pizzaService, Scanner scanner) {
        this.service = pizzaService;
        this.scanner = scanner;
    }

    public void run() {
        char cmd;
        String[] input;
        String name, composition;
        int id, price;
        do {
            System.out.print("Pizza service: [a]dd, [u]pdate, [d]elete, [f]ind, [p]rint, [b]ack: ");
            cmd = scanner.nextLine().charAt(0);
            switch (cmd) {
                case 'a':
                    System.out.print("Pizza service: add: name & composition & price: ");
                    input = scanner.nextLine().split("&");
                    name = input[0].trim();
                    composition = input[1].trim();
                    price = Integer.valueOf(input[2].trim());
                    service.add(name, composition, price);
                    break;
                case 'u':
                    System.out.print("Pizza service: update: id & name & composition & price: ");
                    input = scanner.nextLine().split("&");
                    id = Integer.valueOf(input[0].trim());
                    name = input[1].trim();
                    composition = input[2].trim();
                    price = Integer.valueOf(input[3].trim());
                    service.update(id, name, composition, price);
                    break;
                case 'd':
                    System.out.print("Pizza service: delete: id: ");
                    id = Integer.valueOf(scanner.nextLine());
                    service.delete(id);
                    break;
                case 'f':
                    System.out.print("Pizza service: find: id: ");
                    id = Integer.valueOf(scanner.nextLine());
                    System.out.println(service.findById(id));
                    break;
                case 'p':
                    service.print();
                    break;
                case 'b':
                    break;
                default:
                    System.out.println("Unrecognized command: " + cmd);
            }
        } while (cmd != 'b');
    }
}

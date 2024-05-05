package pizza.controller;

import pizza.service.CustomerService;

import java.util.Scanner;

/**
 * Pizza service controller
 * Implements a menu for working with customer service
 *
 * @author Sergey Iryupin
 * @version 18-Apr-24
 */
public class CustomerController {
    private CustomerService service;
    private Scanner scanner;

    public CustomerController(CustomerService customerService, Scanner scanner) {
        this.service = customerService;
        this.scanner = scanner;
    }

    public void run() {
        char cmd;
        String[] input;
        String name, address, phone;
        int id;
        do {
            System.out.print("Pizza service: [a]dd, [u]pdate, [d]elete, [p]rint, [b]ack: ");
            cmd = scanner.nextLine().charAt(0);
            switch (cmd) {
                case 'a':
                    System.out.print("Customer service: add: name & address & phone: ");
                    input = scanner.nextLine().split("&");
                    name = input[0].trim();
                    address = input[1].trim();
                    phone = input[2].trim();
                    service.add(name, address, phone);
                    break;
                case 'u':
                    System.out.print("Customer service: update: id & name & composition & price: ");
                    input = scanner.nextLine().split("&");
                    id = Integer.valueOf(input[0].trim());
                    name = input[1].trim();
                    address = input[2].trim();
                    phone = input[3].trim();
                    service.update(id, name, address, phone);
                    break;
                case 'd':
                    System.out.print("Customer service: delete: id: ");
                    id = Integer.valueOf(scanner.nextLine());
                    service.delete(id);
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

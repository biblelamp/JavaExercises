package pizza.controller;

import pizza.service.ExtComponentService;

import java.util.Scanner;

/**
 * Pizza service controller
 * Implements a menu for working with ext component service
 *
 * @author Sergey Iryupin
 * @version 18-Apr-24
 */
public class ExtComponentController {
    private ExtComponentService service;
    private Scanner scanner;

    public ExtComponentController(ExtComponentService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void run() {
        char cmd;
        String[] input;
        String name;
        int id, price;
        do {
            System.out.print("Pizza service: [a]dd, [u]pdate, [d]elete, [p]rint, [b]ack: ");
            cmd = scanner.nextLine().charAt(0);
            switch (cmd) {
                case 'a':
                    System.out.print("ExtComponent service: add: name & price: ");
                    input = scanner.nextLine().split("&");
                    name = input[0].trim();
                    price = Integer.valueOf(input[1].trim());
                    service.add(name, price);
                    break;
                case 'u':
                    System.out.print("ExtComponent service: update: id & name & price: ");
                    input = scanner.nextLine().split("&");
                    id = Integer.valueOf(input[0].trim());
                    name = input[1].trim();
                    price = Integer.valueOf(input[2].trim());
                    service.update(id, name, price);
                    break;
                case 'd':
                    System.out.print("ExtComponent service: delete: id: ");
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

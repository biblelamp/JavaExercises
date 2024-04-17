package pizza;

import pizza.service.CustomerService;
import pizza.service.ExtComponentService;
import pizza.service.OrderService;
import pizza.service.PizzaService;

import java.util.Scanner;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project #1
 * JavaPizza based on https://www.foodora.cz/en/restaurant/uyou/saporito-pizza-and-pasta
 *
 * @author Sergey Iryupin
 * @version 17-Apr-24
 */
public class JavaPizza {
    public static void main(String[] args) {
        // define variables
        char cmd;
        String[] input;
        String name, composition;
        int id, price;
        Scanner scanner = new Scanner(System.in);
        // create all services
        PizzaService pizzaService = new PizzaService();
        ExtComponentService extComponentService = new ExtComponentService();
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();
        // init data
        pizzaService.init();
        // main menu
        do {
            System.out.print("Choose service: [p]izza, [e]xtcomponent, [c]ustomer, [o]rder, e[x]it: ");
            cmd = scanner.nextLine().charAt(0);
            switch (cmd) {
                case 'p':
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
}

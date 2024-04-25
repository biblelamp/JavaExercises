package lesson41.homework.controller;

import lesson41.homework.service.BookService;

import java.util.Scanner;

public class BookController {
    private BookService bookService;

    private Scanner scanner;

    public BookController(BookService bookService) {
        this.bookService = bookService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        String[] input;
        int id;
        String name;
        char cmd = 0;
        do {
            System.out.print("CRUD app: [a]dd, [u]pdate, [d]elete, [p]rint, e[x]it: ");
            cmd = scanner.nextLine().charAt(0);
            switch (cmd) {
                case 'a':
                    System.out.print("Add: book name: ");
                    name = scanner.nextLine().trim();
                    bookService.add(name);
                    break;
                case 'u':
                    System.out.print("Update: book id & name: ");
                    input = scanner.nextLine().split("&");
                    id = Integer.valueOf(input[0].trim());
                    name = input[1].trim();
                    bookService.update(id, name);
                    break;
                case 'd':
                    System.out.print("Delete: book id: ");
                    id = Integer.valueOf(scanner.nextLine().trim());
                    if (!bookService.delete(id)) {
                        System.out.println("Book not found, book id: " + id);
                    }
                    break;
                case 'p':
                    bookService.print();
                    break;
                case 'x':
                    break;
                default:
                    System.out.println("Undefined command: " + cmd);
            }
        } while (cmd != 'x');
    }
}

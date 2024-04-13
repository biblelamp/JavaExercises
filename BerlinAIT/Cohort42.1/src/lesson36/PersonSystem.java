package lesson36;

import java.util.Scanner;

public class PersonSystem {
    public static void main(String[] args) {
        int id, age;
        String name;
        char operation;
        Persons persons = new Persons();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Simple personal system:");
        do {
            System.out.print("Enter operation ([a]dd,[d]elete,[u]pdate,[p]rint,[f]ind,e[x]it): ");
            operation = scanner.next().charAt(0);
            switch (operation) {
                case 'a':
                    System.out.print("Add: name & age: ");
                    name = scanner.next();
                    age = scanner.nextInt();
                    persons.add(name, age);
                    break;
                case 'd':
                    System.out.print("Delete: id: ");
                    id = scanner.nextInt();
                    persons.delete(id);
                    break;
                case 'u':
                    System.out.print("Update: id & name & age: ");
                    id = scanner.nextInt();
                    name = scanner.next();
                    age = scanner.nextInt();
                    persons.update(id, name, age);
                    break;
                case 'p':
                    System.out.println(persons);
                    break;
                case 'f':
                    System.out.print("Find: id: ");
                    id = scanner.nextInt();
                    System.out.println(persons.find(id));
                    break;
                case 'x':
                    break;
                default:
                    System.out.println("Undefined operation: " + operation);
            }
        } while (operation != 'x');
    }
}

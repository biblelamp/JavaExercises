/**
 * Java. Game Guess The Number
 * Simple game for beginners in console
 */
import java.util.*;

class GuessTheNumber {

    public static void main(String[] args) {
        int range = 10;
        int number = (int) (Math.random() * range);
        int counter = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Your task is to guess the conceived number.");
        while (true) {
            System.out.println("Guess the number from 0 to " + range + ":");
            int input_number = scanner.nextInt();
            counter++;
            if (input_number == number) {
                System.out.println("You guessed for " + counter + " attempt(s).");
                break;
            } else if (input_number > number) {
                System.out.println("Conceived number less.");
            } else {
                System.out.println("Conceived number greater.");
            }
        }
        scanner.close();
    }
}
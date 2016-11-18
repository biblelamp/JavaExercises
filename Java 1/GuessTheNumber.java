/**
 * Java. Game Guess The Number
 * Simple game for beginners in console
 *
 * @version 0.2
 */
import java.util.*;

class GuessTheNumber {

    static Scanner scanner = new Scanner(System.in);
    static int counter = 0;

    public static void main(String[] args) {
        int range = 10;
        int number = (int) (Math.random() * range) + 1;
        System.out.println("Your task is to guess the conceived number.");
        playLevel(range, number);
        scanner.close();
    }

    static void playLevel(int range, int number) {
        while (true) {
            System.out.println("Guess the number from 1 to " + range + ":");
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
    }
}
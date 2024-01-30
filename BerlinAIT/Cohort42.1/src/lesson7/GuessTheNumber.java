package lesson7;

import java.util.Random;
import java.util.Scanner;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #7
 *
 * @version 29-Jan-24
 */
public class GuessTheNumber {
    public static void main(String[] args) {
        // create and init variables
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        do {
            int number = random.nextInt(10);
            int counter = 0;
            int answer = -1;
            // main game loop
            while (answer != number && counter < 3) {
                counter++;
                System.out.println("Guess the number [0..9]:");
                answer = scanner.nextInt();
                if (answer < number) {
                    System.out.println("Your number is less");
                } else if (answer > number) {
                    System.out.println("Your number is greater");
                }
            }
            // to show result of game
            if (answer == number) {
                System.out.println("You WIN!");
            } else {
                System.out.println("You LOSE! It was " + number);
            }
            System.out.println("Play again? [1-yes, 0-no]:");
        } while (scanner.nextInt() == 1);
    }
}

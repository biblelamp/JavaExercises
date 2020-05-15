import java.util.Scanner;
import java.util.Random;

class GuessTheNumber {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        final int limit = 3;
        int count, guess, number;
        do {
            count = 0;
            guess = -1;
            number = random.nextInt(10);
            while (count < limit && guess != number) {
                System.out.printf(
                    "Guess [%d attempts] the number (0..9): ", limit-count);
                guess = sc.nextInt();
                if (number != guess) {
                    System.out.println("Your number is " +
                        ((guess > number)? "greater" : "less"));
                    count++;
                }
            }
            System.out.println("You " + ((guess == number)? "WON!" : "lose..."));
            System.out.println("Repeat the game? [Y]es/[N]o");
        } while (sc.next().equalsIgnoreCase("y"));
    }
}
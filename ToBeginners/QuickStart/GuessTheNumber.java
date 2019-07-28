import java.util.Scanner;
import java.util.Random;

class GuessTheNumber {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        do {
            int count = 0;
            int guess = -1;
            int number = random.nextInt(10);
            while (count < 3 && guess != number) {
                System.out.print(
                    "Guess [" + (count + 1) + "] the number (0..9): ");
                guess = sc.nextInt();
                if (number == guess) {
                    System.out.println("You won!");
                } else {
                    System.out.println("Your number is " + ((guess > number)?
                        "greater" : "less."));
                    count++;
                }
            }
            if (count == 3)
                System.out.println("You lost!");
            System.out.print("Repeat the game?\n[1 - yes / 0 - no]: ");
        } while (sc.next().equals("1"));
    }
}
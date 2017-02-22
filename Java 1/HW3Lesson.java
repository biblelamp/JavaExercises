/**
 * Java. Level 1. Lesson 3. Example of homework
 *
 * @author Sergey Iryupin
 * @version dated 22 Feb 2017
 */
import java.util.*;

public class HW3Lesson {

    public static void main(String[] args) {
        guessTheNumber();
    }

    /**
     * 1. Написать программу, которая загадывает случайное число от 0 до 9,
     *    и пользователю дается 3 попытки угадать это число. При каждой попытке
     *    компьютер должен сообщить больше ли указанное пользователем число чем
     *    загаданное, или меньше. После победы или проигрыша выводится запрос
     *    «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
     */
    static void guessTheNumber() {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        int repeat = 0;
        do {
            int count = 0;
            int guess = -1;
            int number = random.nextInt(10);
            while (count < 3 && guess != number) {
                System.out.print("Guess [" + (count + 1) + "] the number (0..9): ");
                guess = sc.nextInt();
                if (number == guess) {
                    System.out.println("You won!");
                } else {
                    System.out.println("Your number is " + ((guess > number)? "more" : "less."));
                    count++;
                }
            }
            if (count == 3)
                System.out.println("You lost!");
            System.out.print("Repeat the game?\n[1-yes/0-no]: ");
            again = sc.nextInt();
        } while (repeat == 1);
    }
}
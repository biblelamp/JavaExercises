import java.util.Random;

/**
 * Java 1. Lesson 8. Game Tic Tac Toe
 * Class: AI
 *
 * @author Sergey Iryupin
 * @version 0.1.2 dated Sep 08, 2018
 */
class AI {
    private final char DOT;
    private Random random;

    AI(char ch) {
        DOT = ch;
        random = new Random();
    }

    void turn(Field field) {
        int x, y;
        do {
            x = random.nextInt(field.getSize());
            y = random.nextInt(field.getSize());
        } while (!field.isCellValid(x, y));
        field.setDot(x, y, DOT);
    }
}
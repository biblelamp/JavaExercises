/*
 * Java 1. Lesson 8. Game Tic Tac Toe
 * Class: AI
 *
 * @author Sergey Iryupin
 * @version 0.1.1 dated Oct 21, 2017
 */
import java.util.*;

class AI {
    Random random = new Random();
    private final char DOT;

    AI(char ch) { DOT = ch; }

    void turn(Field field) {
        int x, y;
        do {
            x = random.nextInt(field.getSize());
            y = random.nextInt(field.getSize());
        } while (!field.isCellValid(x, y));
        field.setDot(x, y, DOT);
    }
}
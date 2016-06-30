package battleship;
import java.util.Random;

/**
 * Сlass AiPlayer - for computer
 * 
 * @author Sergey Iryupin
 * @version 30 June 2016
 */
public class AiPlayer extends Player {
    private Random rand = new Random();
    public AiPlayer() {
        super();
    }
    @Override
    public void turn(GameField fl) {
        System.out.println("Computer turn...");
        int x = rand.nextInt(10);
        int y = rand.nextInt(10);
        fl.strike(x, y);
    }
}
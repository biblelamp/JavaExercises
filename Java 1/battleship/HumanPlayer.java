package battleship;
import java.util.Scanner;

/**
 * Class HumanPlayer - for human
 * 
 * @author Sergey Iryupin
 * @version 30 June 2016
 */
public class HumanPlayer extends Player {
    private Scanner sc = new Scanner(System.in);
    public HumanPlayer() {
        super();
    } 
    @Override
    public void turn(GameField fl) {
        System.out.println("Enter the coordinates of shooting: X Y");
        int x = sc.nextInt() - 1;
        int y = sc.nextInt() - 1;
        fl.strike(x, y);
    }
}
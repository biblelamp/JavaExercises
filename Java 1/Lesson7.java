/**
 * Java. Level 1. Lesson 7. Homework
 * Writing program BattleShip in OOP style
 * 
 * @author Sergey Iryupin
 * @version 30 June 2016
 */
import battleship.GameField;
import battleship.Player;
import battleship.AiPlayer;
import battleship.HumanPlayer;

public class Lesson7 {

    public static void main(String[] args) {
        Player p1 = new HumanPlayer();
        Player p2 = new AiPlayer();
        p1.getField().printField(true);
        while (true) {
            p1.turn(p2.getField());
            p2.getField().printField(true);
            if (p2.getField().isDefeated())
                break;
            p2.turn(p1.getField());
            p1.getField().printField(true);
            if (p1.getField().isDefeated())
                break;
        }
    }
}
package battleship;

/**
 * Abstract class Player - base class for players
 * 
 * @author Sergey Iryupin
 * @version 30 June 2016
 */
public abstract class Player {
    private GameField gf;
    public GameField getField() {
        return gf;
    };
    public Player() {
        gf = new GameField();
    }
    public abstract void turn(GameField fl);
}
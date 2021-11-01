// Перечисление с кодами игр

enum GameCode {
    CHESS,
    MONOPOLY
}

// Абстрактный класс с абстрактными методами для их последующей реализации

abstract class AbstractGame {
    private int playersAmount;

    protected abstract void initGame();

    protected abstract void playGame();

    protected abstract void printWinner();

    public void playOneGame(int playersAmount){
        setPlayersAmount(playersAmount);
        initGame();
        playGame();
        printWinner();
    }

    private void setPlayersAmount(int playersAmount){
        this.playersAmount = playersAmount;
    }
}

// Класс, имитирующий игру в шахматы

class Chess extends AbstractGame {
 
    @Override
    public void initGame() {
        System.out.println("Chess: init game");
    }

    @Override
    public void playGame() {
        System.out.println("Chess: play game");
    }

    @Override
    public void printWinner() {
        System.out.println("Chess: print winner");
    }
}

// Класс, имитирующий игру в монополию

class Monopoly extends AbstractGame {
 
    @Override
    public void initGame() {
        System.out.println("Monopoly: init game");
    }

    @Override
    public void playGame() {
        System.out.println("Monopoly: play game");
    }

    @Override
    public void printWinner() {
        System.out.println("Monopoly: print winner");
    }
}

// Класс для проверки работы шаблона Шаблонный метод

public class TemplateMethodExample {
    public static void main(String[] args) {
        AbstractGame game;
        GameCode[] gameCodes = {GameCode.CHESS, GameCode.MONOPOLY};
        for (GameCode gameCode : gameCodes) {
            switch (gameCode){
            case CHESS:
                game = new Chess();
                break;
            case MONOPOLY :
                game = new Monopoly();
                break;
            default :
                throw new IllegalStateException();
            }
            game.playOneGame(2);
        }
    }
}
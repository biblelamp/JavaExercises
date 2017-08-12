/**
 * Java. Game TicTacToe with objects
 *
 * @author Sergey Iryupin
 * @version 0.3 dated Aug 12, 2017
 */
import java.util.*;

class TicTacToeWithObjects {

    final String HUMAN_WON = "YOU WON!";
    final String AI_WON = "AI WON!";
    final String DRAW_MSG = "Sorry DRAW...";
    final String GAME_OVER_MSG = "GAME OVER.";

    Map map = new Map();
    Human human = new Human();
    AI ai = new AI();

    public static void main(String[] args) {
        new TicTacToeWithObjects();
    }

    TicTacToeWithObjects() {
        while (true) {
            System.out.print(map);
            human.turn(map);
            if (map.checkWin(human.getDot())) {
                System.out.println(HUMAN_WON);
                break;
            }
            if (map.isFull()) {
                System.out.println(DRAW_MSG);
                break;
            }
            ai.turn(map);
            if (map.checkWin(ai.getDot())) {
                System.out.println(AI_WON);
                break;
            }
            /*if (map.isFull()) {
                System.out.println(DRAW_MSG);
                break;
            }*/
        }
        System.out.println(GAME_OVER_MSG);
        System.out.print(map);
    }
}

class Map {
    private final int SIZE = 3;
    private final char EMPTY_DOT = '.';
    private char[][] map = new char[SIZE][SIZE];

    Map() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                map[i][j] = EMPTY_DOT;
    }

    int getSize() { return SIZE; }

    void setDot(int x, int y, char dot) { map[x][y] = dot; }

    boolean isCellEmpty(int x, int y) {
        if (x < 0 || y < 0 || x > SIZE - 1 || y > SIZE - 1)
            return false;
        if (map[x][y] == EMPTY_DOT)
            return true;
        return false;
    }

    boolean isFull() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (map[i][j] == EMPTY_DOT)
                    return false;
        return true;
    }

    boolean checkWin(char dot) {
        // check horizontals and verticals
        for (int i = 0; i < SIZE; i++)
            if ((map[i][0] == dot && map[i][1] == dot && map[i][2] == dot) ||
                (map[0][i] == dot && map[1][i] == dot && map[2][i] == dot))
                return true;
        // check diagonals
        if ((map[0][0] == dot && map[1][1] == dot && map[2][2] == dot) ||
            (map[2][0] == dot && map[1][1] == dot && map[0][2] == dot))
            return true;
        return false;
    }

    @Override
    public String toString() {
        String result = "";
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++)
                result += map[x][y] + " ";
            result += "\n";
        }
        return result;
    }
}

class Human {
    private final char DOT = 'x';
    private Scanner sc = new Scanner(System.in);

    char getDot() { return DOT; }

    void turn(Map map) {
        int x, y;
        do {
            System.out.println("Enter X and Y (1.."+map.getSize()+"):");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!map.isCellEmpty(x, y));
        map.setDot(x, y, DOT);
    }
}

class AI {
    private final char DOT = 'o';
    private Random rand = new Random();

    char getDot() { return DOT; }

    void turn(Map map) {
        int x, y;
        do {
            x = rand.nextInt(map.getSize());
            y = rand.nextInt(map.getSize());
        } while (!map.isCellEmpty(x, y));
        map.setDot(x, y, DOT);
    }
}
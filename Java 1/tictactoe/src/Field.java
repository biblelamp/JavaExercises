import java.awt.*;

/**
 * Java 1. Lesson 8. Game Tic Tac Toe
 * Class: Field
 *
 * @author Sergey Iryupin
 * @version 0.3.3 dated Dec 27, 2018
 */
class Field {
    private final int FIELD_SIZE;
    private final int CELL_SIZE;
    private final char HUMAN_DOT = 'x';
    private final char AI_DOT = 'o';
    private final char EMPTY_DOT = '.';
    private final String MSG_DRAW = "Draw, sorry...";
    private final String MSG_HUMAN_WON = "YOU WON!";
    private final String MSG_AI_WON = "AI WON!";

    private char[][] map;
    private String gameOverMsg;

    Field(int field_size, int cell_size) {
        FIELD_SIZE = field_size;
        CELL_SIZE = cell_size;
        map = new char[FIELD_SIZE][FIELD_SIZE];
        init();
    }

    void init() {
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                map[i][j] = EMPTY_DOT;
        gameOverMsg = null;
    }

    int getSize() { return FIELD_SIZE; }

    char getHumanDot() { return HUMAN_DOT; }

    char getAIDot() { return AI_DOT; }

    boolean isGameOver() { return gameOverMsg != null; }

    String getGameOverMsg() { return gameOverMsg; }

    void setDot(int x, int y, char dot) { // set dot and check fill and win
        map[x][y] = dot;
        if (checkWin(HUMAN_DOT))
            gameOverMsg = MSG_HUMAN_WON;
        else if (checkWin(AI_DOT))
            gameOverMsg = MSG_AI_WON;
        else if (isMapFull())
            gameOverMsg = MSG_DRAW;
    }

    boolean isMapFull() {
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                if (map[i][j] == EMPTY_DOT)
                    return false;
        return true;
    }

    boolean checkWin(char dot) {
        // checking horizontals / verticals
        for (int i = 0; i < FIELD_SIZE; i++)
            if ((map[i][0] == dot && map[i][1] == dot && map[i][2] == dot) ||
                (map[0][i] == dot && map[1][i] == dot && map[2][i] == dot))
                return true;
        // checking diagonals
        if ((map[0][0] == dot && map[1][1] == dot && map[2][2] == dot) ||
            (map[2][0] == dot && map[1][1] == dot && map[0][2] == dot))
            return true;
        return false;
    }

    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x > FIELD_SIZE - 1 || y > FIELD_SIZE - 1)
            return false;
        if (map[x][y] == EMPTY_DOT)
            return true;
        return false;
    }

    public void paint(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.lightGray);
        for (int i = 1; i < FIELD_SIZE; i++) {
            g.drawLine(0, i*CELL_SIZE, FIELD_SIZE*CELL_SIZE, i*CELL_SIZE);
            g.drawLine(i*CELL_SIZE, 0, i*CELL_SIZE, FIELD_SIZE*CELL_SIZE);
        }

        g.setStroke(new BasicStroke(5));

        for (int y = 0; y < FIELD_SIZE; y++) {
            for (int x = 0; x < FIELD_SIZE; x++) {
                if (map[x][y] == HUMAN_DOT) {
                    g.setColor(Color.blue);
                    g.drawLine(x*CELL_SIZE+CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4,
                        (x+1)*CELL_SIZE-CELL_SIZE/4, (y+1)*CELL_SIZE-CELL_SIZE/4);
                    g.drawLine(x*CELL_SIZE+CELL_SIZE/4, (y+1)*CELL_SIZE-CELL_SIZE/4,
                        (x+1)*CELL_SIZE-CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4);
                }
                if (map[x][y] == AI_DOT) {
                    g.setColor(Color.red);
                    g.drawOval(x*CELL_SIZE+CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4,
                        CELL_SIZE/2,
                        CELL_SIZE/2);
                }
            }
        }
    }
}

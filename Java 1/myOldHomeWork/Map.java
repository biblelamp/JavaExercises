import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.Random;

public class Map extends JPanel {
    int linesCount;
    final int PANEL_SIZE = 500;
    int cellSize;
    private static int PL1_DOT = 1;
    private static int PL2_DOT = 2;
    private static int EMPTY = 0;
    private boolean gameOver = false;
    private int winLine = 3;
    private String gameOverMsg = "";
    int xWin1, yWin1, xWin2, yWin2;
    private int[][] field;
    private Random rnd = new Random();
    boolean modeHvC = true; // true: Human vs Computer, false: Human vs Human
    boolean humanOneTurn = true; // switch for mode Human vs Human

    public Map(int linesCount){
        startNewGame(linesCount, modeHvC);
        setBackground(Color.white);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                //System.out.println(e.getX()/cellSize+" "+e.getY()/cellSize);
                //System.out.println(e.getX()+" "+e.getY());
                int x = e.getX()/cellSize;
                int y = e.getY()/cellSize;
                if(!gameOver) {
                    if (modeHvC) {
                        if (setDot(x, y, PL1_DOT)) {
                            checkFieldFull();
                            checkWin(PL1_DOT);
                            repaint();
                            aiTurn();
                        }
                    } else {
                        if (setDot(x, y, (humanOneTurn) ? PL1_DOT : PL2_DOT)) {
                            checkWin((humanOneTurn) ? PL1_DOT : PL2_DOT);
                            checkFieldFull();
                            repaint();
                            humanOneTurn = !humanOneTurn;
                        }
                    }
                }

            }
        });
    }

    public  void startNewGame(int linesCount, boolean mode) {
        this.linesCount = linesCount;
        this.modeHvC = mode;
        field = new int[linesCount][linesCount];
        gameOver = false;
        cellSize = PANEL_SIZE/linesCount;
        repaint();
    }

    public  boolean checkWin(int ox) {
        for (int x = 0; x < linesCount; x++) {
            for (int y = 0; y < linesCount; y++) {
                if (checkLine(x, y, 1, 0, winLine, ox) || checkLine(x, y, 0, 1, winLine, ox) ||
                    checkLine(x, y, 1, 1, winLine, ox) || checkLine(x, y, 1, -1, winLine, ox)) {
                    gameOver = true;
                    if (modeHvC) {
                        gameOverMsg = (ox == PL1_DOT) ? "HUMAN WON!" : "COMPUTER WON!";
                    } else {
                        gameOverMsg = (ox == PL1_DOT) ? "HUMAN1(x) WON!" : "HUMAN2(o) WON!";
                    }
                    return true;
                }

            }
        }
        return false;
    }

    public  boolean checkLine(int x, int y, int vx, int vy, int l, int ox) {
        if (x + l*vx > linesCount || y + l*vy > linesCount || y + l*vy<-1 || x + l*vx < -1)
            return false;
        for (int i = 0; i < l; i++) {
            if (field[y + i * vy][x + i * vx] != ox)
                return false;
        }
        return true;
    }

    public void checkFieldFull() {
        boolean b = true;
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < linesCount; j++) {
                if(field[i][j] == EMPTY) {
                    b = false;
                }
            }
        }
        if (b) {
            gameOverMsg = "DROW!";
            gameOver = true;
        }
    }

    public boolean setDot(int x, int y, int value) {
        if (field[x][y] == 0){
            field[x][y] = value;
            return true;
        }
        return false;
    }

    public void aiTurn() {
        int x, y;
        if (gameOver) return;
        do {
            x = rnd.nextInt(linesCount);
            y = rnd.nextInt(linesCount);
        } while (!setDot(x, y, PL2_DOT));
        checkWin(PL2_DOT);
        checkFieldFull();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < linesCount; i++) {
            g.drawLine(0, i*cellSize, PANEL_SIZE, i*cellSize);
            g.drawLine(i*cellSize, 0, i*cellSize, PANEL_SIZE);
        }
        for (int x = 0; x < linesCount; x++) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(5));
            for (int y = 0; y < linesCount; y++) {
                if (field[x][y] == PL1_DOT) {
                    g.setColor(Color.red);
                    g2.draw(new Line2D.Float(x*cellSize+cellSize/4, y*cellSize+cellSize/4, (x+1)*cellSize-cellSize/4, (y+1)*cellSize-cellSize/4));
                    g2.draw(new Line2D.Float(x*cellSize+cellSize/4, (y+1)*cellSize-cellSize/4, (x+1)*cellSize-cellSize/4, y*cellSize+cellSize/4));

                }
                if (field[x][y] == PL2_DOT) {
                    g.setColor(Color.blue);
                    g.fillOval(x*cellSize + cellSize/4, y*cellSize + cellSize/4, cellSize/2, cellSize/2);
                }
            }
        }

        if(gameOver){
            g.setFont(new Font("Arial", Font.BOLD, 40));
            FontMetrics fm = g.getFontMetrics();
            int totalWidth = fm.stringWidth(gameOverMsg); // width of the gameOverMsg
            int alpha = 127; // 50% transparent
            Color myColour = new Color(128, 128, 128, alpha);
            g.setColor(myColour);
            g.fillRect(0, 220, 500, 70);
            g.setColor(Color.orange);
            g.drawString(gameOverMsg, (500-totalWidth)/2, 270);
        }
    }
}
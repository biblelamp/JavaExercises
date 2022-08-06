package seabattle;

import java.awt.*;
import java.util.Random;

public class BattleField {
    private String[][] field;
    private Random random;
    private SeaBattle.Canvas canvas;
    private Image ship;
    private boolean gameOver;

    public BattleField() {
        field = new String[10][10];
        random = new Random();
    }

    public void setCanvas(SeaBattle.Canvas canvas) {
        this.canvas = canvas;
    }

    public void setShip(Image ship) {
        this.ship = ship;
    }

    public void init() {
        // clear all cells of field
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                field[y][x] = "";
            }
        }
        int x;
        int y;
        // set enemy's ships
        for (int i = 0; i < 5; i++) {
            do {
                x = random.nextInt(5);
                y = random.nextInt(10);
            } while (!isCellValid(x, y));
            field[y][x] = "o";
        }
        // set my ships
        for (int i = 0; i < 5; i++) {
            do {
                x = random.nextInt(5) + 5;
                y = random.nextInt(10);
            } while (!isCellValid(x, y));
            field[y][x] = "x";
        }
        gameOver = false;
    }

    boolean isCellValid(int x, int y) {
        if (!field[y][x].isEmpty()) {
            return false;
        }
        if (y != 0 && !field[y - 1][x].isEmpty()) {
            return false;
        }
        if (y < 9 && !field[y + 1][x].isEmpty()) {
            return false;
        }
        if (x != 0 && !field[y][x - 1].isEmpty()) {
            return false;
        }
        if (x < 9 && !field[y][x + 1].isEmpty()) {
            return false;
        }
        return true;
    }

    public void mouseClick(int x, int y) {
        System.out.println(String.format("x: %d y: %d", x, y));
        // если игра закончилась
        if (gameOver) {
            return;
        }
        // кликать только по вражескому полю
        if (x > 4) {
            return;
        }
        // меняем состояние ячейки
        if (field[y][x].isEmpty()) {
            field[y][x] = ".";
        } else if (field[y][x].equals("x") || field[y][x].equals("o")) {
            field[y][x] += ".";
        } else {
            return;
        }
        // проверка нашей победы
        int count = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                if (field[i][j].length() == 2) {
                    count++;
                }
            }
        }
        if (count == 5) {
            System.out.println("Мы победили!");
            gameOver = true;
            canvas.repaint();
            return;
        }
        // ход оппонента
        int xAI, yAI;
        do {
            xAI = random.nextInt(5) + 5;
            yAI = random.nextInt(10);
        } while (!(field[yAI][xAI].isEmpty() || field[yAI][xAI].equals("x")));
        // отображение хода оппонента
        if (field[yAI][xAI].isEmpty()) {
            field[yAI][xAI] = ".";
        } else if (field[yAI][xAI].equals("x") || field[yAI][xAI].equals("o")) {
            field[yAI][xAI] += ".";
        }
        // проверка победы оппонента
        count = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 5; j < 10; j++) {
                if (field[i][j].length() == 2) {
                    count++;
                }
            }
        }
        if (count == 5) {
            System.out.println("AI победил!");
            gameOver = true;
        }
        canvas.repaint();
    }

    public void paint(Graphics2D g2d, Dimension size) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(3));

        int cellWith = size.width / 10;
        int cellHeigth = size.height / 10;

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                if (!field[y][x].isEmpty()) {
                    int x1 = cellWith * x;
                    int y1 = cellHeigth * y;
                    int dx = cellWith;
                    int dy = cellHeigth;
                    if (field[y][x].equals("o")) {
                        continue;
                    }
                    if (field[y][x].charAt(0) != '.') {
                        g2d.setColor(field[y][x].charAt(0) == 'o'? Color.red : Color.blue);
                        g2d.drawRect(x1, y1, dx, dy);
                        g2d.drawImage(ship, x1 + 1, y1 + 1, cellWith - 1, cellHeigth - 1, null);
                    }
                    if (field[y][x].charAt(0) == '.' || field[y][x].length() == 2) {
                        g2d.setColor(Color.black);
                        g2d.fillOval(x1 + dx / 4, y1 + dy / 4, dx / 2, dy / 2);
                    }
                }
            }
        }
    }
}

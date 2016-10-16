/**
 * Java. Game Battle Ship
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Jctober 16, 2016
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class GameBattleShip extends JFrame {

    final String TITLE_OF_PROGRAM = "Battle Ship";
    final int START_LOCATION = 200;
    final int WINDOW_SIZE = 450;
    final int WINDOW_DX = 7;
    final int WINDOW_DY = 55;
    final int NUMBER_OF_CELLS = 10;
    final int CELL_SIZE = WINDOW_SIZE / NUMBER_OF_CELLS;
    Canvas canvas;
    Random rand = new Random();
    Ships ships = new Ships();

    public static void main(String[] args) {
        new GameBattleShip();
    }

    GameBattleShip() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, WINDOW_SIZE + WINDOW_DX, WINDOW_SIZE + WINDOW_DY);
        setResizable(false);
        // panel for painting
        canvas = new Canvas();
        canvas.setBackground(Color.white);
        // panel for buttons
        JPanel bp = new JPanel();
        bp.setLayout(new GridLayout());
        JButton init = new JButton("New game");
        init.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ships = new Ships();
                canvas.repaint();
            }
        });
        JButton show = new JButton("Show field");
        JButton exit = new JButton("Exit game");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        bp.add(init);
        bp.add(show);
        bp.add(exit);
        add(BorderLayout.CENTER, canvas);
        add(BorderLayout.SOUTH, bp);
        setVisible(true);
    }

    class Ships {
        ArrayList<Ship> ships = new ArrayList<Ship>();
        final int[] pattern = {4, 3, 3, 3, 2, 2, 2, 1, 1, 1, 1};

        Ships() {
            for (int i = 0; i < pattern.length; i++) {
                Ship ship;
                do {
                    int x = rand.nextInt(NUMBER_OF_CELLS);
                    int y = rand.nextInt(NUMBER_OF_CELLS);
                    int position = rand.nextInt(2);
                    ship = new Ship(x, y, pattern[i], position);
                } while (ship.isOutOfField(0, NUMBER_OF_CELLS - 1) || isOverlayOrTouch(ship));
                ships.add(ship);
            }
        }

        boolean isOverlayOrTouch(Ship ctrlShip) {
            for (Ship ship : ships)
                if (ship.isOverlayOrTouch(ctrlShip)) return true;
            return false;
        }

        void paint(Graphics g) {
            for (Ship ship : ships) ship.paint(g);
        }
    }

    class Ship {
        ArrayList<Cell> cells = new ArrayList<Cell>();

        Ship(int x, int y, int length, int position) {
            for (int i = 0; i < length; i++) {
                cells.add(new Cell(x + i * ((position == 1)?0:1), y + i * ((position == 1)?1:0)));
            }
        }

        boolean isOutOfField(int bottom, int top) { // is ship outside the boundary of the field?
            for (Cell cell : cells)
                if (cell.getX() < bottom || cell.getX() > top || cell.getY() < bottom || cell.getY() > top) return true;
            return false;
        }

        boolean isOverlayOrTouch(Ship ctrlShip) { // is ship overlay or touch other ships
            for (Cell cell : cells)
                if (ctrlShip.isOverlayOrTouchCell(cell)) return true;
            return false;
        }

        boolean isOverlayOrTouchCell(Cell ctrlCell) {
            for (Cell cell : cells)
                for (int dx = -1; dx < 2; dx++)
                    for (int dy = -1; dy < 2; dy++)
                        if (ctrlCell.getX() == cell.getX() + dx && ctrlCell.getY() == cell.getY() + dy) return true;
            return false;
        }

        void paint(Graphics g) {
            for (Cell cell : cells) cell.paint(g);
        }
    }

    class Cell {
        int x, y;
        Color color = Color.gray;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getX() { return x; }
        int getY() { return y; }

        void paint(Graphics g) {
            g.setColor(color);
            g.fill3DRect(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, CELL_SIZE, true);
        }
    }

    class Canvas extends JPanel { // for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int i = 0; i < NUMBER_OF_CELLS - 1; i++) {
                g.drawLine(0, (i + 1)*CELL_SIZE, WINDOW_SIZE, (i + 1)*CELL_SIZE);
                g.drawLine((i + 1)*CELL_SIZE, 0, (i + 1)*CELL_SIZE, WINDOW_SIZE);
            }
            ships.paint(g);
        }
    }
}
/**
 * Java. Game TicTacToe with GUI
 *
 * @author Sergey Iryupin
 * @version 0.1 dated September 16, 2016
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class GameTicTacToe extends JFrame {

    final String TITLE_OF_PROGRAM = "TicTacToe";
    final int START_LOCATION = 200;
    final int WINDOW_SIZE = 300;
    final int FIELD_SIZE = 3;
    final int CELL_SIZE = WINDOW_SIZE / FIELD_SIZE;
    char[][] field = new char[FIELD_SIZE][FIELD_SIZE];
    Canvas canvas;

    public static void main(String[] args) {
        new GameTicTacToe();
    }

    GameTicTacToe() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, WINDOW_SIZE + 7, WINDOW_SIZE + 55);
        setResizable(false);
        // panel for painting
        canvas = new Canvas();
        canvas.setBackground(Color.white);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = e.getX() / CELL_SIZE;
                int y = e.getY() / CELL_SIZE;
                System.out.println(x + ":" + y);
            }
        });
        // panel for buttons
        JPanel bp = new JPanel();
        bp.setLayout(new GridLayout(1, 2));
        JButton start = new JButton("New game");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("New game");
            }
        });
        JButton exit = new JButton("Exit game");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        bp.add(start);
        bp.add(exit);
        // add both panels
        add(BorderLayout.CENTER, canvas);
        add(BorderLayout.SOUTH, bp);
        setVisible(true);
    }

    class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int i = 0; i < FIELD_SIZE - 1; i++) {
                g.drawLine(0, (i + 1)*CELL_SIZE, WINDOW_SIZE, (i + 1)*CELL_SIZE);
                g.drawLine((i + 1)*CELL_SIZE, 0, (i + 1)*CELL_SIZE, WINDOW_SIZE);
            }
        }
    }
}
package lesson31.homework;

import lesson11.TicTacToe;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwingTicTacToe extends JFrame {

    private boolean gameOver;

    public static void main(String[] args) {
        new SwingTicTacToe();
    }

    public SwingTicTacToe() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        TicTacToe.initTable();
        gameOver = false;

        Canvas canvas = new Canvas();
        canvas.setBackground(Color.white);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / (getWidth() / 3);
                int y = e.getY() / (getHeight() / 3);
//                System.out.println("x:" + e.getX() + " y:" + e.getY());
//                System.out.println("x:" + x + " y:" + y);
                if (TicTacToe.turnHuman(x, y) && !gameOver) {
                    if (TicTacToe.isWin(TicTacToe.CHAR_X)) {
                        System.out.println("YOU WON!");
                        gameOver = true;
                    }
                    if (TicTacToe.isTableFill()) {
                        System.out.println("Sorry, DRAW!");
                        gameOver = true;
                    }
                    if (!gameOver) {
                        TicTacToe.turnAI();
                        if (TicTacToe.isWin(TicTacToe.CHAR_O)) {
                            System.out.println("AI WON!");
                            gameOver = true;
                        }
                        if (TicTacToe.isTableFill()) {
                            System.out.println("Sorry, DRAW!");
                            gameOver = true;
                        }
                    }
                    canvas.repaint();
                }
            }
        });

        JButton btnInit = new JButton("Init");
        JButton btnExit = new JButton("Exit");

        btnInit.addActionListener(e -> {
            TicTacToe.initTable();
            gameOver = false;
            canvas.repaint();
        });
        btnExit.addActionListener(e -> System.exit(0));

        Panel btnPanel = new Panel();
        btnPanel.setLayout(new GridLayout());
        btnPanel.add(btnInit);
        btnPanel.add(btnExit);

        add(btnPanel, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);

        setVisible(true);
    }

    private class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            int width = getWidth();
            int heigh = getHeight();
            int cellWidth = width / 3;
            int cellHeight = heigh / 3;
            g.setColor(Color.lightGray);
            g.drawLine(0, 1, width, 1);
            for (int i = 0; i < 2; i++) {
                g.drawLine(0, cellHeight * (i + 1), width, cellHeight * (i + 1));
                g.drawLine(cellWidth * (i + 1), 1, cellWidth * (i + 1), heigh);
            }
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    if (TicTacToe.table[y][x] == 'x') {
                        g.setColor(Color.blue);
                        g.drawLine(cellWidth * x, cellHeight * y, cellWidth * (x + 1), cellHeight * (y + 1));
                        g.drawLine(cellWidth * (x + 1), cellHeight * y, cellWidth * x, cellHeight * (y + 1));
                    }
                    if (TicTacToe.table[y][x] == 'o') {
                        g.setColor(Color.red);
                        g.drawOval(cellWidth * x, cellHeight * y, cellWidth, cellHeight);
                    }
                }
            }
        }
    }
}

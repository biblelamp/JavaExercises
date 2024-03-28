package lesson31.homework;

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
    private char[][] table = new char[3][3];

    public static void main(String[] args) {
        new SwingTicTacToe();
    }

    public SwingTicTacToe() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        Canvas canvas = new Canvas();
        canvas.setBackground(Color.white);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int arrayX = e.getX() / (getWidth() / 3);
                int arrayY = e.getY() / (getHeight() / 3);
                System.out.println("x:" + e.getX() + " y:" + e.getY());
                System.out.println("x:" + arrayX + " y:" + arrayY);
                table[arrayX][arrayY] = 'x';
                canvas.repaint();
            }
        });

        JButton btnInit = new JButton("Init");
        JButton btnExit = new JButton("Exit");

        btnInit.addActionListener(e -> {});
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
            g.setColor(Color.lightGray);
            g.drawLine(0, 1, getWidth(), 1);
            for (int i = 0; i < 2; i++) {
                g.drawLine(0, getHeight() / 3 * (i + 1), getWidth(), getHeight() / 3 * (i + 1));
                g.drawLine(getWidth() / 3 * (i + 1), 1, getWidth() / 3 * (i + 1), getHeight());
            }
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    if (table[x][y] == 'x') {
                        // TODO нарисовать крестик
                    }
                    if (table[x][y] == 'o') {
                        // TODO нарисовать нолик
                    }
                }
            }
        }
    }
}

package lesson4;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
    [0,0][1,0][2,0]
    [0,1][1,1][2,1]
    [0,2][1,2][2,2]
 */

public class TicTacToeSwing extends JFrame {
    public static void main(String[] args) {
        new TicTacToeSwing();
    }

    public TicTacToeSwing() {
        setTitle("TicTacToe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setResizable(false);

        Canvas canvas = new Canvas();
        canvas.setBackground(Color.white);
        canvas.setPreferredSize(new Dimension(600, 600));
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(e.getX() + ":" + e.getY());
                int x = e.getX() / 200;
                int y = e.getY() / 200;
                System.out.println(x + ":" + y);
            }
        });

        add(canvas);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.lightGray);
            g.drawLine(200, 0, 200, 600);
            g.drawLine(400, 0, 400, 600);
            g.drawLine(0, 200, 600, 200);
            g.drawLine(0, 400, 600, 400);
        }
    }
}

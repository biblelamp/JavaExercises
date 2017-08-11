/**
 * Java. Level 1. Drawing graphs of simple functions
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Aug 11, 2017
 */
import javax.swing.*;
import java.awt.*;

class DrawGraphs extends JFrame {

    final String TITLE_OF_PROGRAM = "Drawing graphs of simple functions";
    final int START_LOCATION = 200;
    final int WINDOW_WIDTH = 550;
    final int WINDOW_HEIGTH = 350;

    Canvas canvas; // JPanel for painting

    public static void main(String[] args) {
        new DrawGraphs();
    }

    DrawGraphs() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_HEIGTH);
        setResizable(false);
        canvas = new Canvas();
        canvas.setBackground(Color.white);
        add(BorderLayout.CENTER, canvas);
        setVisible(true);
    }

    class Canvas extends JPanel { // for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);

            // get the real size
            Dimension d = this.getSize();
            int width = (int) d.getWidth();
            int height = (int) d.getHeight();

            // drawing coordinate lines
            g.drawLine(5, height / 2, width - 6, height / 2);
            g.drawLine(width / 2, 5, width / 2, height - 6);
            g.drawString("X", width - 15, height / 2 - 10);
            g.drawString("Y", width / 2 - 15, 20);

            // converting degrees to radians
            // radians = degrees * Math.PI/180

            int k = 1; // coefficient of stretching horizontally
            g.setColor(Color.blue);
            for (int i = 10, y = 0; i < width - 10; i++) {
                y = (int) (Math.sin(i * Math.PI/180 * k) * 75) + height / 2;
                g.drawRect(i, y, 0, 0);
            }

            g.setColor(Color.magenta);
            for (int i = 10, y = 0; i < width - 10; i++) {
                y = (int) (Math.cos(i * Math.PI/180) * 75) + height / 2;
                g.drawRect(i, y, 0, 0);
            }
        }
    }
}
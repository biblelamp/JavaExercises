/**
 * Java. Level 1. Drawing graphs of simple functions
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Aug 13, 2017
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DrawGraphs extends JFrame implements KeyListener {

    final String TITLE_OF_PROGRAM = "Drawing graphs of simple functions";
    final int START_LOCATION = 200;
    final int WINDOW_WIDTH = 550;
    final int WINDOW_HEIGTH = 350;
    final int LEFT = 37;    // key codes
    final int UP = 38;
    final int RIGHT = 39;
    final int DOWN = 40;

    Canvas canvas; // JPanel for painting
    float stretchX = 1.2f;
    int stretchY = 75;

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
        addKeyListener(this);
        setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent event) { }

    @Override
    public void keyTyped(KeyEvent event) { }

    @Override
    public void keyReleased(KeyEvent event) {
        switch (event.getKeyCode()) {
            case LEFT: stretchX -= 0.1;
                canvas.repaint();
                break;
            case RIGHT: stretchX += 0.1;
                canvas.repaint();
                break;
            case UP: stretchY += 5;
                canvas.repaint();
                break;
            case DOWN: stretchY -= 5;
                canvas.repaint();
                break;
        }
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

            g.setColor(Color.blue);
            for (int x = -(width / 2 - 10), y = 0; x < width /2 - 10; x++) {
                y = (int) (Math.sin(x * Math.PI/180 * stretchX) * stretchY);
                g.drawRect(x + width / 2, y + height / 2, 0, 0);
            }
        }
    }
}
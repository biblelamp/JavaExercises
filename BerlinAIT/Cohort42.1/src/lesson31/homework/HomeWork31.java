package lesson31.homework;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;

/**
 * AIT-TR, cohort 42.1, Java Basic, Homework #31
 *
 * @author Sergey Iryupin
 * @version 27,28-Mar-24
 */
public class HomeWork31 extends JFrame {
    private ShapeForm shapeForm = ShapeForm.CIRCLES;
    private Shapes shapes = new Shapes();

    public static void main(String[] args) {
        new HomeWork31();
    }

    public HomeWork31()  {
        setTitle("Hello, Swing!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        Canvas canvas = new Canvas();
        canvas.setBackground(Color.white);

        JButton btnCircles = new JButton("Circles");
        JButton btnRectangles = new JButton("Rectangles");
        JButton btnTriangles = new JButton("Triagles");
        JButton btnExit = new JButton("Exit");

        btnCircles.addActionListener(e -> {
            shapeForm = ShapeForm.CIRCLES;
            canvas.repaint();
        });

        btnRectangles.addActionListener(e -> {
            shapeForm = ShapeForm.RECTANGLES;
            canvas.repaint();
        });

        btnTriangles.addActionListener(e -> {
            shapeForm = ShapeForm.TRIANGLES;
            canvas.repaint();
        });

        btnExit.addActionListener(e -> System.exit(0));

        Panel btnPanel = new Panel();
        btnPanel.setLayout(new GridLayout());
        btnPanel.add(btnCircles);
        btnPanel.add(btnRectangles);
        btnPanel.add(btnTriangles);
        btnPanel.add(btnExit);

        add(btnPanel, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);

        setVisible(true);
    }

    private class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            shapes.paint(g, shapeForm, this);
        }
    }
}

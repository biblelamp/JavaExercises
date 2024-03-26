package lesson31;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #31
 *
 * @version 25-Mar-24
 */
public class Lesson31 extends JFrame {
    private Random random;
    private final Color[] COLORS = {Color.red, Color.green, Color.blue, Color.yellow, Color.magenta};
    private int CIRCLE_COUNT = 100;

    public static void main(String[] args) {
        new Lesson31();
    }

    public Lesson31()  {
        setTitle("Hello, Swing!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        random = new Random();

        Canvas canvas = new Canvas();
        canvas.setBackground(Color.white);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(e.getX() + ", " + e.getY());
                canvas.repaint();
            }
        });

        JButton btnDraw = new JButton("Draw");
        JButton btnExit = new JButton("Exit");

        btnDraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.repaint();
            }
        });

        btnExit.addActionListener(e -> System.exit(0));

        Panel btnPanel = new Panel();
        btnPanel.setLayout(new GridLayout());
        btnPanel.add(btnDraw);
        btnPanel.add(btnExit);

        add(btnPanel, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);

        setVisible(true);
    }

    private class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int i = 0; i < CIRCLE_COUNT; i++) {
                int d = random.nextInt(20) + 60;
                int x = random.nextInt(getWidth() - d);
                int y = random.nextInt(getHeight() - d);
                Color color = COLORS[random.nextInt(COLORS.length)];
                g.setColor(color);
                g.fillOval(x, y, d, d);
                g.setColor(Color.black);
                g.drawOval(x, y, d, d);
            }
        }
    }
}

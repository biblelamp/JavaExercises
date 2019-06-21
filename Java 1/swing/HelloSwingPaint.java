import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

// @see https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html

class HelloSwingPaint extends JFrame {
    public static void main(String[] args) {
        new HelloSwingPaint();
    }

    HelloSwingPaint() {
        setTitle("Hello Swing!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        Canvas canvas = new Canvas();
        canvas.setBackground(Color.white);

        Button btnExit = new Button("Exit");
        btnExit.addActionListener(e -> System.exit(0));

        add(canvas, BorderLayout.CENTER);
        add(btnExit, BorderLayout.SOUTH);
        setVisible(true);
    }

   class Canvas extends JPanel { // for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);

            g.setColor(Color.blue);
            g.drawLine(50, 50, 150, 150);

            g.setColor(Color.red);
            g.drawOval(110, 125, 80, 50);

            g.setColor(Color.black);
            g.drawRect(150, 155, 80, 50);

            g.setColor(Color.green);
            g.drawPolygon(new int[]{100, 200, 150, 100}, new int[]{100, 100, 50, 100}, 4);
        }
    }
}
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SimpleAnimation {

    int x = 50;
    int y = 40;
    int dx = 1;
    int dy = 1;
    final int diameter = 40;
    final int fieldWidth = 400;
    final int fieldHeight = 300;

    public static void main(String[] args) {
        SimpleAnimation gui = new SimpleAnimation();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyDrawPanel drawPanel = new MyDrawPanel();

        frame.getContentPane().add(drawPanel);
        frame.setSize(fieldWidth, fieldHeight);
        frame.setVisible(true);

        while (true) {

            if ((x + dx + diameter >= fieldWidth) || (x + dx <= 0))
                dx = -dx;
            if ((y + dy + diameter >= fieldHeight) || (y + dy <= 0))
                dy = -dy;

            x += dx;
            y += dy;
            drawPanel.repaint();

            try {
                Thread.sleep(10);
            } catch(Exception ex) { }
        }
    }

    class MyDrawPanel extends JPanel {

        public void paintComponent(Graphics g) {
            Color[] color = new Color[]{ Color.green, Color.blue, Color.red, Color.yellow };
            Random rand = new Random();

            g.setColor(Color.white);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());

            g.setColor(color[rand.nextInt(color.length)]);
            g.fillOval(x, y, diameter, diameter);
        }
    }
}
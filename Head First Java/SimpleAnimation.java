import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SimpleAnimation {

    int x = 50;
    int y = 50;

    public static void main(String[] args) {
        SimpleAnimation gui = new SimpleAnimation();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyDrawPanel drawPanel = new MyDrawPanel();

        frame.getContentPane().add(drawPanel);
        frame.setSize(300, 300);
        frame.setVisible(true);

        for (int i = 0; i < 130; i++) {

            x++;
            y++;
            drawPanel.repaint();

            try {
                Thread.sleep(20);
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
            g.fillOval(x, y, 40, 40);
        }
    }
}
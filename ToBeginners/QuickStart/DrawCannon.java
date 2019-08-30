import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DrawCannon extends JFrame {
    public static void main(String[] args) {
        new DrawCannon();
    }

    DrawCannon() {
        setTitle("Cannon");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);

        Canvas canvas = new Canvas();
        canvas.setBackground(Color.white);

        JTextField command = new JTextField();
        JButton fire = new JButton("Fire");
        fire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //text.append(calculateShot(command.getText()));
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(command);
        panel.add(fire);

        add(canvas, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    class Canvas extends JPanel { // for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            // 
        }
    }

}
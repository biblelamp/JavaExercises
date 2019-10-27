import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class SwingCannon extends JFrame {
    public static void main(String[] args) {
        new SwingCannon();
    }

    SwingCannon() {
        setTitle("Cannon");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);

        JTextArea text = new JTextArea();
        JScrollPane scroll = new JScrollPane(text);
        JLabel label = new JLabel("  Set the agle (0..45)  ");
        JTextField command = new JTextField();
        JButton fire = new JButton("Fire");
        fire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.append(calculateShot(command.getText()));
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(label);
        panel.add(command);
        panel.add(fire);

        add(scroll, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    String calculateShot(String data) {
        float g = 9.81f;             // acceleration of gravity, m/c2
        int speed = 150;             // starting speed of cannon shell, m/c
        int damageRadius = 5;        // damage radius
        double distanceTarget = 850; // distance to target

        int angle = Integer.parseInt(data);
        double distance = Math.pow(speed, 2) * Math.sin(Math.toRadians(angle * 2)) / (2 * g);
        double difference = distanceTarget - distance;

        if (Math.abs(difference) < damageRadius) {
            return String.format("%d: Target hit", angle);
        } else {
            String result = (difference > 0)? "Under" : "Over";
            return String.format("%d: %sshoot %.2fm\n", angle, result, Math.abs(difference));
        }
    }
}
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

        JTextArea text = new JTextArea();
        JScrollPane scroll = new JScrollPane(text);
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
        panel.add(command);
        panel.add(fire);

        add(BorderLayout.CENTER, scroll);
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
            return "Target hit";
        } else {
            String result = (difference > 0)? "Under" : "Over";
            return String.format("%d: %sshoot %.2f\n", angle, result, Math.abs(difference));
        }
    }

}
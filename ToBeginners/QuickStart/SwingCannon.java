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
        JTextField command = new JTextField();
        JButton fire = new JButton("Fire");
        fire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.append("Fire!\n");
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
}
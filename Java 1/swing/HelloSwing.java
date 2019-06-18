import javax.swing.JFrame;
import java.awt.Button;
import java.awt.BorderLayout;

class HelloSwing extends JFrame {
    public static void main(String[] args) {
        new HelloSwing();
    }

    HelloSwing() {
        setTitle("Hello Swing!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 250);
        setLocationRelativeTo(null);

        Button btnExit = new Button("Exit");
        btnExit.addActionListener(e -> System.exit(0));

        add(btnExit, BorderLayout.SOUTH);
        setVisible(true);
    }
}
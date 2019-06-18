import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class HelloSwingAction extends JFrame {
    public static void main(String[] args) {
        new HelloSwingAction();
    }

    HelloSwingAction() {
        setTitle("Hello Swing!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.addMouseListener(new MouseAdapter () {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("MousePos: " + e.getX() + ":" + e.getY());
            }
        });

        Button btnRight = new Button("Right");
        btnRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button Right pressed...");
            }
        });

        Button btnExit = new Button("Exit");
        btnExit.addActionListener(e -> System.exit(0));

        add(panel, BorderLayout.CENTER);
        add(btnRight, BorderLayout.EAST);
        add(btnExit, BorderLayout.SOUTH);
        setVisible(true);
    }
}
import javax.swing.JFrame;
import java.awt.Button;
import java.awt.BorderLayout;

class HelloSwingLayout extends JFrame {
    public static void main(String[] args) {
        new HelloSwingLayout();
    }

    HelloSwingLayout() {
        setTitle("Hello Swing!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 250);
        setLocationRelativeTo(null);

        Button btnExit = new Button("SOUTH [Exit]");
        btnExit.addActionListener(e -> System.exit(0));

        add(new Button("NORTH"), BorderLayout.NORTH);
        add(new Button("WEST"), BorderLayout.WEST);
        add(new Button("EAST"), BorderLayout.EAST);
        add(new Button("CENTER"), BorderLayout.CENTER);
        add(btnExit, BorderLayout.SOUTH);
        setVisible(true);
    }
}
import javax.swing.JFrame;

class HelloSwing extends JFrame {
    public static void main(String[] args) {
        new HelloSwing();
    }

    HelloSwing() {
        setTitle("Hello Swing");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
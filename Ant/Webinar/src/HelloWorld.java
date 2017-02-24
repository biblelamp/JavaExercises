import javax.swing.*;
import java.awt.*;

public class HelloWorld extends JFrame {

    public static void main(String[] args) {
        new HelloWorld();
    }

    HelloWorld() {
        setTitle(sayHello());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(200, 200, 300, 200);
        JLabel label = new JLabel(sayHello(), SwingConstants.CENTER);
        label.setFont(new Font("", Font.BOLD, 24));
        add(label);
        setVisible(true);
    }

    String sayHello() {
        return "Hello, World!";
    }
}
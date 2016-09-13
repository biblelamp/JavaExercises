/**
 * Java. Level 1. Lesson 7. Exercises in the classroom
 *
 * @author Sergey Iryupin
 * @version of September 13, 2016
 */
import javax.swing.*;
import java.awt.*;

public class GUIExamples {

    public static void main(String[] args) {
        Form w = new Form();
        FormBL wbl = new FormBL();
    }

}

class Form extends JFrame { // necessary javax.swing.*
    Form() {
        setTitle("Test Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200, 200, 500, 300); // start x, start y, width, height
        setVisible(true);
    }
}

class FormBL extends JFrame { // necessary java.awt.*
    FormBL() {
        setTitle("BorderLayout Demo");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(250, 250, 400, 400);
        JButton[] jbs = new JButton[5];
        for (int i = 0; i < 5; i++) {
            jbs[i] = new JButton("#" + i);
        }
        setLayout(new BorderLayout());
        add(jbs[0], BorderLayout.EAST); // adding button no the form
        add(jbs[1], BorderLayout.WEST);
        add(jbs[2], BorderLayout.SOUTH);
        add(jbs[3], BorderLayout.NORTH);
        add(jbs[4], BorderLayout.CENTER);
        setVisible(true);
    }
}
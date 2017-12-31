/**
 * Java. Level 1. Lesson 7. Exercises in the classroom
 *
 * @author Sergey Iryupin
 * @version of September 13, 2016
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GUIExamples {

    public static void main(String[] args) {
        Form w = new Form();
        FormBL wbl = new FormBL();
        FormWithListeners wwl = new FormWithListeners();
    }

}

class Form extends JFrame { // necessary javax.swing.*
    Form() {
        setTitle("Test Window");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(200, 200, 400, 300); // start x, start y, width, height
        setVisible(true);
    }
}

class FormBL extends JFrame { // necessary java.awt.*
    FormBL() {
        setTitle("BorderLayout Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(250, 250, 400, 300);

        // create buttons
        JButton[] jbs = new JButton[5];
        for (int i = 0; i < 5; i++) {
            jbs[i] = new JButton("#" + i);
        }

        // set the layout elements
        setLayout(new BorderLayout());

        // adding buttons no the form
        add(jbs[0], BorderLayout.EAST);
        add(jbs[1], BorderLayout.WEST);
        add(jbs[2], BorderLayout.SOUTH);
        add(jbs[3], BorderLayout.NORTH);
        add(jbs[4], BorderLayout.CENTER);
        setVisible(true);
    }
}

class FormWithListeners extends JFrame { // necessary java.awt.event.*
    int x, y;

    FormWithListeners() {
        setTitle("BorderLayout Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 300);

        // create buttons
        JButton btnFist = new JButton("First");
        JButton btnSecond = new JButton("Second");

        // add buttons listeners
        btnFist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button first");
            }
        });
        btnSecond.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button second");
            }
        });

        // create canvas with MouseAdapter
        Canvas canvasPanel = new Canvas();
        canvasPanel.setBackground(Color.white);
        canvasPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                x = e.getX();
                y = e.getY();
                canvasPanel.repaint();
                System.out.println(x + ":" + y);
            }
        });

        // panel with button
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnFist);
        btnPanel.add(btnSecond);

        // add btnPanel and canvas
        getContentPane().add(BorderLayout.CENTER, canvasPanel);
        getContentPane().add(BorderLayout.SOUTH, btnPanel);
        setVisible(true);
    }

    class Canvas extends JPanel { // my canvas for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawOval(x, y, 50, 50);
        }
    }
}
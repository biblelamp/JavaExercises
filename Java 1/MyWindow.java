import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JFrame {

    public MyWindow() {
        setSize(505, 555);
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(200, 200);
        Map map = new Map(5);
        JPanel jpButtom = new JPanel(new GridLayout());
        add(map, BorderLayout.CENTER);
        add(jpButtom, BorderLayout.SOUTH);

        JButton jbStart = new JButton("Start new game");
        JButton jbEnd = new JButton("End game");
        jbEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(e.getSource().toString());
                System.exit(0);
            }
        });

        jpButtom.add(jbStart);
        jpButtom.add(jbEnd);

        setVisible(true);
    }
}
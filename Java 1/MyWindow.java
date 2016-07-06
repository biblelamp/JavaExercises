import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JFrame{

    Map map;
    OptionGame opt;
    final String hvc = "Human vs Computer";
    final String hvh = "Human vs Human";

    public MyWindow() {
        setSize(500,555);
        setTitle("Tic Tac Toe: " + hvc);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(800,200);
        map = new Map(3);

        JPanel jpButtom = new JPanel(new GridLayout());
        add(map, BorderLayout.CENTER);
        add(jpButtom, BorderLayout.SOUTH);

        opt = new OptionGame(this);

        JButton jbStart = new JButton("Start new game");
        jbStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEnabled(false);
                opt.setVisible(true);
            }
        });
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

    public void createMap(int size, boolean mode) {
       setTitle("Tic Tac Toe: " + ((mode) ? hvc : hvh));
       map.startNewGame(size, mode);
    }
}
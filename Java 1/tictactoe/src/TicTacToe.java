import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Java 1. Lesson 8. Game Tic Tac Toe
 * Class: Main-Class
 *
 * @author Sergey Iryupin
 * @version 0.3.5 dated Dec 27, 2018
 */
class TicTacToe extends JFrame {

    final String TITLE_OF_PROGRAM = "Tic Tac Toe";
    final int WINDOW_SIZE = 330;
    final int WINDOW_DX = 7;
    final int WINDOW_DY = 55;
    final int FIELD_SIZE = 3;
    final int CELL_SIZE = WINDOW_SIZE / FIELD_SIZE;
    final String BTN_INIT = "New game";
    final String BTN_EXIT = "Exit";

    Panel panel;        // panel for rendering (drawing)
    Field field;        // declare a field of game object
    Human human;        // declare a human object
    AI ai;              // declare a Artificial intelligence object

    public static void main(String args[]) {
        new TicTacToe();
    }

    TicTacToe() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_SIZE + WINDOW_DX, WINDOW_SIZE + WINDOW_DY);
        setLocationRelativeTo(null); // to the center
        setResizable(false);

        field = new Field(FIELD_SIZE, CELL_SIZE);
        human = new Human(field.getHumanDot());
        ai = new AI(field.getAIDot());

        panel = new Panel();
        panel.setBackground(Color.white);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                human.turn(e.getX()/CELL_SIZE, e.getY()/CELL_SIZE, field, ai);
                panel.repaint();
                if (field.isGameOver())
                    JOptionPane.showMessageDialog(
                        TicTacToe.this, field.getGameOverMsg());
            }
        });
        JButton init = new JButton(BTN_INIT);
        init.addActionListener(e -> { // lambda version
            field.init();
            panel.repaint();
        });
        JButton exit = new JButton(BTN_EXIT);
        //exit.addActionListener(e -> System.exit(0));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new GridLayout()); // for panel of buttons
        panelBtn.add(init);
        panelBtn.add(exit);

        //setLayout(new BorderLayout()); // by default
        add(panelBtn, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    class Panel extends JPanel { // for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            field.paint((Graphics2D) g);
        }
    }
}

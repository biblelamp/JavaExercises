/*
 * Java 1. Lesson 8. Game Tic Tac Toe
 * Class: Main-Class
 *
 * @author Sergey Iryupin
 * @version 0.2 dated March 12, 2017
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TicTacToe extends JFrame {

    final String TITLE_OF_PROGRAM = "Tic Tac Toe";
    final int START_POSITION = 300;
    final int WINDOW_SIZE = 300;
    final int WINDOW_DX = 17;
    final int WINDOW_DY = 65;
    final int FIELD_SIZE = 3;
    final int CELL_SIZE = WINDOW_SIZE / FIELD_SIZE;
    final String BTN_INIT = "New game";
    final String BTN_EXIT = "Exit";
    final char HUMAN_DOT = 'x';
    final char AI_DOT = 'o';

    Canvas canvas = new Canvas();
    Field field = new Field(FIELD_SIZE, WINDOW_SIZE, HUMAN_DOT, AI_DOT);
    Human human = new Human(HUMAN_DOT);
    AI ai = new AI(AI_DOT);

    public static void main(String args[]) {
        new TicTacToe();
    }

    TicTacToe() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_POSITION, START_POSITION, WINDOW_SIZE + WINDOW_DX, WINDOW_SIZE + WINDOW_DY);

        canvas.setBackground(Color.white);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = e.getX()/CELL_SIZE;
                int y = e.getY()/CELL_SIZE;
                human.turn(x, y, field, ai);
                canvas.repaint();
                if (field.getGameOverMsg() != null)
                    JOptionPane.showMessageDialog(TicTacToe.this, field.getGameOverMsg());
            }
        });
        JButton init = new JButton(BTN_INIT);
        init.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                field.init();
                canvas.repaint();
            }
        });
        JButton exit = new JButton(BTN_EXIT);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel bp = new JPanel();
        bp.setLayout(new GridLayout());
        bp.add(init);
        bp.add(exit);

        setLayout(new BorderLayout());
        add(bp, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);
        setVisible(true);
    }

    class Canvas extends JPanel { // for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            field.paint(g);
        }
    }
}
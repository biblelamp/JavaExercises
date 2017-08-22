/**
 * Java. Game Battle Ship
 * Class: GameBattleShip Main-Class
 *
 * @author Sergey Iryupin
 * @version 0.3 dated Aug 22, 2017
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class GameBattleShip extends JFrame {

    final String TITLE_OF_PROGRAM = "Battle Ship";
    final int FIELD_SIZE = 10;
    final int AI_PANEL_SIZE = 400;
    final int AI_CELL_SIZE = AI_PANEL_SIZE / FIELD_SIZE;
    final int HUMAN_PANEL_SIZE = AI_PANEL_SIZE / 2;
    final int HUMAN_CELL_SIZE = HUMAN_PANEL_SIZE / FIELD_SIZE;
    final String BTN_INIT = "New game";
    final String BTN_EXIT = "Exit";
    final String YOU_WON = "YOU WON!";
    final String AI_WON = "AI WON!";
    final int MOUSE_BUTTON_LEFT = 1; // for mouse listener
    final int MOUSE_BUTTON_RIGHT = 3;

    JTextArea board; // for logging
    Canvas leftPanel, humanPanel; // for game fields
    Ships aiShips, humanShips; // set of human's and AI ships
    Shots humanShots, aiShots; // set of shots from human and AI
    Random random;
    boolean gameOver;

    public static void main(String[] args) {
        new GameBattleShip();
    }

    GameBattleShip() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        leftPanel = new Canvas(); // panel for AI ships
        leftPanel.setPreferredSize(new Dimension(AI_PANEL_SIZE, AI_PANEL_SIZE));
        leftPanel.setBackground(Color.white);
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
        leftPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = e.getX()/AI_CELL_SIZE; // coordinates transformation
                int y = e.getY()/AI_CELL_SIZE;
                if (e.getButton() == MOUSE_BUTTON_LEFT && !gameOver) // left mouse
                    if (!humanShots.hitSamePlace(x, y)) {
                        humanShots.add(x, y, true);
                        if (aiShips.checkHit(x, y)) { // human hit the target
                            if (!aiShips.checkSurvivors()) {
                                board.append("\n" + YOU_WON);
                                gameOver = true;
                            }
                        } else shootsAI(); // human missed - AI will shoot
                        leftPanel.repaint();
                        humanPanel.repaint();
                        board.setCaretPosition(board.getText().length());
                    }
                if (e.getButton() == MOUSE_BUTTON_RIGHT) { // right mouse
                    Shot label = humanShots.getLabel(x, y);
                    if (label != null) 
                        humanShots.removeLabel(label);
                    else 
                        humanShots.add(x, y, false);
                    leftPanel.repaint();
                }
            }
        });

        humanPanel = new Canvas(); // panel for human ships
        humanPanel.setPreferredSize(new Dimension(HUMAN_PANEL_SIZE, HUMAN_PANEL_SIZE));
        humanPanel.setBackground(Color.white);
        humanPanel.setBorder(BorderFactory.createLineBorder(Color.blue));

        JButton init = new JButton(BTN_INIT); // init button
        init.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                init();
                leftPanel.repaint();
                humanPanel.repaint();
            }
        });
        JButton exit = new JButton(BTN_EXIT); // exit game button
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        board = new JTextArea(); // scoreboard
        board.setEditable(false);
        JScrollPane scroll = new JScrollPane(board); // scroll for board

        JPanel buttonPanel = new JPanel(); // panel for button
        buttonPanel.setLayout(new GridLayout());
        buttonPanel.add(init);
        buttonPanel.add(exit);

        JPanel rightPanel = new JPanel();         // panel for human ships,
        rightPanel.setLayout(new BorderLayout()); //  scoreboard and buttons

        rightPanel.add(humanPanel, BorderLayout.NORTH);
        rightPanel.add(scroll, BorderLayout.CENTER);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        add(leftPanel);
        add(rightPanel);
        pack();
        setLocationRelativeTo(null); // to the center
        setVisible(true);
        init();
    }

    void init() { // init all game object
        aiShips = new Ships(FIELD_SIZE, AI_CELL_SIZE, true);
        humanShips = new Ships(FIELD_SIZE, HUMAN_CELL_SIZE, false);
        aiShots = new Shots(HUMAN_CELL_SIZE);
        humanShots = new Shots(AI_CELL_SIZE);
        board.setText(BTN_INIT);
        gameOver = false;
        random = new Random();
    }

    void shootsAI() { // AI shoots randomly
        int x, y;
        do {
            x = random.nextInt(FIELD_SIZE);
            y = random.nextInt(FIELD_SIZE);
        } while (aiShots.hitSamePlace(x, y));
        aiShots.add(x, y, true);
        if (!humanShips.checkHit(x, y)) { // AI missed
            board.append(
                "\n" + (x + 1) + ":" + (y + 1) + " AI missed.");
            return;
        } else { // AI hit the target - AI can shoot again
            board.append(
                "\n" + (x + 1) + ":" + (y + 1) + " AI hit the target.");
            board.setCaretPosition(board.getText().length());
            if (!humanShips.checkSurvivors()) {
                board.append("\n" + AI_WON);
                gameOver = true;
            } else
                shootsAI();
        }
    }

    class Canvas extends JPanel { // for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            int cellSize = (int) getSize().getWidth() / FIELD_SIZE;
            g.setColor(Color.lightGray);
            for (int i = 1; i < FIELD_SIZE; i++) {
                g.drawLine(0, i*cellSize, FIELD_SIZE*cellSize, i*cellSize);
                g.drawLine(i*cellSize, 0, i*cellSize, FIELD_SIZE*cellSize);
            }
            if (cellSize == AI_CELL_SIZE) {
                humanShots.paint(g);
                aiShips.paint(g);
            } else {
                aiShots.paint(g);
                humanShips.paint(g);
            }
        }
    }
}
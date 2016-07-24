/**
 * Java. Conway's Game of Life
 *
 * @author Sergey Iryupin
 * @version 0.1 dated 24 July 2016
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GameOfLife {

    final String nameOfGame = "Conway's Game of Life";
    final int LIFE_SIZE = 50;
    final int POINT_RADIUS = 10;
    final int FIELD_SIZE = (LIFE_SIZE + 1) * POINT_RADIUS;
    final int BTN_PANAL_HEIGHT = 57;
    final int START_LOCATION = 200;
    boolean[][] lifeGeneration = new boolean[LIFE_SIZE][LIFE_SIZE];
    boolean[][] nextGeneration = new boolean[LIFE_SIZE][LIFE_SIZE];
    int numGeneration = 0;
    Random random = new Random();
    JFrame frame;

    Canvas canvasPanel;

    public static void main(String[] args) {
        new GameOfLife().go();
    }

    void go() {
        frame = new JFrame(nameOfGame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FIELD_SIZE, FIELD_SIZE + BTN_PANAL_HEIGHT);
        frame.setLocation(START_LOCATION, START_LOCATION);
        frame.setResizable(false);

        JButton fillButton = new JButton("Fill");
        fillButton.addActionListener(new FillButtonListener());

        JButton stepButton = new JButton("Step");
        stepButton.addActionListener(new StepButtonListener());
        /*
        JButton goButton = new JButton("Go");
        goButton.addActionListener(new GoButtonListener());

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new StopButtonListener());
        */
        canvasPanel = new Canvas();

        JPanel btnPanel = new JPanel();
        btnPanel.add(fillButton);
        btnPanel.add(stepButton);
        //btnPanel.add(goButton);
        //btnPanel.add(stopButton);

        frame.getContentPane().add(BorderLayout.CENTER, canvasPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, btnPanel);
        frame.setVisible(true);
    }
    
    // count the number of neighbors
    int countNeighbors(int x, int y) {
        int count = 0;
        for (int dx = -1; dx < 2; dx++) {
            for (int dy = -1; dy < 2; dy++) {
                if (!((dx == 0) && (dy == 0))) {
                    int nX = x + dx;
                    int nY = y + dy;
                    nX = (nX < 0) ? LIFE_SIZE - 1 : nX;
                    nY = (nY < 0) ? LIFE_SIZE - 1 : nY;
                    nX = (nX > LIFE_SIZE - 1) ? 0 : nX;
                    nY = (nY > LIFE_SIZE - 1) ? 0 : nY;
                    count += (lifeGeneration[nX][nY]) ? 1 : 0;
                }
            }
        }
        return count;
    }

    // the main process of life
    void processOfLife() {
        for (int x = 0; x < LIFE_SIZE; x++) {
            for (int y = 0; y < LIFE_SIZE; y++) {
                int count = countNeighbors(x, y);
                // if are 3 live neighbors around empty cells - the cell becomes alive
                nextGeneration[x][y] = (count == 3) ? true : nextGeneration[x][y];
                // if cells have 2 or three neighbors - it continues to live
                nextGeneration[x][y] = ((count == 2) || (count == 3)) ? nextGeneration[x][y] : false;
            }
        }
        for (int x = 0; x < LIFE_SIZE; x++) {
            for (int y = 0; y < LIFE_SIZE; y++) {
                lifeGeneration[x][y] = nextGeneration[x][y];
            }
        }
        numGeneration++;
    }

    // randomly fill cells
    public class FillButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            numGeneration = 1;
            for (int x = 0; x < LIFE_SIZE; x++) {
                for (int y = 0; y < LIFE_SIZE; y++) {
                    lifeGeneration[x][y] = random.nextBoolean();
                }
            }
            canvasPanel.repaint();
        }
    }

    // get the next generation
    public class StepButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            processOfLife();
            canvasPanel.repaint();
        }
    }

    public class GoButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        }
    }

    public class StopButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        }
    }
    
    public class Canvas extends JPanel {

        public Canvas() {
            super();
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int x = 0; x < LIFE_SIZE; x++) {
                for (int y = 0; y < LIFE_SIZE; y++) {
                    if (lifeGeneration[x][y]) {
                        g.fillOval(x*POINT_RADIUS, y*POINT_RADIUS, 10, 10);
                    }
                }
            }
            frame.setTitle(nameOfGame + " : " + numGeneration);
        }
    }
}
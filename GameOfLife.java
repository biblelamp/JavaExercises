/**
 * Java. Conway's Game of Life
 *
 * @author Sergey Iryupin
 * @version 0.4.1 dated 02 Aug 2016
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.util.*;

public class GameOfLife {

    final String nameOfGame = "Conway's Game of Life";
    final int LIFE_SIZE = 50;
    final int POINT_RADIUS = 10;
    final int FIELD_SIZE = (LIFE_SIZE + 1) * POINT_RADIUS;
    final int BTN_PANEL_HEIGHT = 57;
    final int START_LOCATION = 200;
    boolean[][] lifeGeneration = new boolean[LIFE_SIZE][LIFE_SIZE];
    boolean[][] nextGeneration = new boolean[LIFE_SIZE][LIFE_SIZE];
    int countGeneration = 0;
    int showDelay = 500;
    int showDelayStep = 50;
    boolean goNextGeneration = false;
    boolean useColors = false;
    Random random = new Random();
    JFrame frame;
    Canvas canvasPanel;

    public static void main(String[] args) {
        new GameOfLife().go();
    }

    void go() {
        frame = new JFrame(nameOfGame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FIELD_SIZE, FIELD_SIZE + BTN_PANEL_HEIGHT);
        frame.setLocation(START_LOCATION, START_LOCATION);
        frame.setResizable(false);

        // randomly fill cells
        JButton fillButton = new JButton("Fill");
        fillButton.addActionListener(new FillButtonListener());

        // get the next generation
        JButton stepButton = new JButton("Step");
        stepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processOfLife();
                canvasPanel.repaint();
            }
        });

        // generation after generation without stopping
        JButton goButton = new JButton("Go");
        goButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goNextGeneration = !goNextGeneration;
                goButton.setText(goNextGeneration? "Stop" : "Go");
            }
        });

        // show change of generation faster
        JButton fasterButton = new JButton("Faster");
        fasterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDelay -= (showDelay - showDelayStep == 0) ? 0 : showDelayStep;
            }
        });

        // show change of generation slower
        JButton slowerButton = new JButton("Slower");
        slowerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDelay += showDelay;
            }
        });

        // turn on/off colors
        JButton colorButton = new JButton("Color");
        colorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                useColors = !useColors;
                colorButton.setText(useColors? "Mono" : "Color");
            }
        });

        canvasPanel = new Canvas();
        canvasPanel.setBackground(Color.white);
        canvasPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = e.getX()/POINT_RADIUS;
                int y = e.getY()/POINT_RADIUS;
                lifeGeneration[x][y] = !lifeGeneration[x][y];
                canvasPanel.repaint();
            }
        });

        // panel of button: fill/step/go/faster/slower/stop
        JPanel btnPanel = new JPanel();
        btnPanel.add(fillButton);
        btnPanel.add(stepButton);
        btnPanel.add(goButton);
        btnPanel.add(fasterButton);
        btnPanel.add(slowerButton);
        btnPanel.add(colorButton);

        frame.getContentPane().add(BorderLayout.CENTER, canvasPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, btnPanel);
        frame.setVisible(true);

        // endless loop of life
        while (true) {
            if (goNextGeneration) {
                processOfLife();
                canvasPanel.repaint();
                try {
                    Thread.sleep(showDelay);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }

    // randomly fill cells
    public class FillButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            countGeneration = 1;
            for (int x = 0; x < LIFE_SIZE; x++) {
                for (int y = 0; y < LIFE_SIZE; y++) {
                    lifeGeneration[x][y] = random.nextBoolean();
                }
            }
            canvasPanel.repaint();
        }
    }

    // count the number of neighbors
    int countNeighbors(int x, int y) {
        int count = 0;
        for (int dx = -1; dx < 2; dx++) {
            for (int dy = -1; dy < 2; dy++) {
                int nX = x + dx;
                int nY = y + dy;
                nX = (nX < 0) ? LIFE_SIZE - 1 : nX;
                nY = (nY < 0) ? LIFE_SIZE - 1 : nY;
                nX = (nX > LIFE_SIZE - 1) ? 0 : nX;
                nY = (nY > LIFE_SIZE - 1) ? 0 : nY;
                count += (lifeGeneration[nX][nY]) ? 1 : 0;
            }
        }
        if (lifeGeneration[x][y]) { count--; }
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
                nextGeneration[x][y] = ((count < 2) || (count > 3)) ? false : nextGeneration[x][y];
            }
        }
        for (int x = 0; x < LIFE_SIZE; x++) {
            System.arraycopy(nextGeneration[x], 0, lifeGeneration[x], 0, LIFE_SIZE);
        }
        countGeneration++;
    }

    public class Canvas extends JPanel {

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int x = 0; x < LIFE_SIZE; x++) {
                for (int y = 0; y < LIFE_SIZE; y++) {
                    if (lifeGeneration[x][y]) {
                        if (useColors) {
                            int count = countNeighbors(x, y);
                            g.setColor(((count < 2) || (count > 3))? Color.red : Color.blue);
                        }
                        g.fillOval(x*POINT_RADIUS, y*POINT_RADIUS, POINT_RADIUS, POINT_RADIUS);
                    }
                }
            }
            frame.setTitle(nameOfGame + " : " + countGeneration);
        }
    }
}
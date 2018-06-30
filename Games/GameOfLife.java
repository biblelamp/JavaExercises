/**
 * Java. Conway's Game of Life
 *  The Game of Life, also known simply as Life, is a cellular automaton
 *  devised by the British mathematician John Horton Conway in 1970.
 *  Rules:
 *  - Any live cell with fewer than 2 live neighbours dies, as if caused by under-population.
 *  - Any live cell with more than 3 live neighbours dies, as if by over-population.
 *  - Any live cell with 2 or 3 live neighbours lives on to the next generation.
 *  - Any dead cell with exactly 3 live neighbours becomes a live cell, as if by reproduction.
 *
 * @author Sergey Iryupin
 * @version 0.4.11 dated 20 Aug 2016
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;
import java.util.*;
import java.io.*;

public class GameOfLife extends JFrame {

    final String NAME_OF_GAME = "Conway's Game of Life";
    final String SAVE_FILE_EXT = ".life";
    final int LIFE_SIZE = 50;
    final int POINT_RADIUS = 10;
    final int FIELD_SIZE = LIFE_SIZE * POINT_RADIUS + 7;
    final int BTN_PANEL_HEIGHT = 58+4;
    final int START_LOCATION = 200;
    boolean[][] lifeGeneration = new boolean[LIFE_SIZE][LIFE_SIZE];
    boolean[][] nextGeneration = new boolean[LIFE_SIZE][LIFE_SIZE];
    boolean[][] tmp;
    int countGeneration = 0;
    int showDelay = 500;
    int showDelayStep = 50;
	volatile boolean goNextGeneration = false; // fixed the problem in 64-bit JVM added volatile
    boolean useColors = false;
    boolean showGrid = false;
    Random random = new Random();
    Dimension btnDimension = new Dimension(30, 26);
    JFrame frame;
    Canvas canvasPanel;

    // icons for buttons
    final ImageIcon icoFill = new ImageIcon(GameOfLife.class.getResource("img/btnFill.png"));
    final ImageIcon icoNew = new ImageIcon(GameOfLife.class.getResource("img/btnNew.png"));
    final ImageIcon icoOpen = new ImageIcon(GameOfLife.class.getResource("img/btnOpen.png"));
    final ImageIcon icoSave = new ImageIcon(GameOfLife.class.getResource("img/btnSave.png"));
    final ImageIcon icoStep = new ImageIcon(GameOfLife.class.getResource("img/btnStep.png"));
    final ImageIcon icoGo = new ImageIcon(GameOfLife.class.getResource("img/btnGo.png"));
    final ImageIcon icoStop = new ImageIcon(GameOfLife.class.getResource("img/btnStop.png"));
    final ImageIcon icoFaster = new ImageIcon(GameOfLife.class.getResource("img/btnFaster.png"));
    final ImageIcon icoSlower = new ImageIcon(GameOfLife.class.getResource("img/btnSlower.png"));
    final ImageIcon icoColor = new ImageIcon(GameOfLife.class.getResource("img/btnColor.png"));
    final ImageIcon icoNoColor = new ImageIcon(GameOfLife.class.getResource("img/btnNoColor.png"));
    final ImageIcon icoGrid = new ImageIcon(GameOfLife.class.getResource("img/btnGrid.png"));

    public static void main(String[] args) {
        new GameOfLife().go();
    }

    void go() {
        frame = new JFrame(NAME_OF_GAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FIELD_SIZE, FIELD_SIZE + BTN_PANEL_HEIGHT);
        frame.setLocation(START_LOCATION, START_LOCATION);
        frame.setResizable(false);

        // randomly fill cells
        JButton fillButton = new JButton();
        fillButton.setIcon(icoFill);
        fillButton.setPreferredSize(btnDimension);
        fillButton.setToolTipText("Fill randomly");
        fillButton.addActionListener(new FillButtonListener());

        // clear fields
        JButton newButton = new JButton();
        newButton.setIcon(icoNew);
        newButton.setPreferredSize(btnDimension);
        newButton.setToolTipText("Clear field");
        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int x = 0; x < LIFE_SIZE; x++) {
                    Arrays.fill(lifeGeneration[x], false);
                }
                canvasPanel.repaint();
            }
        });

        // open saved file
        JButton openButton = new JButton();
        openButton.setIcon(icoOpen);
        openButton.setPreferredSize(btnDimension);
        openButton.setToolTipText("Open saved file");
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser open = new JFileChooser(".");
                open.setFileSelectionMode(JFileChooser.FILES_ONLY);
                open.addChoosableFileFilter(new FileFilter() {
                    public String getDescription() {
                        return "Saved GameOfLife files (*" + SAVE_FILE_EXT + ")";
                    }
                    public boolean accept(File f) {
                        if (f.isDirectory()) {
                            return true;
                        } else {
                            return f.getName().toLowerCase().endsWith(SAVE_FILE_EXT);
                        }
                    }
                });
                open.setAcceptAllFileFilterUsed(true);
                int result = open.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileInputStream fileIn = new FileInputStream(new File(open.getSelectedFile().getAbsolutePath()));
                        ObjectInputStream is = new ObjectInputStream(fileIn);
                        lifeGeneration = (boolean[][]) is.readObject();
                        canvasPanel.repaint();
                    } catch(Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Incorrect file format.", "Error openning file", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // save field as file
        JButton saveButton = new JButton();
        saveButton.setIcon(icoSave);
        saveButton.setPreferredSize(btnDimension);
        saveButton.setToolTipText("Save field as file");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser save = new JFileChooser(".");
                save.setFileSelectionMode(JFileChooser.FILES_ONLY);
                //save.setFileFilter(new FileNameExtensionFilter("Game Of Life files (*." + SAVE_FILE_EXT + ")", SAVE_FILE_EXT)); // for compatibility with Java 1.5
                int result = save.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileOutputStream fileStream = new FileOutputStream(new File(save.getSelectedFile().getAbsolutePath() + (save.getSelectedFile().getAbsolutePath().endsWith(SAVE_FILE_EXT)?"":SAVE_FILE_EXT)));
                        ObjectOutputStream os = new ObjectOutputStream(fileStream);
                        os.writeObject(lifeGeneration);
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // get the next generation
        JButton stepButton = new JButton();
        stepButton.setIcon(icoStep);
        stepButton.setPreferredSize(btnDimension);
        stepButton.setToolTipText("Show next generation");
        stepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processOfLife();
                canvasPanel.repaint();
            }
        });

        // generation after generation without stopping
        final JButton goButton = new JButton();
        goButton.setIcon(icoGo);
        goButton.setPreferredSize(new Dimension(34, 30));
        goButton.setToolTipText("Go/Stop generation after generation");
        goButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goNextGeneration = !goNextGeneration;
                goButton.setIcon(goNextGeneration? icoStop : icoGo);
                goButton.setFocusable(false); // as an example to remove the frame
            }
        });

        // show change of generation faster
        JButton fasterButton = new JButton();
        fasterButton.setIcon(icoFaster);
        fasterButton.setPreferredSize(btnDimension);
        fasterButton.setToolTipText("Faster");
        fasterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDelay -= (showDelay - showDelayStep == 0) ? 0 : showDelayStep;
            }
        });

        // show change of generation slower
        JButton slowerButton = new JButton();
        slowerButton.setIcon(icoSlower);
        slowerButton.setPreferredSize(btnDimension);
        slowerButton.setToolTipText("Slower");
        slowerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDelay += showDelay;
            }
        });

        // turn on/off colors
        final JButton colorButton = new JButton();
        colorButton.setIcon(icoColor);
        colorButton.setPreferredSize(btnDimension);
        colorButton.setToolTipText("Turn on/off colors");
        colorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                useColors = !useColors;
                colorButton.setIcon(useColors? icoNoColor : icoColor);
                canvasPanel.repaint();
            }
        });

        // to show/hide the grid
        final JButton gridButton = new JButton();
        gridButton.setIcon(icoGrid);
        gridButton.setPreferredSize(btnDimension);
        gridButton.setToolTipText("Show/hide the grid");
        gridButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showGrid = !showGrid;
                canvasPanel.repaint();
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

        // panel of buttons:
        // fill/new/open/save/step/go/stop/faster/slower/color/grid
        JPanel btnPanel = new JPanel();
        btnPanel.add(fillButton);
        btnPanel.add(newButton);
        btnPanel.add(openButton);
        btnPanel.add(saveButton);
        btnPanel.add(stepButton);
        btnPanel.add(goButton);
        btnPanel.add(fasterButton);
        btnPanel.add(slowerButton);
        btnPanel.add(colorButton);
        btnPanel.add(gridButton);

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
                nextGeneration[x][y] = lifeGeneration[x][y];
                // if are 3 live neighbors around empty cells - the cell becomes alive
                nextGeneration[x][y] = (count == 3) ? true : nextGeneration[x][y];
                // if cell has less than 2 or greater than 3 neighbors - it will be die
                nextGeneration[x][y] = ((count < 2) || (count > 3)) ? false : nextGeneration[x][y];
            }
        }
	// swap generations
	tmp		= nextGeneration;
	nextGeneration	= lifeGeneration;
	lifeGeneration	= tmp;

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
                        } else {
                            g.setColor(Color.black);
                        }
                        g.fillOval(x*POINT_RADIUS, y*POINT_RADIUS, POINT_RADIUS, POINT_RADIUS);
                    } else {
                        if (useColors) {
                            int count = countNeighbors(x, y);
                            if (count == 3) {
                                g.setColor(new Color(225, 255, 235));
                                g.fillOval(x*POINT_RADIUS, y*POINT_RADIUS, POINT_RADIUS, POINT_RADIUS);
                            }
                        }
                    }
                    if (showGrid) {
                        g.setColor(Color.lightGray);
                        g.drawLine((x+1)*POINT_RADIUS-1, (y+1)*POINT_RADIUS, (x+1)*POINT_RADIUS+1, (y+1)*POINT_RADIUS);
                        g.drawLine((x+1)*POINT_RADIUS, (y+1)*POINT_RADIUS-1, (x+1)*POINT_RADIUS, (y+1)*POINT_RADIUS+1);
                    }
                }
            }
            frame.setTitle(NAME_OF_GAME + " : " + countGeneration);
        }
    }
}

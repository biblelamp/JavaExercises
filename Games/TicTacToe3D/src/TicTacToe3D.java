import java.awt.*;
import java.awt.event.*;
import static java.lang.Math.*;
import javax.swing.*;
import java.util.*;

/**
 * Java. Tic Tac Toe 3D
 *
 * @author Sergey Iryupin
 * @version 0.0.9 dated Dec 19, 2018
 */

public class TicTacToe3D extends JPanel {
    final int RD = 5;

    double[][] nodes = new double[27][4];

    int[][] edges = {
        {0, 2}, {3, 5}, {6, 8}, {0, 6}, {1, 7}, {2, 8},
        {9, 11}, {12, 14}, {15, 17}, {9, 15}, {10, 16}, {11, 17},
        {18, 20}, {21, 23}, {24, 26}, {18, 24}, {19, 25}, {20, 26},
        {0, 18}, {1, 19}, {2, 20}, {3, 21}, {4, 22}, {5, 23}, {6, 24}, {7, 25}, {8, 26}
    };

    int mouseX, prevMouseX, mouseY, prevMouseY;

    JPanel btnPanel;

    Random random;

    public TicTacToe3D() {
        setPreferredSize(new Dimension(640, 640));
        setBackground(Color.white);

        initNodes();
        scale(140);
        rotate(PI / 5, PI / 9);

        random = new Random();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();

                setColor(mouseX, mouseY);
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                prevMouseX = mouseX;
                prevMouseY = mouseY;
                mouseX = e.getX();
                mouseY = e.getY();

                double incrX = (mouseX - prevMouseX) * 0.01;
                double incrY = (mouseY - prevMouseY) * 0.01;

                rotate(incrX, incrY);
                repaint();
            }
        });

        btnPanel = new JPanel();
        JButton init = new JButton("New game");
        init.addActionListener(e -> {
            for (int i = 0; i < nodes.length; i++)
                nodes[i][3] = 0;
            repaint();
        });
        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> System.exit(0));
        btnPanel.setLayout(new GridLayout());
        btnPanel.add(init);
        btnPanel.add(exit);
    }

    private void initNodes() {
        int i = 0;
        for (int x = -1; x < 2; x++)
            for (int y = -1; y < 2; y++)
                for (int z = -1; z < 2; z++) {
                    nodes[i][0] = x;
                    nodes[i][1] = y;
                    nodes[i][2] = z;
                    nodes[i++][3] = 0;
                }
    }

    private void setColor(double x, double y) {
        x -= getWidth() / 2;
        y -= getHeight() / 2;

        for (int i = 0; i < nodes.length; i++)
            if (Math.abs(nodes[i][0] - x) < RD && Math.abs(nodes[i][1] - y) < RD) {
                System.out.println("Node: " + i);
                if (nodes[i][3] == 0) {
                    nodes[i][3] = 1;
                    setColorAI();

                    System.out.println(checkWin(1));
                }
            }
    }

    private void setColorAI() {
        int i;
        do {
            i = random.nextInt(nodes.length);
        } while (nodes[i][3] != 0);
        nodes[i][3] = -1;
    }

    private boolean isFill() {
        for (double[] node : nodes)
            if (node[3] == 0)
                return false;
        return true;
    }

    private boolean checkWin(int sign) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                // checking Z axis
                if ((nodes[i*9 + j*3][3] + nodes[i*9+1 + j*3][3] + nodes[i*9+2 + j*3][3] == sign*3) ||
                    // checking Y axis
                    (nodes[i + j*9][3] + nodes[i + j*9 + 3][3] + nodes[i + j*9 + 6][3] == sign*3))
                    return true;
        // checking diagonals of surfaces
        if ((nodes[0][3] + nodes[4][3] + nodes[8][3] == sign*3) ||
            (nodes[2][3] + nodes[4][3] + nodes[6][3] == sign*3) ||
            (nodes[2][3] + nodes[14][3] + nodes[26][3] == sign*3) ||
            (nodes[8][3] + nodes[14][3] + nodes[20][3] == sign*3) ||
            (nodes[18][3] + nodes[22][3] + nodes[26][3] == sign*3) ||
            (nodes[20][3] + nodes[22][3] + nodes[24][3] == sign*3) ||
            (nodes[0][3] + nodes[12][3] + nodes[24][3] == sign*3) ||
            (nodes[6][3] + nodes[12][3] + nodes[18][3] == sign*3) ||
            (nodes[0][3] + nodes[10][3] + nodes[20][3] == sign*3) ||
            (nodes[2][3] + nodes[10][3] + nodes[18][3] == sign*3) ||
            (nodes[6][3] + nodes[16][3] + nodes[26][3] == sign*3) ||
            (nodes[8][3] + nodes[16][3] + nodes[24][3] == sign*3))
            return true;
        // checking internal diagonals
        for (int i = 0; i < 8; i++)
            if (nodes[i][3] + nodes[13][3] + nodes[26 - i][3] == sign*3)
                return true;
        return false;
    }

    private void scale(double s) {
        for (double[] node : nodes) {
            node[0] *= s;
            node[1] *= s;
            node[2] *= s;
        }
    }

    private void rotate(double angleX, double angleY) {
        double sinX = sin(angleX);
        double cosX = cos(angleX);

        double sinY = sin(angleY);
        double cosY = cos(angleY);

        for (double[] node : nodes) {
            double x = node[0];
            double y = node[1];
            double z = node[2];

            node[0] = x * cosX - z * sinX;
            node[2] = z * cosX + x * sinX;

            z = node[2];

            node[1] = y * cosY - z * sinY;
            node[2] = z * cosY + y * sinY;
        }
    }

    void drawCube(Graphics2D g) {
        g.translate(getWidth() / 2, getHeight() / 2);

        g.setColor(Color.lightGray);
        for (int[] edge : edges) {
            double[] xy1 = nodes[edge[0]];
            double[] xy2 = nodes[edge[1]];
            g.setColor(Color.lightGray);
            g.drawLine((int) round(xy1[0]), (int) round(xy1[1]),
                    (int) round(xy2[0]), (int) round(xy2[1]));
        }

        for (double[] node : nodes) {
            if (node[3] < 0)
                g.setColor(Color.red);
            else if (node[3] > 0)
                g.setColor(Color.blue);
            else
                g.setColor(Color.black);
            g.fillOval((int) round(node[0]) - RD, (int) round(node[1]) - RD, RD*2, RD*2);
        }
    }

    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        drawCube(g);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe3D t3D = new TicTacToe3D();
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle("TicTacToe3D");
            f.setResizable(false);
            f.add(t3D, BorderLayout.CENTER);
            f.add(t3D.btnPanel, BorderLayout.SOUTH);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}

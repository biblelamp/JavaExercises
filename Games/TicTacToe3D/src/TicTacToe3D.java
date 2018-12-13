import java.awt.*;
import java.awt.event.*;
import static java.lang.Math.*;
import javax.swing.*;
 
public class TicTacToe3D extends JPanel {
    double[][] nodes = {{0, 0, 0},
        {1, 0, 0}, {0, 1, 0}, {0, 0, 1}, {-1, 0, 0}, {0, -1, 0}, {0, 0, -1},
        {-1, 1, -1}, {-1, 0, -1}, {-1, -1, -1}, {0, -1, -1}, {1, -1, -1}, {1, 0, -1}, {1, 1, -1}, {0, 1, -1},
        {-1, 1, 0}, {-1, 0, 0}, {-1, -1, 0}, {0, -1, 0}, {1, -1, 0}, {1, 0, 0}, {1, 1, 0}, {0, 1, 0},
        {-1, 1, 1}, {-1, 0, 1}, {-1, -1, 1}, {0, -1, 1}, {1, -1, 1}, {1, 0, 1}, {1, 1, 1}, {0, 1, 1}
        };
 
    int[][] edges = {
        {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6},
        {7, 9}, {9, 11}, {11, 13}, {13, 7},
        {15, 17}, {17, 19}, {19, 21}, {21, 15},
        {23, 25}, {25, 27}, {27, 29}, {29, 23}
        };
 
    int mouseX, prevMouseX, mouseY, prevMouseY;
 
    public TicTacToe3D() {
        setPreferredSize(new Dimension(640, 640));
        setBackground(Color.white);
 
        scale(120, 120, 120);
        rotateCube(PI / 5, PI / 9);
 
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                System.out.println(mouseX + ":" + mouseY);
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
 
                rotateCube(incrX, incrY);
                repaint();
            }
        });
    }
 
    private void scale(double sx, double sy, double sz) {
        for (double[] node : nodes) {
            node[0] *= sx;
            node[1] *= sy;
            node[2] *= sz;
        }
    }
 
    private void rotateCube(double angleX, double angleY) {
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
 
        g.setColor(Color.black);
        for (double[] node : nodes)
            g.fillOval((int) round(node[0]) - 4, (int) round(node[1]) - 4, 8, 8);
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
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle("TicTacToe3D");
            f.setResizable(false);
            f.add(new TicTacToe3D(), BorderLayout.CENTER);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}

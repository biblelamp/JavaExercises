package lesson31.homework;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Shapes {
    private Random random = new Random();
    private final Color[] COLORS = {Color.red, Color.green, Color.blue, Color.yellow, Color.magenta};
    private int SHAPE_COUNT = 100;

    public void paint(Graphics g, ShapeForm shapeForm, JPanel canvas) {
        for (int i = 0; i < SHAPE_COUNT; i++) {
            switch (shapeForm) {
                case CIRCLES: {
                    int d = random.nextInt(20) + 60;
                    int x = random.nextInt(canvas.getWidth() - d);
                    int y = random.nextInt(canvas.getHeight() - d);
                    Color color = COLORS[random.nextInt(COLORS.length)];
                    g.setColor(color);
                    g.fillOval(x, y, d, d);
                    g.setColor(Color.black);
                    g.drawOval(x, y, d, d);
                    break;
                }
                case RECTANGLES: {
                    int width = random.nextInt(20) + 100;
                    int height = random.nextInt(20) + 50;
                    int x = random.nextInt(canvas.getWidth() - width);
                    int y = random.nextInt(canvas.getHeight() - height);
                    Color color = COLORS[random.nextInt(COLORS.length)];
                    g.setColor(color);
                    g.fillRect(x, y, width, height);
                    g.setColor(Color.black);
                    g.drawRect(x, y, width, height);
                    break;
                }
                case TRIANGLES: {
                    int length = random.nextInt(20) + 80;
                    int h = (int) (length * Math.sqrt(3) / 2);
                    int x1 = random.nextInt(canvas.getWidth() - length);
                    int y1 = random.nextInt(canvas.getHeight() - length);
                    int x2 = x1 + length;
                    int y2 = y1;
                    int x3 = x2 / 2;
                    int y3 = y2 + h;
                    Color color = COLORS[random.nextInt(COLORS.length)];
                    g.setColor(color);
                    g.drawLine(x1, y1, x2, y2);
                    g.drawLine(x1, y1, x3, y3);
                    g.drawLine(x3, y3, x2, y2);
                    break;
                }
            }
        }
    }
}

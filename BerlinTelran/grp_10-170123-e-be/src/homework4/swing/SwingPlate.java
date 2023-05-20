package homework4.swing;

import homework4.Plate;

import java.awt.Color;
import java.awt.Graphics;

public class SwingPlate extends Plate {

    SwingPlate(int volume, int food) {
        super(volume, food);
    }

    void paint(Graphics g, int windowWidth, int dy) {
        g.setColor(Color.red);
        g.fill3DRect(10, 10, food * (windowWidth - 20) / maxVolume + 1, dy + 1, false);
        g.setColor(Color.black);
        g.drawRect(10, 10, windowWidth - 20, dy);
    }
}

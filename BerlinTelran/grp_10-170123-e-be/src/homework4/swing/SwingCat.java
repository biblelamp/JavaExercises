package homework4.swing;

import homework4.Cat;

import java.awt.Color;
import java.awt.Graphics;

public class SwingCat extends Cat {

    SwingCat(String name, int appetite) {
        super(name, appetite);
    }

    void paint(Graphics g, int y, int dx, int dy) {
        if (fullness) {
            g.setColor(Color.green);
            g.fill3DRect(20, y, appetite * dx + 1, dy + 1, true);
        }
        g.setColor(Color.black);
        g.drawRect(20, y, appetite * dx, dy);
    }
}

package starbattle;

import java.awt.Graphics;
import java.awt.Image;

public class StarShip {
    private int x;
    private int y;
    private int dx;
    private int speed;
    private int keyCode;
    private Image image;

    public StarShip(int x, int y, int dx, int speed, Image image) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.speed = speed;
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDx() {
        return dx;
    }

    public void setKeyCode(int keyCode) {
        if (keyCode == 37 || keyCode == 38 || keyCode == 39 || keyCode == 40) {
            this.keyCode = keyCode;
        }
    }

    public void move() {
        switch (keyCode) {
            case 37:
                if (x > dx + speed) {
                    x -= speed;
                } else {
                    keyCode = 39;
                }
                break;
            case 38:
                if (y > dx / 2 + speed) {
                    y -= speed;
                } else {
                    keyCode = 40;
                }
                break;
            case 39:
                x += speed;
                break;
            case 40:
                if (y < StarBattle.WINDOW_HEIGTH - dx / 2 - speed) {
                    y += speed;
                } else {
                    keyCode = 38;
                }
                break;
        }
    }

    public void paint(Graphics g) {
//        g.setColor(Color.white);
//        g.drawLine(x, y, x - dx, y - dx/2);
//        g.drawLine(x - dx, y - dx/2, x - dx, y + dx/2);
//        g.drawLine(x - dx, y + dx/2, x, y);
//        int x1 = x - dx;
//        int y1 = y - dx / 2;
//        g.drawOval(x1, y1, dx, dx);
        g.drawImage(image, x - dx, y - dx / 2, dx, dx, null);
    }
}

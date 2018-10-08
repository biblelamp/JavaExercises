import java.awt.Color;
import java.awt.Graphics;

/**
 * Java. Katamari Damacy Game
 *  Class Circle: base class
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Oct 08, 2018
 */

public class Hero extends Circle {
	private int direction;
	private int speed = 1;

	public Hero(int x, int y, int size, Color color) {
		super(x, y, size, color);
		direction = KatamariDamacy.KEY_RIGHT;
	}

	public void setDirection(int direction) {
		if ((direction >= KatamariDamacy.KEY_LEFT) && (direction <= KatamariDamacy.KEY_DOWN))
			this.direction = direction;
	}

	public void move() {
		switch (direction) {
			case KatamariDamacy.KEY_LEFT:
				x -= speed;
				if (x < 0)
					direction = KatamariDamacy.KEY_RIGHT;
				break;
			case KatamariDamacy.KEY_RIGHT:
				x += speed;
				if (x > 640 - size)
					direction = KatamariDamacy.KEY_LEFT;
				break;
			case KatamariDamacy.KEY_UP:
				y -= speed;
				break;
			case KatamariDamacy.KEY_DOWN:
				y += speed;
				break;
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(x + size / 2, y + 20, x + size / 2, y + size - 20);
		g.drawLine(x + 20, y + size / 2, x + size - 20, y + size / 2);
	}
}

import java.awt.Color;
import java.awt.Graphics;

/**
 * Java. Katamari Damacy Game
 *  Class Circle: base class
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Oct 12, 2018
 */

public class Hero extends Circle {
	private int speed = 1;

	public Hero(int x, int y, int size, int direction, Color color) {
		super(x, y, size, direction, color);
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
				if (x > KatamariDamacy.WIDTH - size)
					direction = KatamariDamacy.KEY_LEFT;
				break;
			case KatamariDamacy.KEY_UP:
				y -= speed;
				if (y < 0)
					direction = KatamariDamacy.KEY_DOWN;
				break;
			case KatamariDamacy.KEY_DOWN:
				y += speed;
				if (y > KatamariDamacy.HEIGHT - size)
					direction = KatamariDamacy.KEY_UP;
				break;
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(x + size / 2, y + 15, x + size / 2, y + size - 15);
		g.drawLine(x + 15, y + size / 2, x + size - 15, y + size / 2);
	}
}

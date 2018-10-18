import java.awt.Color;
import java.awt.Graphics;

/**
 * Java. Katamari Damacy Game
 *  Class Circle: base class
 *
 * @author Sergey Iryupin
 * @version 0.2.2 dated Oct 18, 2018
 */

public class Hero extends Circle {
	private int speed = 1;

	public Hero(int x, int y, int size, int direction, Color color) {
		super(x, y, size, direction, color);
	}

	public void setDirection(int direction) {
		if ((direction >= KatamariDamacy.KEY_LEFT &&
				direction <= KatamariDamacy.KEY_DOWN) ||
				direction == KatamariDamacy.KEY_SPACE)
			this.direction = direction;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(getX(), y + size / 3, getX(), y + size - size / 3);
		g.drawLine(x + size / 3, getY(), x + size - size / 3, getY());
	}
}

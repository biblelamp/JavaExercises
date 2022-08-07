package starbattle;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Stars {
    private List<Star> stars;
    private Random random;

    public Stars(int count) {
        random = new Random();
        stars = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int x = random.nextInt(StarBattle.WINDOW_WIDTH);
            int y = random.nextInt(StarBattle.WINDOW_HEIGTH);
            int d = random.nextInt(4) + 2;
            stars.add(new Star(x, y, d));
        }
    }

    public void paint(Graphics g) {
        for (Star star : stars) {
            star.paint(g);
        }
    }
}

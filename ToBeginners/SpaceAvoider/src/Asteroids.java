import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;

class Asteroids {
    private List<Asteroid> asteroids;

    public Asteroids(int amount) {
        Random rand = new Random();
        asteroids = new ArrayList<>(amount);
        for (int i = 0; i < amount; i++) {
            asteroids.add(new Asteroid(
                rand.nextInt(SpaceAvoider.WINDOW_WIDTH),
                rand.nextInt(SpaceAvoider.WINDOW_HEIGHT - 100),
                rand.nextInt(20) + 20)
            );
        }
    }

    public void paint(Graphics g) {
        for (Asteroid asteroid : asteroids) {
            asteroid.paint(g);
        }
    }

    class Asteroid {
        int x, y, d;

        Asteroid(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        void paint(Graphics g) {
            g.drawOval(x, y, d, d);
        }
    }
}
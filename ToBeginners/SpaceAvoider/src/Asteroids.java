import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Rectangle;

class Asteroids {
    private List<Asteroid> asteroids;

    public Asteroids(int amount) {
        Asteroid asteroid;
        Random rand = new Random();
        asteroids = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            do {
                asteroid = new Asteroid(
                    rand.nextInt(SpaceAvoider.WINDOW_WIDTH),
                    rand.nextInt(SpaceAvoider.WINDOW_HEIGHT - 150),
                    rand.nextInt(20) + 20);
            } while (isCrossing(asteroid));
            asteroids.add(asteroid);
        }
    }

    boolean isCrossing(Asteroid asteroid) {
        Rectangle asteroidR = new Rectangle(asteroid.x, asteroid.y, asteroid.d, asteroid.d);
        for (Asteroid a : asteroids) {
            Rectangle aR = new Rectangle(a.x, a.y, a.d, a.d);
            if (asteroidR.intersects(aR)) {
                return true;
            }
        }
        return false;
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
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

    boolean isCrossing(Asteroid asteroid) {
        for (Asteroid asteroid : asteroids) {
            // https://ru.stackoverflow.com/questions/758529/%D0%9F%D0%B5%D1%80%D0%B5%D1%81%D0%B5%D1%87%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B4%D0%B2%D1%83%D1%85-%D0%BF%D1%80%D1%8F%D0%BC%D0%BE%D1%83%D0%B3%D0%BE%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA%D0%BE%D0%B2-c
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
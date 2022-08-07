package starbattle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class StarBattle extends JFrame {
    final static int WINDOW_WIDTH = 1000;
    final static int WINDOW_HEIGTH = 700;

    int countAsteroids = 15; // default: 15

    int sleep = 15; // задержка

    int starShipDx = 100; // диаметр окружности корабля

    int starShipSpeed = 5;

    int bulletDx = 6;

    int starCount = 150;

    Image imageAsteroid;

    Image imageShip;

    StarShip starShip;

    List<Asteroid> asteroids = new ArrayList<>();

    List<Bullet> bullets = new ArrayList<>();

    Stars stars;

    Random random = new Random();

    Canvas canvas;

    int countBullets = 0;

    int countHits = 0;

    boolean gameOver = false;

    public static void main(String[] args) throws InterruptedException {
        new StarBattle().go();
    }

    public StarBattle() {
        setTitle("Star Battle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new Canvas();
        canvas.setBackground(Color.black);
        canvas.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGTH));

        imageAsteroid = new ImageIcon(this.getClass().getResource("img/asteroid.png")).getImage();
        imageShip = new ImageIcon(this.getClass().getResource("img/spaceship.png")).getImage();

        starShip = new StarShip(WINDOW_WIDTH/4, WINDOW_HEIGTH/2, starShipDx, starShipSpeed, imageShip);

        initAsteroids(countAsteroids);

        stars = new Stars(starCount);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                shotStarShip(e.getKeyCode());
                starShip.setKeyCode(e.getKeyCode());
            }
        });

        add(canvas);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /*
     * Main game method
     */
    private void go() throws InterruptedException {
        while (!gameOver) {
            moveBullets();
            moveAsteroids();
            starShip.move();
            isBulletShotAsteroid();
            isAsteroidCrossShip();
            canvas.repaint();
            Thread.sleep(sleep);
        }
    }

    private void initAsteroids(int count) {
        int d, x, y;
        Asteroid asteroid = null;
        for (int i = 0; i < count; i++) {
            do {
                d = random.nextInt(75) + 25;
                x = random.nextInt(WINDOW_WIDTH / 2) + WINDOW_WIDTH / 2;
                y = random.nextInt(WINDOW_HEIGTH - d);
                asteroid = new Asteroid(x, y, d, imageAsteroid);
            } while (isAsteroidCrossAsteroid(asteroid));
            asteroids.add(asteroid);
        }
    }

    private void shotStarShip(int keyCode) {
        if (keyCode == 32) {
            int x = starShip.getX();
            int y = starShip.getY() - 5;
            int bx = 30 + 1;
            if (bullets.size() > 0) {
                bx = bullets.get(bullets.size() - 1).getX() - x;
            }
            if (bx > 30) {
                bullets.add(new Bullet(starShip.getX(), starShip.getY() - 5, bulletDx));
                countBullets++;
                setTitle(String.format("Star Battle: %d bulltes %d hits", countBullets, countHits));
            }
        }
    }
    // перемещение пуль
    private void moveBullets() {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.setX(bullet.getX() + 2);
            // if bullet go out - to kill
            if (bullet.getX() > WINDOW_WIDTH) {
                iterator.remove();
            }
        }
    }
    // перемещение астероидов
    private void moveAsteroids() {
        for (Asteroid asteroid : asteroids) {
            asteroid.setX(asteroid.getX() - 1);
            // если астероид ушел за левый край окна
            if (asteroid.getX() == -asteroid.getD()) {
                resetAsteroid(asteroid);
            }
        }
    }

    /*
     * Проверка: пересекается ли астероид с другими астероидами из списка?
     */
    private boolean isAsteroidCrossAsteroid(Asteroid asteroid1) {
        for (Asteroid asteroid : asteroids) {
            if (isCollision(asteroid, asteroid1)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Проверка: пересекается ли корабль с каким-либо астероидом?
     */
    private void isAsteroidCrossShip() {
        for (Asteroid asteroid : asteroids) {
            if (isCollision(starShip, asteroid)) {
                System.out.println("Cross starShip & asteroid!");
                gameOver = true;
            }
        }
    }

    /*
     * Проверка: поразила ли пуля астероид?
     */
    private void isBulletShotAsteroid() {
        for (Bullet bullet : bullets) {
            for (Asteroid asteroid : asteroids) {
                if (isCollision(bullet, asteroid)) {
                    bullets.remove(bullet);
                    resetAsteroid(asteroid);
                    countHits++;
                    setTitle(String.format("Star Battle: %d bulltes %d hits", countBullets, countHits));
                    return;
                }
            }
        }
    }

    /*
     * Изменение координат поражённого или ушедшего за край астероида
     */
    private void resetAsteroid(Asteroid asteroid) {
        int x, y;
        do {
            x = WINDOW_WIDTH;
            y = random.nextInt(WINDOW_HEIGTH - asteroid.getD());
        } while (isAsteroidCrossAsteroid(new Asteroid(x, y, asteroid.getD())));
        asteroid.setX(x);
        asteroid.setY(y);
    }

    private boolean isCollision(Asteroid asteroid1, Asteroid asteroid2) {
        double x1 = asteroid1.getX() + asteroid1.getD() / 2;
        double y1 = asteroid1.getY() + asteroid1.getD() / 2;
        double x2 = asteroid2.getX() + asteroid2.getD() / 2;
        double y2 = asteroid2.getY() + asteroid2.getD() / 2;
        double d = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

        boolean result = d < (asteroid1.getD()/2 + asteroid2.getD()/2);
        return result;
    }

    private boolean isCollision(Bullet bullet, Asteroid asteroid) {
        double x1 = bullet.getX() + bullet.getD() / 2;
        double y1 = bullet.getY() + bullet.getD() / 2;
        double x2 = asteroid.getX() + asteroid.getD() / 2;
        double y2 = asteroid.getY() + asteroid.getD() / 2;
        double d = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

        boolean result = d < (bullet.getD()/2 + asteroid.getD()/2);
        return result;
    }

    private boolean isCollision(StarShip starShip, Asteroid asteroid) {
        double x1 = starShip.getX() - starShip.getDx() / 2;
        double y1 = starShip.getY();
        double x2 = asteroid.getX() + asteroid.getD() / 2;
        double y2 = asteroid.getY() + asteroid.getD() / 2;
        double d = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        double radiusPlusRadius = starShip.getDx()/2 + asteroid.getD()/2;

        boolean result = d + 25 < radiusPlusRadius;
        return result;
    }

    private class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            // stars
            stars.paint(g);

            // star ship
            starShip.paint(g);

            // bullets
            for (Bullet bullet : bullets) {
                bullet.paint(g);
            }

            // asteroids
            for (Asteroid asteroid : asteroids) {
                asteroid.paint(g);
            }
            // канва
        }
    }
}
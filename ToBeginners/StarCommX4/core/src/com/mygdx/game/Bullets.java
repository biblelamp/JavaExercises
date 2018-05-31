package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullets {
    Texture imgBullet;
    static Bullet[] bullets;

    class Bullet {
        Vector2 position;
        float speed;
        boolean active; // true or false

        Bullet() {
            position = new Vector2();
            speed = 14f;
            active = false;
        }

        void deactivate() {
            active = false;
        }

        void activate(float x, float y) {
            position.set(x, y);
            active = true;
        }

        void update() {
            position.x += speed;
            if (position.x > Gdx.graphics.getWidth())
                deactivate();
        }
    }

    public Bullets() {
        imgBullet = new Texture("bullet20.tga");
        bullets = new Bullet[100];
        for (int i = 0; i < bullets.length; i++)
            bullets[i] = new Bullet();
    }

    public void render(SpriteBatch batch) {
        for (Bullet bullet : bullets)
            if (bullet.active)
                batch.draw(imgBullet,
                        bullet.position.x - imgBullet.getWidth() / 2,
                        bullet.position.y - imgBullet.getHeight() / 2);
    }

    public void update() {
        for (Bullet bullet : bullets)
            if (bullet.active) {
                bullet.update();
                for (Asteroids.Asteroid asteroid : Asteroids.asteroids)
                    if (asteroid.hitBox.contains(bullet.position)) {
                        asteroid.recreate();
                        bullet.deactivate();
                    }
            }
    }
}
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Asteroids {
    Texture imgAsteroid;
    static Asteroid[] asteroids;

    class Asteroid {
        Vector2 position;
        float speed;
        Rectangle hitBox;

        Asteroid() {
            position = new Vector2(
                    MathUtils.random(Gdx.graphics.getWidth(), Gdx.graphics.getWidth() * 2),
                    MathUtils.random(0, Gdx.graphics.getHeight()));
            speed = MathUtils.random(3.0f, 6.0f);
            hitBox = new Rectangle(position.x, position.y,
                    imgAsteroid.getWidth(), imgAsteroid.getHeight());
        }

        void recreate() {
            position.set(Gdx.graphics.getWidth(),
                    MathUtils.random(0, Gdx.graphics.getHeight()));
            speed = MathUtils.random(3.0f, 6.0f);
        }

        void update() {
            position.x -= speed;
            if (position.x < -imgAsteroid.getWidth())
                recreate();
            for (int i = 0; i < asteroids.length; i++)
                if (!this.equals(asteroids[i]))
                    if (asteroids[i].hitBox.overlaps(hitBox))
                        if (asteroids[i].position.y < position.y) {
                            asteroids[i].position.y -= asteroids[i].speed / 6;
                            asteroids[i].position.x -= asteroids[i].speed / 6;
                            //asteroids[i].speed += 0.5;
                            position.y += speed / 6;
                            position.x += speed / 3;
                            //speed -= 0.5;
                        } else {
                            asteroids[i].position.y += asteroids[i].speed / 6;
                            asteroids[i].position.x -= asteroids[i].speed / 6;
                            //asteroids[i].speed += 1;
                            position.y -= speed / 6;
                            position.x += speed / 3;
                            //speed -= 0.5;
                        }
            hitBox.setPosition(position);
        }
    }

    public Asteroids() {
        imgAsteroid = new Texture("asteroid50.tga");
        asteroids = new Asteroid[20];
        for (int i = 0; i < asteroids.length; i++)
            asteroids[i] = new Asteroid();
    }

    public void render(SpriteBatch batch) {
        for (Asteroid asteroid : asteroids)
            batch.draw(imgAsteroid, asteroid.position.x, asteroid.position.y);
    }

    public void update() {
        for (Asteroid asteroid : asteroids)
            asteroid.update();
    }
}
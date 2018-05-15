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

        public Asteroid() {
            position = new Vector2(
                    MathUtils.random(Gdx.graphics.getWidth(), Gdx.graphics.getWidth() * 2),
                    MathUtils.random(0, Gdx.graphics.getHeight()));
            speed = MathUtils.random(3.0f, 6.0f);
            hitBox = new Rectangle(position.x, position.y, imgAsteroid.getWidth(),
                    imgAsteroid.getHeight());
        }

        public void recreate() {
            position.set(Gdx.graphics.getWidth(),
                    MathUtils.random(0, Gdx.graphics.getHeight()));
            speed = MathUtils.random(3.0f, 6.0f);
        }

        public void update() {
            position.x -= speed;
            if (position.x < -imgAsteroid.getWidth())
                recreate();
            hitBox.setPosition(position);
        }
    }

    public Asteroids() {
        imgAsteroid = new Texture("asteroid60.tga");
        asteroids = new Asteroid[20];
        for (int i = 0; i < asteroids.length; i++)
            asteroids[i] = new Asteroid();
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < asteroids.length; i++)
            batch.draw(imgAsteroid, asteroids[i].position.x, asteroids[i].position.y);
    }

    public void update() {
        for (int i = 0; i < asteroids.length; i++)
            asteroids[i].update();
    }
}
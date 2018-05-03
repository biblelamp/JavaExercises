package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Asteroid {
    Texture imgAsteroid;
    Vector2 position;
    float speed;
    Rectangle hitBox;

    public Asteroid(Texture imgAsteroid) {
        this.imgAsteroid = imgAsteroid;
        position = new Vector2();
        recreate();
    }

    public void render(SpriteBatch batch) {
        batch.draw(imgAsteroid, position.x, position.y);
    }

    public void recreate() {
        position.set(MathUtils.random(1024, 2048), MathUtils.random(0, 576));
        hitBox = new Rectangle(position.x, position.y, imgAsteroid.getWidth(), imgAsteroid.getHeight());
        speed = MathUtils.random(3.0f, 6.0f);
    }

    public void update() {
        position.x -= speed;
        if (position.x < -imgAsteroid.getWidth())
            recreate();
        hitBox.setPosition(position);
    }
}
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Asteroid {
    Texture imgAsteroid;
    Vector2 position;
    float speed;

    public Asteroid() {
        imgAsteroid = new Texture("asteroid60.tga");
        recreate();
    }

    public void render(SpriteBatch batch) {
        batch.draw(imgAsteroid, position.x, position.y);
    }

    public void recreate() {
        position = new Vector2(MathUtils.random(1024, 2048), MathUtils.random(0, 576));
        speed = MathUtils.random(3.0f, 6.0f);
    }

    public void update() {
        position.x -= speed;
        if(position.x < 0)
            recreate();
    }
}
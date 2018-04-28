package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Asteroid {
    private Vector2 position;
    private float speed;
    private static Texture texture;

    public Asteroid() {
        if (texture == null)
            texture = new Texture("asteroid60.tga");
        position = new Vector2(1280 + (float)Math.random() * 1280, (float)Math.random() * 720);
        speed = 2.0f + (float)Math.random() * 3.0f;
    }

    public Rectangle getRect() {
        return new Rectangle(position.x, position.y, 60, 60);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void update() {
        position.x -= speed;
        if(position.x < -40) recreate();
    }

    public void recreate() {
        position.x = 1280 + (float)Math.random() * 1280;
        position.y = (float)Math.random() * 720;
    }
}

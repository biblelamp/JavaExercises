package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    Texture imgBullet;
    Vector2 position;
    float speed;
    boolean active; // true or false

    public Bullet(Texture imgBullet) {
        this.imgBullet = imgBullet;
        position = new Vector2(0, 0);
        speed = 14f;
        active = false;
    }

    public void render(SpriteBatch batch) {
        batch.draw(imgBullet, position.x - imgBullet.getWidth()/2, position.y - imgBullet.getHeight()/2);
    }

    public void deactivate() {
        active = false;
    }

    public void activate(float x, float y) {
        position.set(x, y);
        active = true;
    }

    public void update() {
        position.x += speed;
        if(position.x > 1024)
            deactivate();
    }
}
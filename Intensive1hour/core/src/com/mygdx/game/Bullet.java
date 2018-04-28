package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Bullet {
    private Vector2 position;
    private float speed;
    private boolean active;

    public Vector2 getPosition() {
        return position;
    }

    public boolean isActive() {
        return active;
    }

    public Bullet() {
        active = false;
        position = new Vector2(0.0f, 0.0f);
        speed = 10.0f;
    }

    public void update() {
        position.x += speed;
        if (position.x > 1300) {
            destroy();
        }
    }

    public void setup(float x, float y) {
        active = true;
        position.x = x;
        position.y = y;
    }

    public void destroy() {
        active = false;
    }
}
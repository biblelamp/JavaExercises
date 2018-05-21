package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    Texture imgShip;
    Vector2 position;
    float speed;
    int fireCounter;

    public Hero() {
        imgShip = new Texture("ship80x60.tga");
        position = new Vector2(100, Gdx.graphics.getHeight()/2);
        speed = 8.0f;
        fireCounter = 0;
    }

    public void render(SpriteBatch batch) {
        batch.draw(imgShip, position.x, position.y);
    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            position.y -= speed;
            if (position.y < 0)
                position.y = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            position.y += speed;
            if (position.y > Gdx.graphics.getHeight() - imgShip.getHeight())
                position.y = Gdx.graphics.getHeight() - imgShip.getHeight();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += speed;
            if (position.x > Gdx.graphics.getWidth() - imgShip.getWidth())
                position.x = Gdx.graphics.getWidth() - imgShip.getWidth();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= speed;
            if (position.x < 0)
                position.x = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isTouched()) {
            fireCounter++;
            if (fireCounter > 8) { // control of the rate of fire
                fireCounter = 0;
                fire();
            }
        }
    }

    public void fire() {
        for (Bullets.Bullet bullet : Bullets.bullets)
            if (!bullet.active) {
                bullet.activate(position.x + imgShip.getWidth(),
                        position.y + imgShip.getHeight()/2);
                break;
            }
    }
}
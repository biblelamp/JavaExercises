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
        position = new Vector2(100, 288);
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
            if (position.y > 576 - 60)
                position.y = 576 - 60;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += speed;
            if (position.x > 1024 - 80)
                position.x = 1024 - 80;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= speed;
            if (position.x < 0)
                position.x = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            fireCounter++;
            if (fireCounter > 8) { // control of the rate of fire
                fireCounter = 0;
                fire();
            }
        }
    }

    public void fire() {
        for (int i = 0; i < MyGdxGame.bullets.length; i++) {
            if(!MyGdxGame.bullets[i].active) {
                MyGdxGame.bullets[i].activate(position.x + imgShip.getWidth(), position.y + imgShip.getHeight()/2);
                break;
            }
        }
    }
}
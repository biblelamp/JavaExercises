package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    Texture imgShip;
    Vector2 position;
    float speed;

    public Hero() {
        imgShip = new Texture("ship80x60.tga");
        position = new Vector2(100, 288);
        speed = 10.0f;
    }

    public void render(SpriteBatch batch) {
        batch.draw(imgShip, position.x, position.y);
    }

    public void update() {

    }
}
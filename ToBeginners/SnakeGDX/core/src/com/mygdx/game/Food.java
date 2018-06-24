package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Food extends Cell {

    public Food() {
        super(new Vector2(32 * 15, 32 * (Gdx.graphics.getHeight() / 64)),
                new Texture("cup.png"));
    }

    public boolean isFood(float x, float y) {
        return position.x == x && position.y == y;
    }

    public void reset() {
        position.set(32 * MathUtils.random(0, (Gdx.graphics.getWidth() / 32) - 1),
                32 * MathUtils.random(0, (Gdx.graphics.getHeight() / 32) - 1));
    }
}
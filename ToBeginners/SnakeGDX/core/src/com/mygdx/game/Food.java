package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Food extends Cell {

    public Food() {
        super(new Vector2(32 * 15, 32 * 9), new Texture("cup.png"));
    }

    public boolean isFood(float x, float y) {
        return position.x == x && position.y == y;
    }

    public void reset() {
        position.set(32 * MathUtils.random(0, 31), 32 * MathUtils.random(0, 17));
    }
}
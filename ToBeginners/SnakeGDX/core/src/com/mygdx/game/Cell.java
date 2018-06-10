package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Cell {
    private Vector2 position;
    private Texture img;

    public Cell(Vector2 position, Texture img) {
        this.position = position;
        this.img = img;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public void render(SpriteBatch batch) {
        batch.draw(img, position.x, position.y);
    }
}
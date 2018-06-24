package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Cell {
    protected Vector2 position;
    protected Texture img;

    public Cell(Vector2 position, Texture img) {
        this.position = position;
        this.img = img;
    }

    public void render(SpriteBatch batch) {
        batch.draw(img, position.x, position.y);
    }
}
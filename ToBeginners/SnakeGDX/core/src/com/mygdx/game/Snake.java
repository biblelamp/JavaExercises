package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.ArrayList;

public class Snake {
    private List<Cell> snake;
    private int direction;
    private Texture img;

    public Snake() {
        snake = new ArrayList<Cell>();
        img = new Texture("gear.png");
        for (int i = 0; i < 5; i++)
            snake.add(new Cell(new Vector2(150 - i * 32, 230), img));
        direction = Input.Keys.RIGHT;
    }

    public void render(SpriteBatch batch) {
        for (Cell cell : snake)
            cell.render(batch);
    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            direction = Input.Keys.DOWN;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            direction = Input.Keys.UP;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            direction = Input.Keys.LEFT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            direction = Input.Keys.RIGHT;
        }
        float x = snake.get(0).getX();
        float y = snake.get(0).getY();
        switch (direction) {
            case Input.Keys.UP: y += img.getHeight();
                break;
            case Input.Keys.DOWN: y -= img.getHeight();
                break;
            case Input.Keys.RIGHT: x += img.getWidth();
                break;
            case Input.Keys.LEFT: x -= img.getWidth();
                break;
        }
        snake.add(0, new Cell(new Vector2(x, y), img));
        snake.remove(snake.size() - 1);
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {}
    }
}

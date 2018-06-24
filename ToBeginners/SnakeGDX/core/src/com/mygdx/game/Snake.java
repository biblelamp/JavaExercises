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
    private Texture img;
    private int direction;

    public Snake() {
        snake = new ArrayList<Cell>();
        img = new Texture("gear.png");
        for (int i = 0; i < 5; i++)
            snake.add(new Cell(new Vector2(160 - i * 32,
                    32 * (Gdx.graphics.getHeight() / 64)), img));
        direction = Input.Keys.RIGHT;
    }

    public void render(SpriteBatch batch) {
        for (Cell cell : snake)
            cell.render(batch);
    }

    public int isInside(float x, float y) {
        for (int i = 1; i < snake.size(); i++)
            if (snake.get(i).position.x == x && snake.get(i).position.y == y)
                return i;
        return -1;
    }

    public void update(Food food) {
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) &&
                direction != Input.Keys.UP)
            direction = Input.Keys.DOWN;
        if (Gdx.input.isKeyPressed(Input.Keys.UP) &&
                direction != Input.Keys.DOWN)
            direction = Input.Keys.UP;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) &&
                direction != Input.Keys.RIGHT)
            direction = Input.Keys.LEFT;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&
                direction != Input.Keys.LEFT)
            direction = Input.Keys.RIGHT;
        float x = snake.get(0).position.x;
        float y = snake.get(0).position.y;
        switch (direction) {
            case Input.Keys.UP:
                y += img.getHeight();
                if (y == Gdx.graphics.getHeight())
                    y = 0;
                break;
            case Input.Keys.DOWN:
                y -= img.getHeight();
                if (y < 0)
                    y = Gdx.graphics.getHeight() - img.getHeight();
                break;
            case Input.Keys.RIGHT:
                x += img.getWidth();
                if (x == Gdx.graphics.getWidth())
                    x = 0;
                break;
            case Input.Keys.LEFT:
                x -= img.getWidth();
                if (x < 0)
                    x = Gdx.graphics.getWidth() - img.getWidth();
                break;
        }
        snake.add(0, new Cell(new Vector2(x, y), img));
        int cutPoint = isInside(x, y);
        if (cutPoint > -1)
            for (int i = cutPoint; i < snake.size(); i++)
                snake.remove(i);
        else if (food.isFood(x, y))
            food.reset();
        else
            snake.remove(snake.size() - 1);
        Gdx.graphics.setTitle("Snake: " + snake.size());
    }
}
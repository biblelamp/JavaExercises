package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SnakeGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Snake snake;
    Food food;
    int slowingCounter;

    @Override
    public void create() {
        batch = new SpriteBatch();
        snake = new Snake();
        food = new Food();
        slowingCounter = 0;
    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        snake.render(batch);
        food.render(batch);
        batch.end();
    }

    public void update() {
        slowingCounter++;
        if (slowingCounter == 10) {
            slowingCounter = 0;
            snake.update(food);
        }
    }
}
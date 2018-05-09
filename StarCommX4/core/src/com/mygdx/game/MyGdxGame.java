package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Background background;
    Hero hero;
    Asteroids asteroids;
    Bullets bullets;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Background();
        hero = new Hero();
        asteroids = new Asteroids();
        bullets = new Bullets();
    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(0, 0, 15/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.render(batch);
        hero.render(batch);
        asteroids.render(batch);
        bullets.render(batch);
        batch.end();
    }

    public void update() {
        background.update();
        hero.update();
        asteroids.update();
        bullets.update();
    }
}
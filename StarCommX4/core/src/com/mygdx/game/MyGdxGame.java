package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background bg;
	Hero hero;
	Asteroid[] asteroids;

	@Override
	public void create() {
		batch = new SpriteBatch();
		bg = new Background();
		hero = new Hero();
		asteroids = new Asteroid[20];
        for (int i = 0; i < asteroids.length; i++) {
            asteroids[i] = new Asteroid();
        }
	}

	@Override
	public void render() {
	    update();
		Gdx.gl.glClearColor(0, 0, 15/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        bg.render(batch);
        hero.render(batch);
        for (int i = 0; i < asteroids.length; i++) {
            asteroids[i].render(batch);
        }
		batch.end();
	}

    public void update() {
        bg.update();
        hero.update();
        for (int i = 0; i < asteroids.length; i++) {
            asteroids[i].update();
        }
	}

	/*@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}*/
}
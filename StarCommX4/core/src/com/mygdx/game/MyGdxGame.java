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
    //static Bullet[] bullets;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Background();
        hero = new Hero();
        asteroids = new Asteroids();
        /*Texture imgBullet = new Texture("bullet20.tga");
        bullets = new Bullet[100];
        for (int i = 0; i < bullets.length; i++)
            bullets[i] = new Bullet(imgBullet);
        */
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
        /*for (int i = 0; i < bullets.length; i++)
            if (bullets[i].active)
                bullets[i].render(batch);
        */
        batch.end();
    }

    public void update() {
        background.update();
        hero.update();
        asteroids.update();
        /*for (int i = 0; i < bullets.length; i++)
            if (bullets[i].active) {
                bullets[i].update();
                for (int j = 0; j < asteroids.length; j++)
                    if (asteroids[j].hitBox.contains(bullets[i].position)) {
                        asteroids[j].recreate();
                        bullets[i].deactivate();
                    }
            }
         */
    }
}
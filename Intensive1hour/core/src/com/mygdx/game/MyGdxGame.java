package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Background bg;
    Hero hero;
    Hero hero2;
    Texture texBullets;
    private final int AST_COUNT = 20;
    private final int BULLETS_COUNT = 150;
    Asteroid[] asteroids;
    public static Bullet[] bullets;

    @Override
    public void create() {
        batch = new SpriteBatch();
        bg = new Background();
        hero = new Hero(100, 100, new KeysControl(Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.UP, Input.Keys.DOWN, Input.Keys.P));
        hero2 = new Hero(100, 300, new KeysControl(Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S, Input.Keys.H));
        asteroids = new Asteroid[AST_COUNT];
        for (int i = 0; i < AST_COUNT; i++) {
            asteroids[i] = new Asteroid();
        }
        bullets = new Bullet[BULLETS_COUNT];
        for (int i = 0; i < BULLETS_COUNT; i++) {
            bullets[i] = new Bullet();
        }
        texBullets = new Texture("bullet20.tga");
    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(1, 1, 1, 1);// RGBA
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        bg.render(batch);
        hero.render(batch);
        hero2.render(batch);
        for (int i = 0; i < AST_COUNT; i++) {
            asteroids[i].render(batch);
        }
        for (int i = 0; i < BULLETS_COUNT; i++) {
            if (bullets[i].isActive())
                batch.draw(texBullets, bullets[i].getPosition().x, bullets[i].getPosition().y);
        }
        batch.end();
    }

    public void update() {
        bg.update();
        hero.update();
        hero2.update();
        for (int i = 0; i < AST_COUNT; i++) {
            asteroids[i].update();
        }
        for (int i = 0; i < BULLETS_COUNT; i++) {
            if (bullets[i].isActive())
                bullets[i].update();
        }
        for (int i = 0; i < BULLETS_COUNT; i++) {
            if(bullets[i].isActive()) {
                for (int j = 0; j < AST_COUNT; j++) {
                    if(asteroids[j].getRect().contains(bullets[i].getPosition())) {
                        bullets[i].destroy();
                        asteroids[j].recreate();
                        break;
                    }
                }
            }
        }
    }
}

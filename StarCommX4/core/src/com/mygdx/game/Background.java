package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background {
    class Star {
        Vector2 position;
        float speed;

        public Star() {
            position = new Vector2((float)Math.random() * 1024, (float)Math.random() * 576);
            speed = 1.0f + (float)Math.random() * 5.0f;
        }

        public void update() {
            position.x -= speed;
            if(position.x < 0) {
                position.x = 1024;
                position.y = (float)Math.random() * 576;
            }
        }
    }

    private Texture texture;
    private Texture texStar;
    private final int STARS_COUNT = 200;
    private Star[] stars;

    public Background() {
        texture = new Texture("bg.png");
        texStar = new Texture("star12.tga");
        stars = new Star[STARS_COUNT];
        for (int i = 0; i < STARS_COUNT; i++) {
            stars[i] = new Star();
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, 0, 0);
        for (int i = 0; i < STARS_COUNT; i++) {
            batch.draw(texStar, stars[i].position.x, stars[i].position.y);
        }
    }

    public void update() {
        for (int i = 0; i < STARS_COUNT; i++) {
            stars[i].update();
        }
    }
}
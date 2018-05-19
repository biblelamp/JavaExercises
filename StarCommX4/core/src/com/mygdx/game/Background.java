package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Background {
    Texture imgStar;
    Star[] stars;

    class Star {
        Vector2 position;
        float speed;

        Star() {
            position = new Vector2(MathUtils.random(0, Gdx.graphics.getWidth()),
                    MathUtils.random(0, Gdx.graphics.getHeight()));
            speed = MathUtils.random(0.5f, 2.0f);
        }

        void update() {
            position.x -= speed;
            if (position.x < 0) {
                position.set(Gdx.graphics.getWidth(),
                        MathUtils.random(0, Gdx.graphics.getHeight()));
                speed = MathUtils.random(0.5f, 2.0f);
            }
        }
    }

    public Background() {
        imgStar = new Texture("star12.tga");
        stars = new Star[200];
        for (int i = 0; i < stars.length; i++)
            stars[i] = new Star();
    }

    public void render(SpriteBatch batch) {
        for (Star star : stars) {
            float size = star.speed / 2.0f; // making 3D effect
            if (MathUtils.random(0, 40) == 0)   // flicker of stars
                size *= 1.8f;
            batch.draw(imgStar, star.position.x, star.position.y,
                    imgStar.getWidth()/2, imgStar.getHeight()/2,
                    imgStar.getWidth(), imgStar.getHeight(),
                    size, size, 0, 0, 0,
                    imgStar.getWidth(), imgStar.getHeight(), false, false);
        }
    }

    public void update() {
        for (Star star : stars)
            star.update();
    }
}
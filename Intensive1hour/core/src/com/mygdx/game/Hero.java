package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    private Vector2 position;
    private float speed;
    private Texture texture;
    private int fireCounter;
    private final int fireRate = 10;
    private KeysControl kc;

    public Hero(float x, float y, KeysControl kc) {
        texture = new Texture("ship80x60.tga");
        position = new Vector2(x, y);
        this.kc = kc;
        speed = 5.0f;
        fireCounter = 0;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void update() {
        if(Gdx.input.isKeyPressed(kc.kFire)) {
            fireCounter++;
            if(fireCounter > fireRate) {
                fireCounter -= fireRate;
                for (int i = 0; i < MyGdxGame.bullets.length; i++) {
                    if(!MyGdxGame.bullets[i].isActive()) {
                        MyGdxGame.bullets[i].setup(position.x, position.y);
                        break;
                    }
                }
            }
        }
        if(Gdx.input.isKeyPressed(kc.kDown)) {
            position.y -= speed;
            if(position.y < -80) position.y = 800;
        }
        if(Gdx.input.isKeyPressed(kc.kUp)) {
            position.y += speed;
            if(position.y > 800) position.y = -80;
        }
        if(Gdx.input.isKeyPressed(kc.kRight)) {
            position.x += speed;
        }
        if(Gdx.input.isKeyPressed(kc.kLeft)) {
            position.x -= speed;
        }
    }


}

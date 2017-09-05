package com.bees.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScrollingBackground {

    public static final int default_speed = 180;
    public static final int ACCELERATION = 50;
    public static final int GOAL_REACH_ACCELERATION = 200;
    Texture image;
    float x1, x2;
    float speed;
    private float imageScale;
    private int goalSpeed;

    private boolean speedFixed;

    public ScrollingBackground() {
        image = new Texture("background.png");
        x1 = 0;
        x2 = image.getWidth();

        speed = 0;
        goalSpeed = default_speed;

        imageScale = (float) Gdx.graphics.getHeight() / image.getHeight();
        speedFixed = true;
    }

    public void updateAndRender(float deltaTime, SpriteBatch batch2) {
        if (speed < goalSpeed) {
            speed += GOAL_REACH_ACCELERATION * deltaTime;
            if (speed > goalSpeed)
                speed = goalSpeed;
        } else if (speed > goalSpeed) {
            speed -= GOAL_REACH_ACCELERATION * deltaTime;
            if (speed < goalSpeed)
                speed = goalSpeed;
        }

        if (!speedFixed)
            speed += ACCELERATION * deltaTime;

        x1 -= speed * deltaTime;
        x2 -= speed * deltaTime;

        /* if image reached the bottom of screen and is not visible, put it back on top */
        if (x1 +  image.getWidth() * imageScale <= 0) {
            x1 = x2 + image.getWidth() * imageScale;
        }

        if (x2 +  image.getWidth() * imageScale <= 0) {
            x2 = x1 + image.getWidth() * imageScale;
        }

        /* Render (Texture texture, float x, float y, float width, float height)  */
        batch2.draw(image, x1, 0,  image.getWidth() * imageScale, MyBeesGame.HEIGHT);
        batch2.draw(image, x2, 0,  image.getWidth() * imageScale, MyBeesGame.HEIGHT);
    }

    public void setSpeed(int goalSpeed) {
        this.goalSpeed = goalSpeed;
    }

    public void setSpeedFixed(boolean speedFixed) {
        this.speedFixed = speedFixed;
    }


    public void resize(int width, int height) {
        this.imageScale = (float) height / image.getHeight();
    }
}


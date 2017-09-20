package com.bees.game;

import com.badlogic.gdx.math.Rectangle;

public class Flower {
    private boolean answered;
    private Rectangle rectangle;

    public Flower(Rectangle flower, boolean b) {
        this.rectangle = flower;
        this.answered = b;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean getAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
}

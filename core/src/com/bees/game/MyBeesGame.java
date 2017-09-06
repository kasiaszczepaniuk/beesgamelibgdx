package com.bees.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class MyBeesGame extends Game {

    public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;
    public SpriteBatch batch2;
    public SpriteBatch batch;


    @Override
    public void create() {


        batch = new SpriteBatch();
        batch2 = new SpriteBatch();
        this.setScreen(new MainMenuScreen(this));
    }



        public void render() {
            super.render();

        }


        public void dispose() {

            batch.dispose();
        }

        public void resize ( int width, int height){

        }
    }



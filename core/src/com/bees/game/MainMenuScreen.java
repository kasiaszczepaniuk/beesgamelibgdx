package com.bees.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.bees.game.MyBeesGame.HEIGHT;
import static com.bees.game.MyBeesGame.WIDTH;

public class MainMenuScreen implements Screen {

    final MyBeesGame game;
    OrthographicCamera camera;
    private Texture start;
    private Texture stillgame;
    private Texture quit;
    public static Texture backgroundMenu;


    public static SpriteBatch batch3;


    public MainMenuScreen (final MyBeesGame game){

        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        backgroundMenu = new Texture("backbuttons.png");


        start = new Texture(Gdx.files.internal("start.png"));
//        stillgame = new Texture(Gdx.files.internal("continue.png"));
//        quit = new Texture(Gdx.files.internal("quit.png"));


        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        game.batch.draw(backgroundMenu, 0, 0);
        game.batch.draw(start,600,550);
//        game.batch.draw(stillgame, 600, 550);
//        game.batch.draw(quit, 600, 350);



        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new TheGameScreen(game));
            dispose();
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

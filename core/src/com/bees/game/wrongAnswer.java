package com.bees.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.bees.game.MyBeesGame.HEIGHT;
import static com.bees.game.MyBeesGame.WIDTH;

public class wrongAnswer implements Screen {


    private final MyBeesGame game;
    private final OrthographicCamera camera;
    private final Texture backgroundWrong;
    private final SpriteBatch batch;

    public wrongAnswer (final MyBeesGame game){

        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
        backgroundWrong = new Texture("badanswer.png");

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                //START BUTTON

                wrongAnswer.this.dispose();
                game.setScreen(new TheGameScreen(game));
                return super.touchDown(screenX, screenY, pointer, button);
            }
        });

        batch = new SpriteBatch();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        camera.update();
        batch.begin();
        batch.draw(backgroundWrong, 0, 0);
        batch.end();

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
        backgroundWrong.dispose();
        batch.dispose();
        Gdx.input.setInputProcessor(null);
    }
}

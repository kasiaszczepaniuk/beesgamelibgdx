package com.bees.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.bees.game.MyBeesGame.HEIGHT;
import static com.bees.game.MyBeesGame.WIDTH;

public class RightAnswer {

    private final Texture backgroundRight;
    private final SpriteBatch batch;
    private OrthographicCamera camera;
    private TheGameScreen gameScreen;

    public RightAnswer(final TheGameScreen gameScreen) {

        this.gameScreen = gameScreen;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
        backgroundRight = new Texture("goodanswer.png");


        batch = new SpriteBatch();

    }


    public void render() {

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {

                gameScreen.backtobee(TheGameScreen.GameState.RIGHTANSWER);


                Gdx.input.setInputProcessor(null);


                return super.touchDown(screenX, screenY, pointer, button);
            }
        });


        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backgroundRight, 0, 0);
        batch.end();
    }


    public void resize(int width, int height) {

    }

    public void pause() {

    }


    public void resume() {

    }


    public void hide() {

    }


    public void dispose() {
        batch.dispose();
        backgroundRight.dispose();

    }
}

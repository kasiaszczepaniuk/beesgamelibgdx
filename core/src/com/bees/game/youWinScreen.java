package com.bees.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import static com.bees.game.MyBeesGame.HEIGHT;
import static com.bees.game.MyBeesGame.WIDTH;

public class youWinScreen implements Screen {


    final MyBeesGame game;
    private final OrthographicCamera camera;
    private final Texture winback;
    public final Texture winbutton;
    private final int winButtonWidth;
    private final int winbuttonX;
    private final int winButtonY;

    public youWinScreen (final MyBeesGame game)
    {

        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
        winback = new Texture("winback.png");
        winbutton = new Texture("winbutton.png");


        //TRYAGAIN BUTTON
        winButtonWidth = winbutton.getWidth();
        winbuttonX = (2* MyBeesGame.WIDTH / 3 ) - winButtonWidth / 2 ;
        winButtonY = 200;



    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(winback, 0, 0);
        game.batch.draw(winbutton, winbuttonX, winButtonY);

        game.batch.end();

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

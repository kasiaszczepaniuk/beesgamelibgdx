package com.bees.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import javax.xml.transform.Templates;

import static com.bees.game.MyBeesGame.HEIGHT;
import static com.bees.game.MyBeesGame.WIDTH;

public class GameOverScreen implements Screen{
    final MyBeesGame game;
    public final Texture gameoverback;
    public final Texture trybutton;
    private final int tryButtonWidth;
    private final int trybuttonX;
    private final int tryButtonY;
    private final int quitButtonY;
    private final int quitButtonWidth;
    private final Texture quit;
    private final int quitButtonX;
    OrthographicCamera camera;


    public GameOverScreen(final MyBeesGame game) {

        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);

        gameoverback = new Texture("gameoverback.png");
        trybutton = new Texture("trybutton.png");
        quit = new Texture("quit.png");


//        //TRYAGAIN BUTTON
          tryButtonWidth = trybutton.getWidth();
          trybuttonX = (2* MyBeesGame.WIDTH / 3 ) - tryButtonWidth / 2 ;
          tryButtonY = 200;

        //QUITBUTTON
        quitButtonY = 90;
        quitButtonWidth = quit.getWidth();
        quitButtonX = (2*MyBeesGame.WIDTH / 3) - quitButtonWidth / 2;

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                //TRY BUTTON
                if ((trybuttonX < screenX && trybuttonX + tryButtonWidth > screenX)
                        && (MyBeesGame.HEIGHT - getInputInGameWorld(camera).y < tryButtonY + trybutton.getHeight() && MyBeesGame.HEIGHT - getInputInGameWorld(camera).y > tryButtonY)) {
                    dispose();
                    game.setScreen(new TheGameScreen(game));
                }
                //QUIT BUTTON
                if ((quitButtonX < screenX && quitButtonX + quitButtonWidth > screenX)
                        && (MyBeesGame.HEIGHT - getInputInGameWorld(camera).y < quitButtonY + quit.getHeight() && MyBeesGame.HEIGHT - getInputInGameWorld(camera).y > quitButtonY)) {
                    dispose();
                    Gdx.app.exit();
                }





                return super.touchDown(screenX, screenY, pointer, button);
            }
        });
//
//


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
        game.batch.draw(gameoverback, 0, 0);
        game.batch.draw(trybutton, trybuttonX , tryButtonY);
        game.batch.draw(quit,quitButtonX, quitButtonY);
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

    public Vector2 getInputInGameWorld(OrthographicCamera cam) {
        Vector3 inputScreen = new Vector3(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 0);
        Vector3 unprojected = cam.unproject(inputScreen);
        return new Vector2(unprojected.x, unprojected.y);
    }
}

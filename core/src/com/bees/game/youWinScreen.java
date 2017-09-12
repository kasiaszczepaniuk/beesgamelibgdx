package com.bees.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

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

    private final int quitButtonY;
    private final int quitButtonWidth;
    private final Texture quitbutton;
    private final int quitButtonX;
    private final SpriteBatch batch;

    public youWinScreen (final MyBeesGame game)
    {

        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
        winback = new Texture("winback.png");
        winbutton = new Texture("winbutton.png");
        quitbutton = new Texture("quit.png");

        //TRYAGAIN BUTTON
        winButtonWidth = winbutton.getWidth();
        winbuttonX = (2* MyBeesGame.WIDTH / 3 ) - winButtonWidth / 2 ;
        winButtonY = 200;

        //QUITBUTTON
        quitButtonY = 90;
        quitButtonWidth = quitbutton.getWidth();
        quitButtonX = (2*MyBeesGame.WIDTH / 3) - quitButtonWidth / 2;

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                //TRY BUTTON
                if ((winbuttonX < screenX && winbuttonX + winButtonWidth > screenX)
                        && (MyBeesGame.HEIGHT - getInputInGameWorld(camera).y < winButtonY + winbutton.getHeight() && MyBeesGame.HEIGHT - getInputInGameWorld(camera).y > winButtonY)) {
                    dispose();
                    game.setScreen(new TheGameScreen(game));
                }
                //QUIT BUTTON
                if ((quitButtonX < screenX && quitButtonX + quitButtonWidth > screenX)
                        && (MyBeesGame.HEIGHT - getInputInGameWorld(camera).y < quitButtonY + quitbutton.getHeight() && MyBeesGame.HEIGHT - getInputInGameWorld(camera).y > quitButtonY)) {
                    dispose();
                    Gdx.app.exit();
                }





                return super.touchDown(screenX, screenY, pointer, button);
            }
        });


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(winback, 0, 0);
        batch.draw(winbutton, winbuttonX, winButtonY);
        batch.draw(quitbutton, quitButtonX, quitButtonY);

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
        winbutton.dispose();
        quitbutton.dispose();
        batch.dispose();
        Gdx.input.setInputProcessor(null);

    }

    public Vector2 getInputInGameWorld(OrthographicCamera cam) {
        Vector3 inputScreen = new Vector3(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 0);
        Vector3 unprojected = cam.unproject(inputScreen);
        return new Vector2(unprojected.x, unprojected.y);
    }
}

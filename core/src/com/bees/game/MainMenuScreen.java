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

public class MainMenuScreen implements Screen {

    final MyBeesGame game;
    public Texture backgroundMenu;
    public SpriteBatch batch3;
    OrthographicCamera camera;
    private Texture start;
    private Texture stillgame;
    private Texture quit;
    private int playButtonY;
    private int playButtonWidth;
    private int buttonX;


    public MainMenuScreen(final MyBeesGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);

        backgroundMenu = new Texture("backbuttons.png");
        start = new Texture(Gdx.files.internal("start.png"));

        stillgame = new Texture(Gdx.files.internal("continue.png"));
//        quit = new Texture(Gdx.files.internal("quit.png"));

        playButtonY = 550;
        playButtonWidth = start.getWidth();
        buttonX = MyBeesGame.WIDTH / 2 - playButtonWidth / 2;

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {

                if ((buttonX < screenX && buttonX + playButtonWidth > screenX)
                        && (MyBeesGame.HEIGHT - getInputInGameWorld(camera).y < playButtonY + start.getHeight() && MyBeesGame.HEIGHT - getInputInGameWorld(camera).y > playButtonY)) {
                    dispose();
                    game.setScreen(new TheGameScreen(game));
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
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        game.batch.draw(backgroundMenu, 0, 0);
        game.batch.draw(start, buttonX, playButtonY);
        game.batch.draw(stillgame, 500, 350);
//        game.batch.draw(quit, buttonX, 350);


        game.batch.end();

//        if (Gdx.input.isTouched()) {
//            if(start.getWidth())
//            game.setScreen(new TheGameScreen(game));
//            dispose();
//        }

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
        backgroundMenu.dispose();
        start.dispose();

    }

    public Vector2 getInputInGameWorld(OrthographicCamera cam) {
        Vector3 inputScreen = new Vector3(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 0);
        Vector3 unprojected = cam.unproject(inputScreen);
        return new Vector2(unprojected.x, unprojected.y);
    }
}

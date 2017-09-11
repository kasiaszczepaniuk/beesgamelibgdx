package com.bees.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;

public class QuizScreen implements Screen {
    private final MyBeesGame game;
    private Stage stage;

    public QuizScreen(MyBeesGame stage) {
        game = stage;
    }

    @Override
    public void show() {

        stage = new Stage();
        stage.setViewport(new ScreenViewport());

        TextButton.TextButtonStyle textstyle = new TextButton.TextButtonStyle();
        textstyle.fontColor = Color.BLUE;
        BitmapFont font = new BitmapFont();
        textstyle.font = font;
        TextButton pytanie = new TextButton("byleco",textstyle);

        TextButton odp1 = new TextButton("kot", textstyle);
        TextButton odp2 = new TextButton("kot", textstyle);
        TextButton odp3 = new TextButton("kot", textstyle);


        Table odpowiedzi = new Table();

        odpowiedzi.add(pytanie);
        odpowiedzi.row();
        odpowiedzi.add(odp1, odp2, odp3);
        odpowiedzi.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);



        pytanie.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenuScreen(game));

                return super.touchDown(event, x, y, pointer, button);
            }});


        stage.addActor(odpowiedzi);
        Gdx.input.setInputProcessor(stage);



    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();



    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

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

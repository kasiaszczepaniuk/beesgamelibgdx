package com.bees.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static com.bees.game.MyBeesGame.HEIGHT;
import static com.bees.game.MyBeesGame.WIDTH;

public class QuizScreen implements Screen {
    private final MyBeesGame game;
    private final OrthographicCamera camera;
    private final Texture backgroundMenu;
    private final SpriteBatch batch;
    private Stage stage;
    private Skin skin;
    private Label labelka;
//    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/FjallaOne-Regular.ttf"));
//    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//    parameter.size = 12;
//    BitmapFont font12

    public QuizScreen(MyBeesGame stage) {


        game = stage;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);

        backgroundMenu = new Texture("questionback.png");
        batch = new SpriteBatch();
    }

    @Override
    public void show() {

        stage = new Stage();


        skin = new Skin();
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));


        TextButton.TextButtonStyle textstyle = new TextButton.TextButtonStyle();
        textstyle.fontColor = Color.BLACK;
        BitmapFont font = new BitmapFont();
        textstyle.font = font;
        textstyle.up = skin.newDrawable("white", Color.WHITE);
        textstyle.down = skin.newDrawable("white", Color.WHITE);
        textstyle.checked = skin.newDrawable("white", Color.BLUE);
        textstyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        stage.setViewport(new ScreenViewport());
//        String url = "jdbc:sqlite:sample.db";
//
//        ConnectionSource connectionSource = null;
//        TextButton button1 = null;
//        try {
//            connectionSource = new JdbcConnectionSource(url);
//            Dao<Question, String> questionDao = DaoManager.createDao(connectionSource, Question.class);
//            List<Question> questions = questionDao.queryForAll();
//
////            Question question = questions.get(2);
////            button1 = new TextButton(question.toString(), textstyle);
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


        skin.add("default", textstyle);

//        TextButton pytanie = new TextButton("Co to za zwirz?", skin);

        Image pytanie = new Image();
        pytanie.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("2.png")))));
        pytanie.setSize(200, 200);
        pytanie.setScaling(Scaling.fit);

        TextButton odp1 = new TextButton("niedzwiedz", skin);
        TextButton odp2 = new TextButton("pies", skin);
        TextButton odp3 = new TextButton("kot", skin);


        Table odpowiedzi = new Table();
        odpowiedzi.setHeight(500);
//        odpowiedzi.setFillParent(true);

        odpowiedzi.add(pytanie);
        odpowiedzi.row();
        odpowiedzi.add(odp1);
        odpowiedzi.row();
        odpowiedzi.add(odp2);
        odpowiedzi.row();
        odpowiedzi.add(odp3);
        odpowiedzi.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);


        pytanie.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                QuizScreen.this.dispose();
                game.setScreen(new MainMenuScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        odp1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                QuizScreen.this.dispose();
                game.setScreen(new RightAnswer(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        odp2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                QuizScreen.this.dispose();
                game.setScreen(new wrongAnswer(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        odp3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                QuizScreen.this.dispose();
                game.setScreen(new wrongAnswer(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });


        stage.addActor(odpowiedzi);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(backgroundMenu, 0, 0);
        batch.end();

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
        batch.dispose();
        Gdx.input.setInputProcessor(null);
    }
}

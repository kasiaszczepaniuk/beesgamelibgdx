package com.bees.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class TheGameScreen extends MyBeesGame implements Screen {
    //    final MyBeesGame game;
    private final SpriteBatch batch;
    public ScrollingBackground scrollingBackground;
    public int score;
    public String yourScoreName;
    public BitmapFont yourBitmapFontName;
    Vector3 touchPos = new Vector3();
    private Texture flowerImage;
    private Texture dropImage;
    private Texture bucketImage;
    private Sound dropSound;
    private Music rainMusic;
    private OrthographicCamera camera;
    private Rectangle bucket;
    private Array<Rectangle> raindrops;
    private Array<Flower> flowers;
    private long lastDropTime;
    private long lastFlower;

    private QuizScreen quizScreen;
    private ShapeRenderer menuAlphaBackground;
    private GameState currentGameState;
    private RightAnswer rightAnswer;


    public TheGameScreen() {

        score = 0;
        yourScoreName = "score: 0";
        yourBitmapFontName = new BitmapFont();
        yourBitmapFontName.getData().setScale(6);
        yourBitmapFontName.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        batch = new SpriteBatch();
        dropImage = new Texture(Gdx.files.internal("honey.png"));
        bucketImage = new Texture(Gdx.files.internal("bee.png"));
        flowerImage = new Texture(Gdx.files.internal("flo.png"));

        // load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.wav"));

        // start the playback of the background music immediately
        rainMusic.setLooping(true);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);

        this.scrollingBackground = new ScrollingBackground();

        bucket = new Rectangle();
        bucket.y = Gdx.graphics.getHeight() / 2 - 64 / 2;
        bucket.x = Gdx.graphics.getWidth() / 4 - 64 / 2;
        bucket.width = 64;
        bucket.height = 64;

        raindrops = new Array<Rectangle>();
        spawnRaindrop();
        flowers = new Array<Flower>();
        spawnFlolwer();
        menuAlphaBackground = new ShapeRenderer();
        quizScreen = new QuizScreen(this);

        rightAnswer = new RightAnswer(this);


        currentGameState = GameState.RUNNING;

    }

    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.y = MathUtils.random(0, HEIGHT - 64);
        raindrop.x = WIDTH;
        raindrop.width = 64;
        raindrop.height = 64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    private void spawnFlolwer() {
        Rectangle flower = new Rectangle();
        flower.y = 0;
        flower.x = WIDTH;
        flower.width = flowerImage.getWidth();
        flower.height = Gdx.graphics.getHeight();
        flowers.add(new Flower(flower, false));
        lastFlower = TimeUtils.millis();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        if (currentGameState == GameState.RUNNING) {
            scrollingBackground.update(Gdx.graphics.getDeltaTime());

            if (Gdx.input.isTouched()) {
                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchPos);
                bucket.y = Interpolation.linear.apply(bucket.y, touchPos.y, 0.05f);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) bucket.y -= 200 * Gdx.graphics.getDeltaTime();
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) bucket.y += 200 * Gdx.graphics.getDeltaTime();
            // make sure the bucket stays within the screen bounds
            if (bucket.y < 0) bucket.y = 0;
            if (bucket.y > HEIGHT - 64) bucket.y = HEIGHT - 64;

            if (TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

            Iterator<Rectangle> iter = raindrops.iterator();
            while (iter.hasNext()) {
                Rectangle raindrop = iter.next();
                raindrop.x -= 1000 * Gdx.graphics.getDeltaTime();
                if (raindrop.x + 64 < 0) iter.remove();
                if (raindrop.overlaps(bucket)) {
                    score++;
                    yourScoreName = "score: " + score;
                    dropSound.play();
                    iter.remove();
                }
            }

            if ((TimeUtils.millis() - lastFlower) / 1000 > 25) spawnFlolwer();
            Iterator<Flower> iter2 = flowers.iterator();
            while (iter2.hasNext()) {
                Flower flower = iter2.next();
                flower.getRectangle().x -= 200 * Gdx.graphics.getDeltaTime();
                if (flower.getRectangle().x + flower.getRectangle().width < 0) iter2.remove();
                if (flower.getRectangle().overlaps(bucket) && !flower.getAnswered()) {
                    score = score + 10;
                    yourScoreName = "score: " + score;
                    currentGameState = GameState.QUIZ;
                }
            }

            if ((score >= 100)) {
                ScreenManager.getInstance().showScreen(ScreenEnum.GAME_OVER);
            }
        }

        scrollingBackground.render(batch);

        batch.draw(bucketImage, bucket.x, bucket.y);

        for (Rectangle raindrop : raindrops) {
            batch.draw(dropImage, raindrop.x, raindrop.y);
        }

        for (Flower flower : flowers) {
            batch.draw(flowerImage, flower.getRectangle().x, flower.getRectangle().y);
        }

        yourBitmapFontName.draw(batch, yourScoreName, Gdx.graphics.getWidth() - 400, Gdx.graphics.getHeight() - 100);

        if (currentGameState == GameState.QUIZ) {
            renderQuiz(batch);
        }
        if (currentGameState == GameState.RIGHTANSWER) {
            rightAnswer.render();
        }

        batch.end();
    }

    private void renderQuiz(SpriteBatch batch) {
        menuAlphaBackground.begin(ShapeRenderer.ShapeType.Filled);
        menuAlphaBackground.setColor(new Color(0, 0, 0, 0.5f)); // last argument is alpha channel
        menuAlphaBackground.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        menuAlphaBackground.end();
        quizScreen.render();
    }

    @Override
    public void show() {
        rainMusic.play();
    }

    @Override
    public void resize(int width, int height) {
        this.scrollingBackground.resize(width, height);
        this.quizScreen.resize(width, height);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        dropImage.dispose();
        bucketImage.dispose();
        flowerImage.dispose();
        dropSound.dispose();
        rainMusic.dispose();
        quizScreen.dispose();
        menuAlphaBackground.dispose();
        rightAnswer.dispose();
        batch.dispose();
    }

    public void returnFromQuizScreen(GameState gameState) {
        this.currentGameState = gameState;
        if (flowers.size > 0) {
            flowers.get(0).setAnswered(true);
        }
    }

    public void showRightAnswer() {
        this.currentGameState = GameState.RIGHTANSWER;
    }

    public void backtobee(GameState state) {
        if (flowers.size > 0) {
            flowers.get(0).setAnswered(true);
        }

        if (state == GameState.RIGHTANSWER)

        {
            score += 10;
        }

        currentGameState = GameState.RUNNING;
    }

    enum GameState {
        RUNNING, QUIZ, RIGHTANSWER, WRONGANSWER
    }
}

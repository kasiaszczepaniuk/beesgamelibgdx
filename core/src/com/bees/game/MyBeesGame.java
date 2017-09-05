package com.bees.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class MyBeesGame extends ApplicationAdapter {

    public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;
    public SpriteBatch batch2;
    public ScrollingBackground scrollingBackground;
    Vector3 touchPos = new Vector3();
    // load the images for the droplet and the bucket, 64x64 pixels each
    private Texture dropImage;
    private Texture bucketImage;
    private Sound dropSound;
    private Music rainMusic;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Rectangle bucket;
    private Array<Rectangle> raindrops;
    private long lastDropTime;
    private int score;
    private String yourScoreName;
    BitmapFont yourBitmapFontName;

    @Override
    public void create() {
        dropImage = new Texture(Gdx.files.internal("honey.png"));
        bucketImage = new Texture(Gdx.files.internal("bee.png"));

        // load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        // start the playback of the background music immediately
        rainMusic.setLooping(true);
        rainMusic.play();

        batch = new SpriteBatch();
        batch2 = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);

        batch.setProjectionMatrix(camera.combined);
        batch2.setProjectionMatrix(camera.combined);

        this.scrollingBackground = new ScrollingBackground();


        bucket = new Rectangle();
        bucket.y = Gdx.graphics.getHeight() / 2 - 64 / 2;
        bucket.x = Gdx.graphics.getWidth() / 4 - 64 / 2;
        bucket.width = 64;
        bucket.height = 64;

        raindrops = new Array<Rectangle>();
        spawnRaindrop();

        score = 0;
        yourScoreName = "score: 0";
        yourBitmapFontName = new BitmapFont();
        yourBitmapFontName.getData().setScale(6);
//        textheightyourBitmapFontName.getRegion().getRegionHeight();
//        yourBitmapFontName.setS
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


    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();

        scrollingBackground.updateAndRender(Gdx.graphics.getDeltaTime(), batch);
        yourBitmapFontName.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        yourBitmapFontName.draw(batch, yourScoreName, Gdx.graphics.getWidth()-400, Gdx.graphics.getHeight()-100);

        batch.draw(bucketImage, bucket.x, bucket.y);
        for (Rectangle raindrop : raindrops) {
            batch.draw(dropImage, raindrop.x, raindrop.y);
        }

        batch.end();


        if (Gdx.input.isTouched()) {

            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.y = Interpolation.linear.apply(bucket.y, touchPos.y, 0.05f);
//            bucket.y = touchPos.y - 64 / 2;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();
        // make sure the bucket stays within the screen bounds
        if (bucket.y < 0) bucket.y = 0;
        if (bucket.y > HEIGHT - 64) bucket.y = HEIGHT - 64;

        // check if we need to create a new raindrop
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the later case we play back
        // a sound effect as well.
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

        // tell the camera to update its matrices.
        camera.update();
    }

    @Override
    public void dispose() {
        dropImage.dispose();
        bucketImage.dispose();
        dropSound.dispose();
        rainMusic.dispose();
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        this.scrollingBackground.resize(width, height);
        super.resize(width, height);
    }
}

package com.bees.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import database.Answer;
import database.Question;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MyBeesGame extends Game {

    public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;
    public SpriteBatch batch2;
    public SpriteBatch batch;


    @Override
    public void create() {

        initialDatabase();
        batch = new SpriteBatch();
        batch2 = new SpriteBatch();
        this.setScreen(new QuizScreen(this));
    }


    public void render() {
        super.render();

    }


    public void dispose() {

        batch.dispose();
    }

    public void resize(int width, int height) {

    }

    private void initialDatabase() {
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:sample.db";

            ConnectionSource connectionSource =
                    new JdbcConnectionSource(url);
            Dao<Question, String> questionDao = DaoManager.createDao(connectionSource, Question.class);
            Dao<Answer, String> answersDao = DaoManager.createDao(connectionSource, Answer.class);

            TableUtils.createTable(connectionSource, Question.class);
            TableUtils.createTable(connectionSource, Answer.class);

            Question question = new Question("Co to za zwirz?");
            Answer answer = new Answer("małpa", question);
            answersDao.create(answer);

            answer = new Answer("pies", question);
            answersDao.create(answer);

            answer = new Answer("kot", question);
            answersDao.create(answer);

            answer = new Answer("sowa", question);
            answersDao.create(answer);

            connectionSource.close();
        } catch (SQLException exception) {
            System.out.print(exception.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



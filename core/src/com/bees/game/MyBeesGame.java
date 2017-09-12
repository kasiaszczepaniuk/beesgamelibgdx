package com.bees.game;

import com.badlogic.gdx.Game;
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
    //    public SpriteBatch batch2;
//    public SpriteBatch batch;
    private Question question2;



    @Override
    public void create() {

        initialDatabase();
//        batch = new SpriteBatch();
//        batch2 = new SpriteBatch();
        this.setScreen(new MainMenuScreen(this));
    }


    public void render() {
        super.render();

    }


    public void dispose() {

//        batch.dispose();
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

            //QUESTION1 - RIGHT ANSWER - card
            Question question = new Question("What's that?");
            Answer answer = new Answer("cat", question);
            answersDao.create(answer);
            answer = new Answer("cow", question);
            answersDao.create(answer);
            answer = new Answer("card", question);
            answersDao.create(answer);
            answer = new Answer("notebook", question);
            answersDao.create(answer);

            //QUESTION2 - RIGHT ANSWER - bear
            Question question2 = new Question("What's that?");
            answer = new Answer("bear", question2);
            answersDao.create(answer);
            answer = new Answer("doll", question2);
            answersDao.create(answer);
            answer = new Answer("robot", question2);
            answersDao.create(answer);
            answer = new Answer("plane", question2);
            answersDao.create(answer);

            //QUESTION3 - RIGHT ANSWER - wagon
            Question question3 = new Question("What's that?");
            answer = new Answer("plane", question3);
            answersDao.create(answer);
            answer = new Answer("bus", question3);
            answersDao.create(answer);
            answer = new Answer("car", question3);
            answersDao.create(answer);
            answer = new Answer("wagon", question3);
            answersDao.create(answer);

            //QUESTION4 - RIGHT ANSWER - apple
            Question question4 = new Question("What's that?");
            answer = new Answer("pumpkin", question4);
            answersDao.create(answer);
            answer = new Answer("apple", question4);
            answersDao.create(answer);
            answer = new Answer("orange", question4);
            answersDao.create(answer);
            answer = new Answer("banana", question4);
            answersDao.create(answer);


            connectionSource.close();
        } catch (SQLException exception) {
            System.out.print(exception.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



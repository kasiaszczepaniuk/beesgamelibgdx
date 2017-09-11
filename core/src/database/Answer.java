package database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Answer {

    private static final String QUESTION_ID_FIELD_NAME = "question_id";
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String text;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = QUESTION_ID_FIELD_NAME)
    private Question question;


    public Answer() {
    }

    public Answer(String text, Question question) {
        this.text = text;
        this.question = question;
    }
}

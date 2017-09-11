package database;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Question {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String questionText;

    @ForeignCollectionField
    private ForeignCollection<Answer> answers;

    public Question() {
    }

    public Question(String questionText) {
        this.questionText = questionText;
    }


    //    public ForeignCollection<Answer> getAnswers() {
//        return answers;
//    }
//
//    public void setAnswers(ForeignCollection<Answer> answers) {
//        this.answers = answers;
//    }
}

package Models;

import org.bson.BsonObjectId;

/**
 * Created by Jason on 4/3/2016.
 */
public class Question {
    public BsonObjectId id;
    public int questionId;
    public String question;
    public String[] options;
    public int correctIndex;
    public int[] history;

    public void setQuestion (Question q)
    {
         int questionId = 0;
         String question;
         String[] options;
         int correctIndex;
         int[] history;
    }

    public Question(int id, String question, String[] answers, int correctIndex){
        this.questionId = id;
        this.question = question;
        this.options = answers;
        this.correctIndex = correctIndex;
    }
}


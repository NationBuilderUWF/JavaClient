package WebUtilities;

/**
 * Created by nacht_000 on 4/2/2016.
 */
public class Question {
    public int id;
    public String text;
    public String[] options;
    public int answer;
    public boolean correctlyAnswered;

    public Question(int id, String text, String[] options, int answer, boolean correctlyAnswered){
        this.id = id;
        this.text = text;
        this.options = options;
        this.answer = answer;
        this.correctlyAnswered = correctlyAnswered;
    }
}

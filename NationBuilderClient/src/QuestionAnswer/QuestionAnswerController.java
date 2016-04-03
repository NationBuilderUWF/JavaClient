package QuestionAnswer;

//import WebUtilities.AdminQuestionReq;

import MapRender.SelectData;
import Models.Question;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Random;

/**
 * Created by crims_000 on 4/1/2016.
 */


public class QuestionAnswerController {
    public Button questionSubmitButton;
    public TextArea studentQuestionForm;
    public TextField studentAnswerOneText;
    public TextField studentAnswerTwoText;
    public TextField studentAnswerThreeText;
    public TextField studentAnswerFourText;
    public RadioButton studentAnswerRButtonOne;
    public RadioButton studentAnswerRButtonTwo;
    public RadioButton studentAnswerRButtonThree;
    public RadioButton studentAnswerRButtonFour;
    public int correctQuestion;
    public Question currentQuestion;
    public boolean f = false;

    public static Question question1;
    public static Question question2;
    public static Question question3;

    public void initForm() {
        ToggleGroup questionGroup = new ToggleGroup();
        studentAnswerRButtonOne.setToggleGroup(questionGroup);
        studentAnswerRButtonOne.setSelected(true);

        studentAnswerRButtonTwo.setToggleGroup(questionGroup);
        studentAnswerRButtonThree.setToggleGroup(questionGroup);
        studentAnswerRButtonFour.setToggleGroup(questionGroup);

    }

    public void createQuestion(){


        String[] array = new String[4];
        array[0] = "24";
        array[1] = "16";
        array[2] = "-16";
        array[3] = "-24";

        question1 = new Question(0,"What is the result of when 20 is subtracted from -4?",array,3);

        array[0] = "Copper";
        array[1] = "Magnesium";
        array[2] = "Iron";
        array[3] = "Calcium";
        question2 = new Question(1, "Chlorophyll is a naturally occurring chelate compound in which central metal?",array, 1);

        array[0] = "NaAlO2";
        array[1] = "CaSiO3";
        array[2] = "Al2O3";
        array[3] = "H2O";
        question3 = new Question(2, "What is the chemical formula for water?",array, 3 );
    }

//    public void sub(ActionEvent actionEvent) {
//
//    }

    public Question pullQuestion() {

        createQuestion();

        Random random = new Random();
        int index = random.nextInt() % 3;
        if(index == 0){
            currentQuestion = question1;
        }else if (index == 1){
            currentQuestion = question2;
        }else
        {
            currentQuestion =  question3;
        }
        return currentQuestion;
        //System.out.println("Document contains: " + documents.size() + " document/s");\
    }

    public void displayQuestion(Question question) {
        studentQuestionForm.setText(question.question);
        studentAnswerOneText.setText(question.options[0]);
        studentAnswerTwoText.setText(question.options[1]);
        studentAnswerThreeText.setText(question.options[2]);
        studentAnswerFourText.setText(question.options[3]);

    }


    public void sub(ActionEvent actionEvent) {
        int answer;
        if(studentAnswerRButtonOne.isSelected() == true){
            answer = 0;
        }else if(studentAnswerRButtonTwo.isSelected() == true){
            answer = 1;
        }else if(studentAnswerRButtonThree.isSelected() == true){
            answer = 2;
        }else if(studentAnswerRButtonFour.isSelected() == true){
            answer =3;
        }else{
            System.out.println("Please Select Your Answer");
            return;
        }
        Button b1 = new Button("OK");

        if(answer == currentQuestion.correctIndex){
            Stage dialogStage = new Stage();
            b1.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    dialogStage.close();
                }
            });
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(VBoxBuilder.create().
                    children(new Text("You were correct! Resources are updated!"), b1).alignment(Pos.CENTER).padding(new Insets(5)).build()));
            dialogStage.show();
            SelectData.nation.setResources(500);
            f = true;
        }
        else{
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(VBoxBuilder.create().
                    children(new Text("You were incorrect. No resources this day."), b1).alignment(Pos.CENTER).padding(new Insets(5)).build()));
            dialogStage.show();
            f = true;
        }

    }
}

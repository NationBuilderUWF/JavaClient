package QuestionAnswer;

import WebUtilities.AnswerQuestionReq;
import WebUtilities.AnswerQuestionRes;
import Map.Nation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by crims_000 on 4/1/2016.
 */
public class QuestionAnswerController {

    public AnswerQuestionReq pullQuestion(AnswerQuestionReq bank[]){
        int id = (int)Math.random() * bank.length;
        AnswerQuestionReq query = bank[id];
        AnswerQuestionRes questionResponse;
        try {
            Socket clientSocket = new Socket("169.254.10.178", 3000);
            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
            outToServer.writeObject(query);
            questionResponse = (AnswerQuestionRes) inFromServer.readObject();
            clientSocket.close();
            return query;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void answerQuestion(AnswerQuestionReq question, Nation myNation){
        if(question.question.correctlyAnswered){
            AnswerQuestionRes response;
            try {
                Socket clientSocket = new Socket("169.254.10.178", 3000);
                ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
                outToServer.writeObject(myNation);
                response = (AnswerQuestionRes)inFromServer.readObject();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                Socket clientSocket = new Socket("169.254.10.178", 3000);
                ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
                clientSocket.close();
                //outToServer.writeObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
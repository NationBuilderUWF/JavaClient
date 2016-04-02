package Login;

import WebUtilities.LoginReq;
import WebUtilities.LoginRes;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LoginController {

    public Button loginButton;
    public TextField userNameField;
    public TextField passwordTextField;

    public void loginCheck(ActionEvent actionEvent) {
        LoginReq login = new LoginReq();
        LoginRes loginResponse;

        login.username = userNameField.getText();
        login.password = passwordTextField.getText();

        try{
            Socket clientSocket = new Socket("169.254.10.178", 3000);
            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
            outToServer.writeObject(login);

            loginResponse = (LoginRes) inFromServer.readObject();

            System.out.println(loginResponse.success);
            clientSocket.close();
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}

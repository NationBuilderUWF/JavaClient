package Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class LoginController {

    private static final InetAddress HOST = ;
    private static final int PORT = 3000;
    public Button loginButton;
    public TextField userNameField;
    public TextField passwordTextField;
    public void loginCheck(ActionEvent actionEvent) {
        LoginReq login = new LoginReq();

        login.username = userNameField.getText();
        login.password = passwordTextField.getText();

        try{
            Socket clientSocket = new Socket(HOST, PORT);
            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());

            outToServer.writeObject(login);
        }catch(Exception e) {

        }
    }
}

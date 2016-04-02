package Login;

import StudentInterface.StudentInterfaceController;
import WebUtilities.LoginReq;
import WebUtilities.LoginRes;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LoginController {

    public AnchorPane rootPane;
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
            System.out.println(loginResponse.admin);

            if(loginResponse.success == true && loginResponse.admin == true){

            }else if(loginResponse.success == true && loginResponse.admin == false){
                Stage stage = (Stage) rootPane.getScene().getWindow();
                stage.close();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("StudentInterface/StudentInterface.fxml"));
                Parent root = loader.load();
                stage.setTitle("[Enter Game Name Here] Student Console");
                stage.setScene(new Scene(root, 800, 800));
                stage.show();
                StudentInterfaceController controller = loader.getController();
                controller.loadInterface();
                System.out.println("Here");
            }
            clientSocket.close();
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}

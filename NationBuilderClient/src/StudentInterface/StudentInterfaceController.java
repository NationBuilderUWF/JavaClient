package StudentInterface;

import MapRender.MapRenderController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.SubScene;

import java.io.IOException;

/**
 * Created by crims_000 on 4/1/2016.
 */
public class StudentInterfaceController {

    SubScene mapScene;

    public void questionAction(ActionEvent actionEvent) {
    }

    public void buyTileFunction(ActionEvent actionEvent) {
    }

    public void battleFunction(ActionEvent actionEvent) {
    }

    public void voidFunction(ActionEvent actionEvent) {
    }

    public void loadInterface() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MapRender.fxml"));
        mapScene = new SubScene(root,MapRenderController.width, MapRenderController.height);
    }
}

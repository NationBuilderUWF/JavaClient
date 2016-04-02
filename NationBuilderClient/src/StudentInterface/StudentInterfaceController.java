package StudentInterface;

import MapRender.MapRenderController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by crims_000 on 4/1/2016.
 */
public class StudentInterfaceController {

    public AnchorPane mapPane;

    public void questionAction(ActionEvent actionEvent) {
    }

    public void buyTileFunction(ActionEvent actionEvent) {
    }

    public void battleFunction(ActionEvent actionEvent) {
    }

    public void voidFunction(ActionEvent actionEvent) {
    }

    public void loadInterface() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MapRender/MapRender.fxml"));
        mapPane.getChildren().clear();
        mapPane.getChildren().add(loader.load());
    }
}

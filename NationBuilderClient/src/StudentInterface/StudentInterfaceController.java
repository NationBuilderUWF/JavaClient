package StudentInterface;

import Map.Nation;
import Map.Tile.Tile;
import MapRender.MapRenderController;
import MapRender.SelectData;
import QuestionAnswer.QuestionAnswerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by crims_000 on 4/1/2016.
 */
public class StudentInterfaceController {

    public AnchorPane mapPane;


    public void questionAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("QuestionAnswer/QuestionAnswer.fxml"));
        AnchorPane pane = new AnchorPane();
        pane.getChildren().clear();
        pane.getChildren().add(loader.load());

        QuestionAnswerController controller = loader.getController();
        controller.initForm();
        controller.displayQuestion(controller.pullQuestion());

        Stage stage = new Stage();
        stage.setScene(new Scene(pane,426,490));
        stage.setResizable(false);
        stage.setTitle("Question Answer Form");
        stage.show();

    }

    public void buyTileFunction(ActionEvent actionEvent) {
        Tile tile = SelectData.map.getTile((int)SelectData.col, (int)SelectData.row);
        tile.setOwner(SelectData.nation);
        System.out.println("Buy Tile");

    }

    public void battleFunction(ActionEvent actionEvent) {
    }

    public void loadInterface() throws IOException {
        SelectData.nation = new Nation(1,60,false);
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MapRender/MapRender.fxml"));
        mapPane.getChildren().clear();
        mapPane.getChildren().add(loader.load());
        MapRenderController controller = loader.getController();
        controller.loadInterface();
    }
}

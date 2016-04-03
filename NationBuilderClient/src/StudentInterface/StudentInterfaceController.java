package StudentInterface;

import Map.Tile.Tile;
import MapRender.MapRenderController;
import MapRender.SelectData;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
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
        Tile tile = SelectData.map.getTile((int)SelectData.col, (int)SelectData.row);
        tile.buyTile(SelectData.map,SelectData.nation,1);

    }

    public void battleFunction(ActionEvent actionEvent) {
    }

    public void loadInterface() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MapRender/MapRender.fxml"));
        mapPane.getChildren().clear();
        mapPane.getChildren().add(loader.load());
        MapRenderController controller = loader.getController();
        controller.loadInterface();
    }
}

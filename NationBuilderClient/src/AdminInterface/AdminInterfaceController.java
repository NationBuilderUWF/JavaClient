package AdminInterface;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by crims_000 on 4/1/2016.
 */
public class AdminInterfaceController {

    private class TableData{
        public SimpleStringProperty team;
        public SimpleIntegerProperty resourceAmount;
        public SimpleIntegerProperty tileAmount;

        public TableData(String team, int resourceAmount, int tileAmount){
            this.team = new SimpleStringProperty(team);
            this.resourceAmount = new SimpleIntegerProperty(resourceAmount);
            this.tileAmount = new SimpleIntegerProperty(tileAmount);
        }
    }
    public TableView<TableData> dataTable;
    public Button mapButton;
    public TableColumn<TableData, String> teamCol;
    public TableColumn<TableData, Integer> resCol;
    public TableColumn<TableData, Integer> tileCol;

    public void openMap(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MapRender/MapRender.fxml"));
        AnchorPane root = new AnchorPane();
        root.getChildren().clear();
        root.getChildren().add(loader.load());

        Stage stage = new Stage();
        stage.setTitle("Map Viewer");
        stage.setScene(new Scene(root,500,500));
        stage.show();
    }

    public void loadInterface(){
        dataTable.setEditable(Boolean.FALSE);
        teamCol.setCellValueFactory(new PropertyValueFactory<>("team"));
        resCol.setCellValueFactory(new PropertyValueFactory<>("resourceAmount"));
        tileCol.setCellValueFactory(new PropertyValueFactory<>("tileAmount"));
        //Temp code will get from data base later
        ObservableList<TableData> data = FXCollections.observableArrayList(new TableData("Team A",0,0),new TableData("Team B",0,0),new TableData("Team C",0,0),new TableData("Team D",0,0));
        dataTable.setItems(data);
    }
}

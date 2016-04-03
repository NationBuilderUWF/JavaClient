package AdminInterface;

import AddUser.AddUserController;
import MapRender.MapRenderController;
import QuestionForm.QuestionFormController;
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

    public class TableData{
        public SimpleStringProperty team;
        public SimpleIntegerProperty resourceAmount;
        public SimpleIntegerProperty tileAmount;

        public TableData(String team, int resourceAmount, int tileAmount){
            this.team = new SimpleStringProperty(team);
            this.resourceAmount = new SimpleIntegerProperty(resourceAmount);
            this.tileAmount = new SimpleIntegerProperty(tileAmount);
        }

        public String getTeam(){
            return team.get();
        }

        public int getResourceAmount(){
            return resourceAmount.get();
        }

        public int getTileAmount(){
            return tileAmount.get();
        }

    }
    public Button mapButton;
    public TableView<TableData> table;
    public Button questionButton;

    public void addUser(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AddUser/AddUser.fxml"));
        AnchorPane root = new AnchorPane();
        root.getChildren().clear();
        root.getChildren().add(loader.load());

        AddUserController controller =  loader.getController();
        controller.loadInterface();

        Stage stage = new Stage();
        stage.setScene(new Scene(root,403,192));
        stage.setResizable(false);
        stage.setTitle("User Creation Form");
        stage.show();
    }


    public void createQuestion(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("QuestionForm/QuestionForm.fxml"));
        AnchorPane root = new AnchorPane();
        root.getChildren().clear();
        root.getChildren().add(loader.load());

        QuestionFormController controller = loader.getController();
        controller.initForm();

        Stage stage = new Stage();
        stage.setScene(new Scene(root,426,490));
        stage.setResizable(false);
        stage.setTitle("Question Answer Form");
        stage.show();
    }

    public void openMap(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MapRender/MapRender.fxml"));
        AnchorPane root = new AnchorPane();
        root.getChildren().clear();
        root.getChildren().add(loader.load());

        Stage stage = new Stage();
        stage.setTitle("Map Viewer");
        stage.setScene(new Scene(root, MapRenderController.width,MapRenderController.height));
        stage.setResizable(false);
        stage.show();
    }

    public void loadInterface(){
        //Temp code will get from data base later
        ObservableList<TableData> data = FXCollections.observableArrayList(new TableData("Team A",0,0),new TableData("Team B",0,0),new TableData("Team C",0,0),new TableData("Team D",0,0));
        table.setEditable(false);

        TableColumn team = new TableColumn("Team");
        team.setCellValueFactory(new PropertyValueFactory<TableData, String>("team"));
        team.setResizable(false);
        team.prefWidthProperty().bind(table.widthProperty().divide(3));

        TableColumn res = new TableColumn("Resources");
        res.setCellValueFactory(new PropertyValueFactory<TableData, Integer>("resourceAmount"));
        res.setResizable(false);
        res.prefWidthProperty().bind(table.widthProperty().divide(3));

        TableColumn tile = new TableColumn("Tile Amount");
        tile.setCellValueFactory(new PropertyValueFactory<TableData, Integer>("tileAmount"));
        tile.setResizable(false);
        tile.prefWidthProperty().bind(table.widthProperty().divide(3));

        table.setItems(data);
        table.getColumns().addAll(team, res, tile);

    }
}

package MapRender;

import Map.Map;
import AdminInterface.AdminInterfaceController;
import Map.Nation;
import Map.Tile.Tile;
import StudentInterface.StudentInterfaceController;
import WebUtilities.GetMapReq;
import WebUtilities.GetMapRes;
import WebUtilities.LoginRes;
import WebUtilities.SetMapReq;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.w3c.dom.css.Rect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by crims_000 on 4/2/2016.
 */
public class MapRenderController {
    private static int amountOfTiles = 132;
    private static int circleLocation = amountOfTiles + 2;
    public static double height = 576;
    public static double width = 528;
    private static boolean hasMadeCircle = Boolean.FALSE;

    public AnchorPane mapPane;

    //Create selection dot to show what is currently selected
    Circle selectionDot;


    public void getCoords(Event event) {
        Rectangle source = (Rectangle) event.getSource();
        double x = source.getLayoutX();
        double y = source.getLayoutY();

        try{
            mapPane.getChildren().remove(circleLocation);
        } catch(java.lang.IndexOutOfBoundsException e){
            System.err.println("Another One");
        }
        selectionDot = new Circle(x + 24,y + 24,10, Color.BLACK);
        mapPane.getChildren().add(circleLocation,selectionDot);
        hasMadeCircle = Boolean.TRUE;

        x = x/48;
        y = y/48;

        SelectData.col = x;
        SelectData.row = y;

    }
    public void loadInterface(){
        SelectData.map = new Map();
        try{
            SelectData.map.repopulateMap();
            SetMapReq map = new SetMapReq();
            map.SetMapReq(SelectData.map);
            Socket clientSocket = new Socket("169.254.10.178", 3000);
            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());

            outToServer.writeObject(map);

        }catch(Exception e){
            System.out.println(e);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(3000);
                        System.out.println("New one sent");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try{
                        GetMapReq map = new GetMapReq();
                        GetMapRes mapResponse;
                        Socket clientSocket = new Socket("169.254.10.178", 3000);
                        ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
                        ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
                        outToServer.writeObject(map);

                        mapResponse = (GetMapRes) inFromServer.readObject();
                        SelectData.map = mapResponse.decode();
                        loadMap(SelectData.map.tiles);
                        clientSocket.close();
                    }catch(Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }).start();
    }
    public void loadMap(Tile array[][]){
        Color darkRed = Color.web("#9f1b14");
        Color red = Color.web("#d2231a");
        Color darkBlue = Color.web("#0000b3");
        Color blue = Color.web("#0000ff");
        Color darkGreen = Color.web("#509e15");
        Color green = Color.web("#6ad11b");
        Color darkOrgange = Color.web("#cc5800");
        Color orange = Color.web("#ff6f00");
        Color grey = Color.web("#d4d3d3");
        for(int x = 0; x < 11; x++){
            for(int y = 0; y < 12; y++){
                if(array[x][y].isDarkFlag()){
                    //is Dark
                    Nation owner = array[x][y].getOwner();
                    if(owner.getID() == 0){
                        Rectangle rect = (Rectangle) mapPane.getChildren().get((x*11) + y);
                        rect.fillProperty().setValue(grey);
                    }else if(owner.getID() == 1){
                        Rectangle rect = (Rectangle) mapPane.getChildren().get(x*11 + y);
                        rect.fillProperty().setValue(darkRed);
                    }else if(owner.getID() == 2){
                        Rectangle rect = (Rectangle) mapPane.getChildren().get(x*11 + y);
                        rect.fillProperty().setValue(darkBlue);
                    }else if(owner.getID() == 3){
                        Rectangle rect = (Rectangle) mapPane.getChildren().get(x*11 + y);
                        rect.fillProperty().setValue(darkGreen);
                    }else{
                        Rectangle rect = (Rectangle) mapPane.getChildren().get(x*11 + y);
                        rect.fillProperty().setValue(darkOrgange);
                    }
                }else{
                    //not dark
                    Nation owner = array[x][y].getOwner();
                    if(owner.getID() == 0){
                        Rectangle rect = (Rectangle) mapPane.getChildren().get((x*11) + y);
                        rect.fillProperty().setValue(Color.WHITE);
                    }else if(owner.getID() == 1){
                        Rectangle rect = (Rectangle) mapPane.getChildren().get(x*11 + y);
                        rect.fillProperty().setValue(red);
                    }else if(owner.getID() == 2){
                        Rectangle rect = (Rectangle) mapPane.getChildren().get(x*11 + y);
                        rect.fillProperty().setValue(blue);
                    }else if(owner.getID() == 3){
                        Rectangle rect = (Rectangle) mapPane.getChildren().get(x*11 + y);
                        rect.fillProperty().setValue(green);
                    }else{
                        Rectangle rect = (Rectangle) mapPane.getChildren().get(x*11 + y);
                        rect.fillProperty().setValue(orange);
                    }
                }
            }
        }
    }

}

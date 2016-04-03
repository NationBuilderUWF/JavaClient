package MapRender;

import Map.Map;
import Map.Nation;
import Map.Tile.Tile;
import WebUtilities.GetMapReq;
import WebUtilities.GetMapRes;
import WebUtilities.SetMapReq;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by crims_000 on 4/2/2016.
 */
public class MapRenderController {
    private static int amountOfTiles = 132;
    private static int circleLocation = amountOfTiles;
    public static double height = 576;
    public static double width = 528;
    private static boolean hasMadeCircle = Boolean.FALSE;

    public AnchorPane mapPane;

    //Create selection dot to show what is currently selected
    Circle selectionDot;


    public void getCoords(Event event) {
        Rectangle source = (Rectangle) event.getSource();
        double x = source.getX();
        double y = source.getY();

        try {
            mapPane.getChildren().remove(circleLocation);
        } catch (java.lang.IndexOutOfBoundsException e) {
            System.err.println("Another One");
        }
        selectionDot = new Circle(x + 24, y + 24, 10, Color.BLACK);
        mapPane.getChildren().add(circleLocation, selectionDot);
        hasMadeCircle = Boolean.TRUE;

        x = x / 48;
        y = y / 48;

        SelectData.col = x;
        SelectData.row = y;

        System.out.println(SelectData.map.getTile((int) SelectData.col, (int) SelectData.row).isDarkFlag());
        System.out.println(SelectData.map.getTile((int) SelectData.col, (int) SelectData.row).getOwner().getID());
        System.out.println(SelectData.map.getTile((int) SelectData.col, (int) SelectData.row).isDefendFlag());
    }


    public void loadInterface() {
        for(int x = 0; x < 11; x ++){
            for(int y = 0; y < 12; y++){
                Rectangle rect = new Rectangle(x*48.0,y*48.0,48,48);
                rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                            if(mouseEvent.getClickCount() == 1){
                                getCoords(mouseEvent);
                            }
                        }
                    }
                });
                mapPane.getChildren().add(rect);
            }
        }
        // SelectData.map = new Map();

//        try{
//            SelectData.map.repopulateMap();
//            SetMapReq map = new SetMapReq();
//            Socket clientSocket = new Socket("127.0.0.1", 3000);
//            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
//
//            outToServer.writeObject(map);
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }

        new Thread(new Runnable() {

            @Override
            public void run() {
                Map newMap = new Map();
                SetMapReq map = new SetMapReq(newMap);


//                for(Tile[] t : newMap.tiles){
//                    for(Tile t1: t){
//                        map.maps.add(t1);
//
//                    }
//                }
                map.banks = new ArrayList<Integer>();
                for (int i = 0; i < 4; i++) {
                    map.banks.add((int) (Math.random() * 100));
                }
                GetMapRes mapResponse;
                try {
                    Socket clientSocket = new Socket("localhost", 3000);
                    ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
                    ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
                    outToServer.writeObject(map);

                    // mapResponse = (GetMapRes) inFromServer.readObject();
                    // SelectData.map = mapResponse.decode();
                    // loadMap(SelectData.map.tiles);
                    clientSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                while(true) {
                    try {

                        System.out.println("Looking for new map");
                        Socket clientSocket = new Socket("localhost", 3000);
                        GetMapReq gmr = new GetMapReq();
                        ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
                        ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
                        outToServer.writeObject(gmr);
                        Object o = inFromServer.readObject();
                        GetMapRes GMR = (GetMapRes) o;
                        System.out.println("loading Map" + GMR.maps);
                        SelectData.map = GMR.decode();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                loadMap(map.maps);
                            }
                        });

                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    public void loadMap(ArrayList<Tile> tiles) {
        Color darkRed = Color.web("#9f1b14");
        Color red = Color.web("#d2231a");
        Color darkBlue = Color.web("#0000b3");
        Color blue = Color.web("#0000ff");
        Color darkGreen = Color.web("#509e15");
        Color green = Color.web("#6ad11b");
        Color darkOrange = Color.web("#cc5800");
        Color orange = Color.web("#ff6f00");
        Color grey = Color.web("#d4d3d3");

//        int value = -1;
//        //for(int y = 0; y < 11; y++){
//           // for(int x = 0; x < 12; x++){
//                //int value = (x*12 + y);
//        for (Tile T:tiles) {
//            System.out.println("Drawing!!");
//            value++;
//            tiles.get(value);
//
//            Rectangle rect = (Rectangle) mapPane.getChildren().get(value);
//            rect.fillProperty().setValue(Color.WHITE);
//
//            if(T.isDarkFlag()){
//                rect = (Rectangle) mapPane.getChildren().get(value);
//                rect.fillProperty().setValue(grey);
//            }else {
//                rect.setFill(Color.PURPLE);
//            }
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
////                if(T.isDarkFlag()){
        int value = -1;
        for (int x = 0; x < 11; x++) {
            for (int y = 0; y < 12; y++) {
                value++;
                if (tiles.get(value).isDarkFlag() == true) {
                    //is Dark
                    Nation owner = tiles.get(value).getOwner();
                    if (owner.getID() == 0) {
                        Rectangle rect = (Rectangle) mapPane.getChildren().get(value);
                        rect.fillProperty().setValue(grey);
                    } else if (owner.getID() == 1) {
                        Rectangle rect = (Rectangle) mapPane.getChildren().get(value);
                        rect.fillProperty().setValue(darkRed);
                        rect.setFill(darkRed);
                    } else if (owner.getID() == 2) {
                        Rectangle rect = (Rectangle) mapPane.getChildren().get(value);
                        rect.fillProperty().setValue(darkBlue);
                    } else if (owner.getID() == 3) {
                        Rectangle rect = (Rectangle) mapPane.getChildren().get(value);
                        rect.fillProperty().setValue(darkGreen);
                    } else if (owner.getID() == 4) {
                        Rectangle rect = (Rectangle) mapPane.getChildren().get(value);
                        rect.fillProperty().setValue(darkOrange);
                    }
                } else {
                    //not dark
                    Nation owner = tiles.get(value).getOwner();
                    if (owner.getID() == 0) {
                        Rectangle rect = (Rectangle) mapPane.getChildren().get(value);
                        rect.fillProperty().setValue(Color.WHITE);
                    } else if (owner.getID() == 1) {
                        Rectangle rect = (Rectangle) mapPane.getChildren().get(value);
                        rect.fillProperty().setValue(red);
                    } else if (owner.getID() == 2) {
                        Rectangle rect = (Rectangle) mapPane.getChildren().get(value);
                        rect.fillProperty().setValue(blue);
                    } else if (owner.getID() == 3) {
                        Rectangle rect = (Rectangle) mapPane.getChildren().get(value);
                        rect.fillProperty().setValue(green);
                    } else if (owner.getID() == 4) {
                        Rectangle rect = (Rectangle) mapPane.getChildren().get(value);
                        rect.fillProperty().setValue(orange);
                    }
                }
            }
        }
    }
}




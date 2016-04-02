package MapRender;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.event.Event;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

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

        if(mapPane.getChildren().get(circleLocation) != null) {
            mapPane.getChildren().remove(circleLocation);
        }
        selectionDot = new Circle(x + 24,y + 24,10, Color.BLACK);
        mapPane.getChildren().add(circleLocation,selectionDot);
        hasMadeCircle = Boolean.TRUE;

        System.out.println("X: " + x + " Y " + y);
        x = x/48;
        y = y/48;

        SelectData.col = x;
        SelectData.row = y;

        System.out.println("Column: " + SelectData.col + " Row: " + SelectData.row);
    }

    public void loadMap(int array[][]){
        for(int x = 0; x < 11; x++){
            for(int y = 0; x < 12; x++){
                if(array[x][y] == 1) {
                    int temp = (x * 11) + y;
                    Rectangle rect = (Rectangle) mapPane.getChildren().get(temp);
                    rect.fillProperty().setValue(Color.web("#d2231a"));
                }else if(array[x][y] == 2){
                    int temp = (x * 11) + y;
                    Rectangle rect = (Rectangle) mapPane.getChildren().get(temp);
                    rect.fillProperty().setValue(Color.DODGERBLUE);
                }else if(array[x][y] == 3){
                    int temp = (x * 11) + y;
                    Rectangle rect = (Rectangle) mapPane.getChildren().get(temp);
                    rect.fillProperty().setValue(Color.web("#6ad11b"));
                }else if(array[x][y] == 4){
                    int temp = (x * 11) + y;
                    Rectangle rect = (Rectangle) mapPane.getChildren().get(temp);
                    rect.fillProperty().setValue(Color.web("#ffa35c"));
                }
            }
        }

    }
}

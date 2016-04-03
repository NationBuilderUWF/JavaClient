package WebUtilities;

import Map.Map;
import Map.Tile.Tile;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by poebr_000 on 4/2/2016.
 */
public class GetMapRes implements Serializable {
    public static final long serialVersionUID = 1L;
    ArrayList<Tile> maps;
    ArrayList<Integer> banks;

    public Map decode(){
        Map temp = new Map();
        for(int x = 0; x < 11; x++){
            for(int y = 0; y < 12; y++){
                temp.tiles[x][y] = maps.get((x*11 + y));
            }
        }
        return temp;
    }
}

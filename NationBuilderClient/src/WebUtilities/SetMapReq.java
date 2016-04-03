package WebUtilities;

import Map.Map;
import Map.Tile.Tile;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by poebr_000 on 4/2/2016.
 */
public class SetMapReq implements Serializable{
    public static final long serialVersionUID = 1L;

    public ArrayList<Tile> maps;
    public ArrayList<Integer> banks;

    public void SetMapReq(Map map){
        for(int x = 0; x < 11; x++){
            for(int y = 0; y < 12; y++){
                maps.add(map.getTile(x,y));
            }
        }
    }
}

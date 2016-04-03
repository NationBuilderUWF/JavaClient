package MapRender;

import Map.Map;

/**
 * Created by poebr_000 on 4/2/2016.
 */
public class SendOps {
    public static void initSend(Send data, Map map){
        for(int x = 0; x < 11; x++){
            for(int y = 0; y < 12; y++){
                data.attacking[x*11 + y] = map.tiles[x][y].getAttacker().getID();
                data.defend[x*11 + y] = map.tiles[x][y].isDefendFlag();
                data.isDark[x*11 + y] = map.tiles[x][y].isDarkFlag();
                data.own[x*11 + y] = map.tiles[x][y].getOwner().getID();
            }
        }
    }

    public static void reinitMap(Map map, Send data){
        for(int x = 0; x < 11; x++){
            for(int y = 0; y < 12; y++){
                map.tiles[x][y].getAttacker().setID(data.attacking[x*12 + y]);
                map.tiles[x][y].setDefendFlag(data.defend[x*12 + y]);
                map.tiles[x][y].setDarkFlag(data.isDark[x*12 + y]);
                map.tiles[x][y].getOwner().setID(data.own[x*12 + y]);

            }
        }
    }
}

package MapRender;

import Map.Map;
import Map.Nation;

/**
 * Created by crims_000 on 4/2/2016.
 */
public class SelectData {
    public static double row;
    public static double col;
    public static Map map;
    public static int nationID;
    public static int nationResources;
    public static Nation nation;

    public static void initNation(){
        nation = new Nation(nationID, nationResources, false);
    }

}

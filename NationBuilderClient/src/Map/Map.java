package Map;
import Map.Tile.Tile;
import Map.Nation;

public class Map{
    private Tile[][] tiles;

    public Map(){
        this.tiles = new Tile[11][12];
    }

    public Tile getTile(int x, int y){
        return tiles[x][y];
    }

    public int[][] returnOwnership(Map map){
        int[][] ownership = new int[11][12];
        for(int i=0; i<=11; i++){
            for(int j=0; i<12; j++){
                Tile t = getTile(i,j);
                Nation owner = t.Map.getOwner();
                int id = owner.getID();
                ownership[i][j] = id;
            }
        }
    }
}

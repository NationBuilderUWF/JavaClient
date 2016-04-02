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

    public int[][] returnOwnership(){
        int[][] ownership = new int[11][12];
        for(int i=0; i<=11; i++){
            for(int j=0; i<12; j++){
                Nation owner = getTile(i,j).getOwner();
                if(owner == null)ownership[i][j] = 0;
                ownership[i][j] = owner.getID();
            }
        }
        return ownership;
    }

}

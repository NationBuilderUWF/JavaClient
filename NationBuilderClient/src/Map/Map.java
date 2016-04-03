package Map;
import Map.Tile.Tile;
import Map.Nation;

import java.io.Serializable;

public class Map implements Serializable{

    public Tile[][] tiles;
    public String _id;
    Nation red;
    Nation blue;
    Nation green;
    Nation orange;
    public Map(){
        this.tiles = new Tile[11][12];
        System.out.println("HEr");
        repopulateMap();
    }

    public void repopulateMap(){
        populateDarkZone();
        populateHomeTiles();
        addBoundaries();
    }

    public void populateDarkZone(){
        for(int i=2; i<=8; i++){
            for(int j=0; j<12; j++){
                if(i>=4 && i<=6){
                    getTile(i,j).setDarkFlag(true);
                }
                else if(j>=2 && j<=9){
                    getTile(i,j).setDarkFlag(true);
                }
            }
        }
    }

    public void populateHomeTiles(){
        this.red = new Nation(1,0,false);
        this.blue = new Nation(1,0,false);
        this.green = new Nation(1,0,false);
        this.orange = new Nation(1,0,false);
        getTile(0,0).setOwner(red);
        getTile(0,12).setOwner(blue);
        getTile(11,0).setOwner(green);
        getTile(11,12).setOwner(orange);

    }

    public void addBoundaries(){
        //maybe finish if time allows
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

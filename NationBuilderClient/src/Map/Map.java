package Map;
import Map.Tile.Tile;

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
        for(int x = 0; x < 11; x++){
            for(int y = 0; y < 12; y++){
                this.tiles[x][y] = new Tile();
            }
        }
        System.out.println("Made new Map");
        repopulateMap();
    }

    public void repopulateMap(){
        populateDarkZone();
        populateHomeTiles();
        addBoundaries();
    }

    public void populateDarkZone(){
       for(int x = 2; x < 9; x++ ){
           for(int y = 2; y < 10; y++){
               this.getTile(x,y).setDarkFlag(true);
           }
       }
        for(int x = 4; x < 7; x++){
            for(int y = 0; y < 2; y++){
                this.getTile(x,y).setDarkFlag(true);
            }
        }
        for(int x = 4; x < 7; x++){
            for(int y = 10; y < 12; y++){
                this.getTile(x,y).setDarkFlag(true);
            }
        }
    }

    public void populateHomeTiles(){
        this.red = new Nation(1,0,false);
        this.blue = new Nation(2,0,false);
        this.green = new Nation(3,0,false);
        this.orange = new Nation(4,0,false);
        getTile(0,0).setOwner(red);
        getTile(0,11).setOwner(blue);
        getTile(10,0).setOwner(green);
        getTile(10,11).setOwner(orange);

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

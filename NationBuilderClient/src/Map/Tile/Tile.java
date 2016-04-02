package Map.Tile;
import Map.Map;
import Map.Nation;
import MapRender.SelectData;

public class Tile {

    private Map map;
    private Nation owner; //nation which owns tile
    private boolean darkFlag; //flag for dark zone tiles, which allow pvp
    private boolean defendFlag; //flag for defending against attack
    private Nation attacker; //nation attacking tile

    public Nation getOwner() {
        return owner;
    }

    public void setOwner(Nation owner) {
        this.owner = owner;
    }

    public boolean isDarkFlag() {
        return darkFlag;
    }

    public void setDarkFlag(boolean darkFlag) {
        this.darkFlag = darkFlag;
    }

    public boolean isDefendFlag() {
        return defendFlag;
    }

    public void setDefendFlag(boolean defendFlag) {
        this.defendFlag = defendFlag;
    }

    public Nation getAttacker() {
        return attacker;
    }

    public void setAttacker(Nation attacker) {
        this.attacker = attacker;
    }

    public void declareBattle(Nation attacker, int cost){
        Tile defender = map.getTile((int)SelectData.row, (int)SelectData.col);
        if(!defender.isDarkFlag()){ //if tile isn't a dark zone tile
            //displayError("notDarkZoneTile"); //placeholder for actual error displaying
            return;
        }
        if(defender.isDefendFlag()){ //if time is already in a battle
            //displayError("tileAlreadyInBattle");
            return;
        }
        if(attacker.getResources() > cost){ //if nation can afford battle
            attacker.setResources(attacker.getResources() - cost); //remove resources for cost
            defender.setAttacker(attacker);
            defender.setDefendFlag(true);
            //server.pushNationQuestions(attacker, 1); //for each student in nation push 1 question
            //server.pushNationQuestions(defender.getOwner());
        }
        else{
            //displayError("notEnoughResources");
            return;
        }
    }

    public void resolveBattle(Tile defender, boolean defenderWin){ //server might handle this?
        if(!defenderWin){
            defender.setOwner(defender.getAttacker());
        }
        defender.setAttacker(null);
        defender.setDefendFlag(false);
    }

    public void buyTile(Nation buyer, int cost){
        Tile product = map.getTile((int)SelectData.row,(int)SelectData.col); //retrieve desired tile
        if(product.getOwner() != null){
            //displayError("tileAlreadyOwned");
        }

        if(buyer.getResources() > cost){ //if nation can afford tile
            buyer.setResources(buyer.getResources() - cost); //remove resources for cost
            product.setOwner(buyer); //buyer now owns tile
        }
        else{
            //displayError("notEnoughResources");
            return;
        }
    }

    public Tile(Map map, Nation owner, boolean darkFlag, boolean defendFlag, Nation attacker){
        this.map = map;
        this.owner = null;
        this.darkFlag = false;
        this.defendFlag = false;
        this.attacker = null;
    }

}

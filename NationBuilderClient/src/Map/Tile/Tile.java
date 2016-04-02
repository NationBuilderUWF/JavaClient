package Map.Tile;

public class Tile {
    public Nation owner;
    private boolean darkFlag;
    private boolean defendFlag;
    private Nation attacker;

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

    public void declareBattle(Nation attacker, Tile defender){
        defender.setAttacker(attacker);
        server.pushQuestions();
    }

    public void resolveBattle(Nation attacker, Tile)

    public Tile(Nation owner, boolean darkFlag, boolean defendFlag, Nation attacker){
        this.owner = null;
        this.darkFlag = false;
        this.defendFlag = false;
        this.attacker = null;
    }
}

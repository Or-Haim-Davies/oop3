package game.tiles;

public abstract class Unit extends Tile{

    protected String name;
    protected int healthPool;
    protected int healthAmount;
    protected int attackPoints;
    protected int defensePoints;

    public Unit(char tileChar,Position position,  String name,int healthPool,int healthAmount,
                int attackPoints,int defensePoints){
        super(tileChar,position);
        this.name=name;
        this.healthAmount=healthAmount;
        this.healthPool=healthPool;
        this.attackPoints=attackPoints;
        this.defensePoints=defensePoints;
    }


    public boolean isAlive() {
        return healthAmount > 0;
    }

    public void receiveDamage(int damage) {
        this.healthAmount -= damage;
        if (this.healthAmount < 0) this.healthAmount = 0;
    }


    public String getName(){
        return this.name;}
    public int getHealthPool(){
        return this.healthPool;}
    public void setHealthPool(int healthPool){
        this.healthPool=healthPool;
    }
    public int getHealthAmount(){
        return this.healthAmount;
    }
    public void setHealthAmount(int healthAmount){
        this.healthAmount=healthAmount;
    }
    public int getAttackPoints(){
        return attackPoints;
    }
    public void setAttackPoints(int attackPoints){
        this.attackPoints=attackPoints;
    }
    public int getDefensePoints(){
        return this.defensePoints;
    }
    public void setDefensePoints(int defensePoints){
        this.defensePoints=defensePoints;
    }

}

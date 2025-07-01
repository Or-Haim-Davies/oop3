package game.tiles;

public class Player extends Unit {
    protected int experience;
    protected  int player_Level;


    public Player(Position position,String name,int healthPool,int healthAmount,int attackPoints,int defensePoints){
        super( '@',position,  name, healthPool, healthAmount, attackPoints, defensePoints);
        this.experience=0;
        this.player_Level=1;
    }


    public void gainExperience(int amount) {
        experience += amount;
        while (experience >= 50 * player_Level) {
            levelUp();
        }
    }

    void levelUp() {
        experience -= 50 * player_Level;
        player_Level++;
        healthPool += 10 * player_Level;
        healthAmount = healthPool;
        attackPoints += 4 * player_Level;
        defensePoints +=  player_Level;
        System.out.println(name + " leveled up to level " + player_Level + "!");
    }


    public void castAbility(int cost) {
        if (experience < cost) {
            System.out.println("Not enough resources to cast ability!");
        } else {
            experience -= cost;
            System.out.println(name + " casts special ability!");
        }
    }












    public int getExperience(){
        return this.experience;
    }
    public void setExperience(int experience){
        this.experience=experience;
    }
    public int getPlayer_Level(){
        return this.player_Level;
    }
    public void setPlayer_Level(int player_Level){
        this.player_Level=player_Level;
    }



}

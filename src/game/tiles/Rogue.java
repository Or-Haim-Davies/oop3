package game.tiles;

import java.util.List;

public class Rogue extends Player {
    private int cost;  // energy cost of special ability
    private int currentEnergy;

    public Rogue(Position position, String name,
                 int healthPool, int healthAmount,
                 int attack, int defense,
                 int cost) {
        super(position, name, healthPool, healthAmount, attack, defense);
        this.cost = cost;
        this.currentEnergy = 100;
    }

    @Override
    protected void levelUp() {
        super.levelUp(); // normal player upgrades
        currentEnergy = 100;
        attackPoints += 3 * player_Level;
        System.out.println(name + " sharpens blades: attack now " + attackPoints + ", energy reset to 100.");
    }

    public void onGameTick() {
        currentEnergy = Math.min(currentEnergy + 10, 100);
    }

    public void castAbility(List<Enemy> enemies) {
        if (currentEnergy < cost) {
            System.out.println("Not enough energy to cast Fan of Knives!");
            return;
        }

        currentEnergy -= cost;

        boolean hitAnyone = false;
        for (Enemy e : enemies) {
            if (e.isAlive() && getPosition().distance(e.getPosition()) < 2) {
                int damage = Math.max(0, attackPoints - e.getDefensePoints());
                e.receiveDamage(damage);
                System.out.println(name + " hits " + e.getName() + " with Fan of Knives for " + damage);

                if (!e.isAlive()) {
                    e.onDeath(this);
                }
                hitAnyone = true;
            }
        }

        if (!hitAnyone) {
            System.out.println("No enemies close enough for Fan of Knives.");
        }
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }
}
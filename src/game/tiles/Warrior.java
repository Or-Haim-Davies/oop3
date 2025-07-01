package game.tiles;

import game.tiles.Player;
import game.tiles.Position;

import java.util.List;
import java.util.Random;

public class Warrior extends Player {
    private int abilityCooldown;
    private int remainingCooldown;
    private Random rand;

    public Warrior(Position position, String name,
                   int healthPool, int attack, int defense,
                   int abilityCooldown) {
        super(position, name, healthPool,healthPool, attack, defense);
        this.abilityCooldown = abilityCooldown;
        this.remainingCooldown = 0;
        this.rand = new Random();
    }



    public void onGameTick() {
        if (remainingCooldown > 0) {
            remainingCooldown--;
        }
    }

    @Override
    protected void levelUp() {
        super.levelUp(); // also does experience, level, health, attack, defense
        remainingCooldown = 0;
        healthPool += 5 * player_Level;
        healthAmount = healthPool;
        attackPoints += 2 * player_Level;
        defensePoints +=  player_Level;
        System.out.println(name + " gains Warrior bonuses on level up!");
    }

    public void castAbility(List<Enemy> enemies) {
        if (remainingCooldown > 0) {
            System.out.println("Ability on cooldown for " + remainingCooldown + " more ticks.");
            return;
        }

        int healAmount = 10 * defensePoints;
        healthAmount = Math.min(healthAmount + healAmount, healthPool);
        System.out.println(name + " heals for " + healAmount + ". Current health: " + healAmount);

        // Find enemies within range < 3
        List<Enemy> inRange = enemies.stream()
                .filter(e -> e.isAlive() && Utils.range( getPosition(),e.getPosition()) < 3)
                .toList();

        if (inRange.isEmpty()) {
            System.out.println("No enemies in range to hit.");
        } else {
            Enemy target = inRange.get(rand.nextInt(inRange.size()));
            int damage = (int)(0.1 * healthPool);
            target.receiveDamage(damage);
            System.out.println(name + " hits " + target.getName() + " for " + damage);
        }

        remainingCooldown = abilityCooldown;
    }
}



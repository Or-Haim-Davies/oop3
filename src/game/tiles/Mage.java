package game.tiles;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class Mage extends Player {
    private int manaPool;
    private int currentMana;
    private int manaCost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;

    private Random rand;

    public Mage(Position position, String name,
                int healthPool, int healthAmount,
                int attack, int defense,
                int manaPool, int manaCost, int spellPower,
                int hitsCount, int abilityRange) {
        super(position, name, healthPool, healthAmount, attack, defense);
        this.manaPool = manaPool;
        this.currentMana = manaPool;
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
        this.rand = new Random();
    }

    @Override
    protected void levelUp() {
        super.levelUp(); // also updates health, attack, defense, etc.
        manaPool += 25 * player_Level;
        currentMana = Math.min(currentMana + manaPool / 4, manaPool);
        spellPower += 10 * player_Level;
        System.out.println(name + " grows as a Mage: mana pool now " + manaPool + ", spell power now " + spellPower);
    }


    public void onGameTick() {
        currentMana = Math.min(manaPool, (currentMana +1) * player_Level);
    }









    public void castAbility(List<Enemy> enemies) {
        if (currentMana < manaCost) {
            System.out.println("Not enough mana to cast Blizzard!");
            return;
        }

        currentMana -= manaCost;
        int hits = 0;

        while (hits < hitsCount) {
            // filter living enemies within range
            List<Enemy> inRange = enemies.stream()
                    .filter(e -> e.isAlive() && getPosition().distance(e.getPosition()) < abilityRange)
                    .collect(Collectors.toList());

            if (inRange.isEmpty()) {
                break;
            }

            // pick one at random
            Enemy target = inRange.get(rand.nextInt(inRange.size()));
            int damage = Math.max(0, spellPower - target.getDefensePoints());
            target.receiveDamage(damage);

            System.out.println(name + " hits " + target.getName() + " with Blizzard for " + damage);
            if (!target.isAlive()) {
                target.onDeath(this);
            }

            hits++;
        }

        if (hits == 0) {
            System.out.println("No enemies in range for Blizzard.");
        }
    }











    public int getCurrentMana() {
        return currentMana;
    }

    public int getManaPool() {
        return manaPool;
    }

    public int getSpellPower() {
        return spellPower;
    }
}
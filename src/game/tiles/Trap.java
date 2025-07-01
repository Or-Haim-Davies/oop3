package game.tiles;

public class Trap extends Enemy {
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;

    public Trap(Position position, String name,
                int healthPool, int healthAmount,
                int attack, int defense,
                int experienceValue,
                int visibilityTime, int invisibilityTime) {
        super('T', position, name, healthPool, healthAmount, attack, defense, experienceValue);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
        this.visible = true;
    }

    @Override
    public void onEnemyTurn(Player player, Tile[][] board) {
        // update visibility
        if (ticksCount < visibilityTime) {
            visible = true;
        } else {
            visible = false;
        }

        if (ticksCount == visibilityTime + invisibilityTime) {
            ticksCount = 0;
        } else {
            ticksCount++;
        }

        // attack if close
        if (this.getPosition().distance(player.getPosition()) < 2) {
            attack(player);
        }
    }

    private void attack(Player player) {
        int damage = Math.max(0, this.attackPoints - player.getDefensePoints());
        player.receiveDamage(damage);
        System.out.println(name + " attacks " + player.getName() + " for " + damage);
    }

    public boolean isVisible() {
        return visible;
    }
}

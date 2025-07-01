package game.tiles;

import game.tiles.Player;
import game.tiles.Position;
import game.tiles.Tile;
import game.tiles.Unit;

public abstract class Enemy extends Unit {
    protected int experienceValue;

    public Enemy(char tileChar, Position position, String name,
                 int healthPool, int healthAmount,
                 int attack, int defense,
                 int experienceValue) {
        super(tileChar, position, name, healthPool, healthAmount, attack, defense);
        this.experienceValue = experienceValue;
    }

    public int getExperienceValue() {
        return experienceValue;
    }

    public void onDeath(Player player) {
        System.out.println(name + " died. " + player.getName() + " gains " + experienceValue + " XP.");
        player.gainExperience(experienceValue);
    }


    public abstract void onEnemyTurn(Player player, Tile[][] board);
}

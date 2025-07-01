package game.tiles;

import java.util.Random;

public class Monster extends Enemy {
    private int visionRange;
    private Random rand;

    public Monster(Position position, String name,
                   int healthPool, int healthAmount,
                   int attack, int defense,
                   int experienceValue,
                   int visionRange) {
        super('M', position, name, healthPool, healthAmount, attack, defense, experienceValue);
        this.visionRange = visionRange;
        this.rand = new Random();
    }

    @Override
    public void onEnemyTurn(Player player, Tile[][] board) {
        double dist = this.getPosition().distance(player.getPosition());

        if (dist < visionRange) {
            int dx = this.getPosition().getX() - player.getPosition().getX();
            int dy = this.getPosition().getY() - player.getPosition().getY();

            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0) move(-1, 0, board);  // move left
                else move(1, 0, board);          // move right
            } else {
                if (dy > 0) move(0, -1, board);  // move up
                else move(0, 1, board);          // move down
            }
        } else {
            // random move or stay
            int choice = rand.nextInt(5);  // 0..4
            switch (choice) {
                case 0 -> move(-1, 0, board);
                case 1 -> move(1, 0, board);
                case 2 -> move(0, -1, board);
                case 3 -> move(0, 1, board);
                default -> {} // stay
            }
        }
    }

    private void move(int dx, int dy, Tile[][] board) {
        int newX = getPosition().getX() + dx;
        int newY = getPosition().getY() + dy;
        // Add bounds checks & collision with Wall or other units
        if (isValidMove(newX, newY, board)) {
            setPosition( new Position(newX, newY));
            System.out.println(name + " moves to " + getPosition());
        }
    }

    private boolean isValidMove(int x, int y, Tile[][] board) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) return false;
        return !(board[x][y] instanceof Wall); // can't move into walls
    }
}

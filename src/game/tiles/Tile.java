package game.tiles;

public abstract class Tile {
    private char tile;
    private Position position;
    public Tile(char tile, Position position){
        this.tile=tile;
        this.position=position;
    }
    public Tile(char tile, int x, int y){
        this.tile=tile;
        this.position=new Position(x,y);
    }

    public Tile(){
        tile='.';
        position= new Position(0,0);
    }

    public void setPosition(Position position){
        this.position=position;
    }
    public Position getPosition() { return position; }
    public char getTileChar() { return tile; }


    @Override
    public String toString() {
        return String.valueOf(tile);
    }

}

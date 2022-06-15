package Business;

public abstract class Tile {
    protected char character;
    protected Position position;

    protected Tile(char character){
        this.character=character;
    }

    protected Tile(char character, Position position){
        this.character=character;
        this.position=position;
    }

    protected void initialize(Position position){
        this.position=position;
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }
}

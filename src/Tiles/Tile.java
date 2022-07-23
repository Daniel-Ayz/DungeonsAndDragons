package Tiles;

import Game.Position;

public abstract class Tile implements Comparable<Tile>{
    protected char character;
    protected Position position;

    protected Tile(char character){
        this.character=character;
    }

    public char getCharacter() {
        return character;
    }

    public Position getPosition() {
        return position;
    }

    protected void setPosition(Position position) {
        this.position = position;
    }
    //abstract-------------------
    protected abstract void accept(Unit unit);
    //---------------------------
    @Override
    public int compareTo(Tile tile) {
        return getPosition().compareTo(tile.getPosition());
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }
}

package Tiles;

import Game.Position;
import VisitorPattern.Visited;

public abstract class Tile implements Comparable<Tile>, Visited {
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
    public abstract void accept(Unit unit);
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

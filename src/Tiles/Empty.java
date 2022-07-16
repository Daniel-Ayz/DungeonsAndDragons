package Tiles;

import Game.Position;

public class Empty extends Tile {
    private static final char CHARACTER_EMPTY= '.';

    public Empty(Position position) {
        super(CHARACTER_EMPTY);
        setPosition(position);
    }

    @Override
    protected void accept(Unit unit) {
        unit.visit(this);
    }
}

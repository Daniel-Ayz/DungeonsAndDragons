package Tiles;

import Game.Position;
import VisitorPattern.Visited;

public class Empty extends Tile {
    private static final char CHARACTER_EMPTY= '.';

    public Empty(Position position) {
        super(CHARACTER_EMPTY);
        setPosition(position);
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
}

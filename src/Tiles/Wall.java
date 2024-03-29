package Tiles;

import Game.Position;

public class Wall extends Tile {
    private static final char CHARACTER_WALL= '#';

    public Wall(Position position) {
        super(CHARACTER_WALL);
        setPosition(position);
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
}

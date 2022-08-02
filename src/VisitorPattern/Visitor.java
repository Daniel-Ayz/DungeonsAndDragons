package VisitorPattern;

import Tiles.Tile;

public interface Visitor {
    void interact(Tile tile);
}

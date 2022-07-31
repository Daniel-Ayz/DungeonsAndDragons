package VisitorPattern;

import Tiles.Unit;

public interface Visited {
    void accept(Unit unit);
}

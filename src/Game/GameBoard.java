package Game;

import Tiles.Empty;
import Tiles.Tile;
import Tiles.Units.Enemy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {
    private List<Tile> tiles;

    protected GameBoard(Tile[][] board){
        tiles = new ArrayList<>();
        for(Tile[] line : board){
            tiles.addAll(Arrays.asList(line));
        }
    }

    protected Tile get(int x, int y) {
        for(Tile t : tiles){
            if (t.getPosition().samePosition(x,y)){
                return t;
            }
        }
        return null;
    }

    protected void remove(Enemy e) {
        tiles.remove(e);
        Position p = e.getPosition();
        tiles.add(new Empty(p));
    }

    @Override
    public String toString() {
        tiles = tiles.stream().sorted().collect(Collectors.toList());
        // TODO: Implement me
        return null;
    }
}

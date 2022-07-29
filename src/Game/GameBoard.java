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

    protected GameBoard(List<Tile> board){
        tiles = board;
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
        String board="";
        int row=0;
        for (Tile tile:tiles) {
            if(tile.getPosition().getX()>row){
                board+="\n";
                row++;
            }
            board+=tile.toString();
        }
        return board;
    }
}

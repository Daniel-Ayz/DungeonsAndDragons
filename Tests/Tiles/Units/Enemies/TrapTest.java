package Tiles.Units.Enemies;

import Callbacks.EnemyDeathCallback;
import Callbacks.GetTileCallBack;
import Game.Position;
import Tiles.Empty;
import Tiles.Tile;
import Tiles.Units.Player;
import Tiles.Units.Players.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrapTest {
    Trap trap;
    Player player;
    Position position1;
    Position position2;

    @BeforeEach
    void setUp() {
        trap = new Trap('Q', "Queen's Trap", 250, 50, 10, 100, 1, 2);
        player = new Warrior("Jon Snow", 300, 30, 4, 3);
        position1 = new Position(1, 2);
        position2 = new Position(1,1);
        trap.initializeOnLevel(position2, new GetTileCallBack() {
            @Override
            public Tile getTile(int x, int y) {
                return new Empty(position1);
            }
        }, new EnemyDeathCallback() {
            @Override
            public void call() {

            }
        });
    }

    @Test
    void testTakeTurn1() {
        trap.takeTurn(position1);
        trap.takeTurn(position1);
        assertEquals(".",trap.toString());
    }

    @Test
    void testTakeTurn2() {
        trap.takeTurn(position1);
        trap.takeTurn(position1);
        trap.takeTurn(position1);
        trap.takeTurn(position1);
        trap.takeTurn(position1);
        assertEquals("Q",trap.toString());
    }
}
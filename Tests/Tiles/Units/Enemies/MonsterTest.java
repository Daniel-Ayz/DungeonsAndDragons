package Tiles.Units.Enemies;

import CLI.UserInterface;
import Callbacks.EnemyDeathCallback;
import Callbacks.GetTileCallBack;
import Game.GameBoard;
import Game.LevelManager;
import Game.Position;
import Tiles.Empty;
import Tiles.Tile;
import Tiles.Units.Enemy;
import Tiles.Units.Player;
import Tiles.Units.Players.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {
    Monster monster;
    Player player;
    Position position1;
    Position position2;

    @BeforeEach
    void setUp() {
        monster = new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        monster = monster.constantMonster();
        player = new Warrior("Jon Snow", 300, 30, 4, 3);
        position1 = new Position(2, 1);
        position2 = new Position(1,1);
        monster.initializeOnLevel(position2, new GetTileCallBack() {
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
    void testTakeTurn() {
        monster.takeTurn(new Position(1,3));
        assertEquals(position1, monster.getPosition());
    }

    @Test
    void testMoveRandomly(){
        monster.moveRandomly();
        assertEquals(new Position(2,1), monster.getPosition());
    }
}
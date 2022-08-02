package Tiles;

import CLI.UserInterface;
import Callbacks.EnemyDeathCallback;
import Callbacks.GetTileCallBack;
import Callbacks.MessageCallback;
import Game.Position;
import Tiles.Units.Enemies.Monster;
import Tiles.Units.Enemies.Trap;
import Tiles.Units.Enemy;
import Tiles.Units.Player;
import Tiles.Units.Players.Rogue;
import Tiles.Units.Players.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    Player player;
    Empty empty;
    Wall wall;
    Monster monster;
    Position position1;
    Position position2;
    UserInterface ui;
    List<Enemy> list;

    @BeforeEach
    void setUp() {
        player = new Rogue("Arya Stark", 150, 40, 2, 20);
        position1 = new Position(1,1);
        position2 = new Position(1,2);
        ui = new UserInterface();
        player.initializeMessageCallBack(new MessageCallback() {
            @Override
            public void send(String m) {
                ui.print(m);
            }
        });
        list = new ArrayList<>();

        monster = new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        monster = monster.constantMonster();
        monster.initializeMessageCallBack(new MessageCallback() {
            @Override
            public void send(String m) {
                ui.print(m);
            }
        });

        empty = new Empty(position2);
        wall = new Wall(position2);
    }

    @Test
    void testInteract1() {
        monster.initializeOnLevel(position2, new GetTileCallBack() {
            @Override
            public Tile getTile(int x, int y) {
                return player;
            }
        }, new EnemyDeathCallback() {
            @Override
            public void call() {

            }
        });
        list.add(monster);
        player.initilizeOnLevel(position1, (int x, int y) -> {return monster;}, (int r) -> {return list;});

        player.interact(monster);
        assertTrue(monster.health.healthAmount < 80);
    }

    @Test
    void testInteract2() {
        player.initilizeOnLevel(position1, (int x, int y) -> {return empty;}, (int r) -> {return list;});
        player.interact(empty);
        assertEquals(position2,player.getPosition());
    }

    @Test
    void testInteract3(){
        monster.initializeOnLevel(position1, new GetTileCallBack() {
            @Override
            public Tile getTile(int x, int y) {
                return empty;
            }
        }, new EnemyDeathCallback() {
            @Override
            public void call() {

            }
        });
        monster.interact(empty);
        assertEquals(position2,monster.getPosition());
    }

    @Test
    void testInteract4(){
        monster.initializeOnLevel(position1, new GetTileCallBack() {
            @Override
            public Tile getTile(int x, int y) {
                return wall;
            }
        }, new EnemyDeathCallback() {
            @Override
            public void call() {

            }
        });
        monster.interact(wall);
        assertEquals(position1,monster.getPosition());
    }

    @Test
    void testInteract5() {
        player.initilizeOnLevel(position1, (int x, int y) -> {return wall;}, (int r) -> {return list;});
        player.interact(wall);
        assertEquals(position1,player.getPosition());
    }

    @Test
    void testInteract6() {
        monster.initializeOnLevel(position2, new GetTileCallBack() {
            @Override
            public Tile getTile(int x, int y) {
                return player;
            }
        }, new EnemyDeathCallback() {
            @Override
            public void call() {

            }
        });
        list.add(monster);
        player.initilizeOnLevel(position1, (int x, int y) -> {return monster;}, (int r) -> {return list;});

        monster.interact(player);
        assertTrue(player.health.healthAmount < 150);
    }
}
package Tiles.Units;

import CLI.UserInterface;
import Callbacks.EnemyDeathCallback;
import Callbacks.GetTileCallBack;
import Callbacks.MessageCallback;
import Game.Position;
import Tiles.Tile;
import Tiles.Units.Enemies.Monster;
import Tiles.Units.Players.Rogue;
import Tiles.Units.Players.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player;
    Monster monster;
    Position position1;
    Position position2;
    UserInterface ui;

    @BeforeEach
    void setUp() {
        player = new Rogue("Arya Stark", 150, 40, 2, 20);
        monster = new Monster('s', "Lannister Solider", 2, 8, 3,25, 3);
        monster = monster.constantMonster();
        position1 = new Position(1,1);
        position2 = new Position(1,2);
        ui = new UserInterface();

        player.initializeMessageCallBack(new MessageCallback() {
            @Override
            public void send(String m) {
                ui.print(m);
            }
        });
        List<Enemy> list = new ArrayList<>();
        monster.initializeMessageCallBack(new MessageCallback() {
            @Override
            public void send(String m) {
                ui.print(m);
            }
        });

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

    }

    @Test
    void testOnKill() {
       player.interact(monster); //monster should die beacuse it's health amount is 2
        assertEquals(25,player.experience);
    }
}
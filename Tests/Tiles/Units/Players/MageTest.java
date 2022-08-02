package Tiles.Units.Players;

import CLI.UserInterface;
import Callbacks.EnemiesInRangeCallBack;
import Callbacks.GetTileCallBack;
import Callbacks.MessageCallback;
import Game.Position;
import Tiles.Empty;
import Tiles.Tile;
import Tiles.Unit;
import Tiles.Units.Enemies.Monster;
import Tiles.Units.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MageTest {
    Mage mage;
    Position position1;
    Position position2;
    UserInterface ui;
    Monster monster;

    @BeforeEach
    void setUp() {
        mage = new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6);
        mage = mage.constantMage();
        position1 = new Position(1,1);
        position2 = new Position(2,2);
        monster = new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        monster = monster.constantMonster();
        monster.initializeMessageCallBack(new MessageCallback() {
            @Override
            public void send(String m) {
                ui.print(m);
            }
        });
        List<Enemy> list= new ArrayList<>();
        list.add(monster);

        ui = new UserInterface();
        mage.initilizeOnLevel(position1, (int x, int y) -> {return monster;}, (int r) -> {return list;});
        mage.initializeMessageCallBack(new MessageCallback() {
            @Override
            public void send(String m) {
                ui.print(m);
            }
        });
    }

    @Test
    void testLevelUp() {
        mage.levelUp();
        assertEquals(2,mage.getPlayerLevel());
        assertEquals(13,mage.getAttackPoints());
    }

    @Test
    void testOnGameTick() {
        mage.onGameTick();
        assertEquals(75,mage.getCurrentMana());
    }

    @Test
    void testCastAbility() {
        mage.castAbility();
        assertTrue(monster.health.healthAmount < 80);
    }
}
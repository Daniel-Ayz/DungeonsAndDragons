package Tiles.Units.Players;

import CLI.UserInterface;
import Callbacks.MessageCallback;
import Game.Position;
import Tiles.Units.Enemies.Monster;
import Tiles.Units.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HunterTest {

    Hunter hunter;
    Position position1;
    Position position2;
    UserInterface ui;
    Monster monster;

    @BeforeEach
    void setUp() {
        hunter = new Hunter("Ygritte", 220, 30, 2, 6);
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
        hunter.initilizeOnLevel(position1, (int x, int y) -> {return monster;}, (int r) -> {return list;});
        hunter.initializeMessageCallBack(new MessageCallback() {
            @Override
            public void send(String m) {
                ui.print(m);
            }
        });
    }

    @Test
    void testLevelUp() {
        hunter.levelUp();
        assertEquals(2,hunter.getPlayerLevel());
        assertEquals(42,hunter.getAttackPoints());
    }

    @Test
    void testOnGameTick() {
        for(int i = 0; i < 12; i++)
            hunter.onGameTick();
        assertEquals(11,hunter.getArrowsCount());
    }

    @Test
    void testCastAbility() {
        hunter.castAbility();
        assertTrue(monster.health.healthAmount < 80);
    }
}
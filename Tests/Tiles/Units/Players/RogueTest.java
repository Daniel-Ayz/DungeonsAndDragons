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

class RogueTest {
    Rogue rogue;
    Position position1;
    Position position2;
    UserInterface ui;
    Monster monster;

    @BeforeEach
    void setUp() {
        rogue = new Rogue("Arya Stark", 150, 40, 2, 20);
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
        rogue.initilizeOnLevel(position1, (int x, int y) -> {return monster;}, (int r) -> {return list;});
        rogue.initializeMessageCallBack(new MessageCallback() {
            @Override
            public void send(String m) {
                ui.print(m);
            }
        });
    }

    @Test
    void testLevelUp() {
        rogue.levelUp();
        assertEquals(2,rogue.getPlayerLevel());
        assertEquals(54,rogue.getAttackPoints());
    }

    @Test
    void testOnGameTick() {
        rogue.onGameTick();
        assertEquals(100,rogue.getCurrentEnergy());
    }

    @Test
    void testCastAbility() {
        rogue.castAbility();
        assertTrue(monster.health.healthAmount < 80);
    }
}
package Tiles.Units.Players;

import CLI.UserInterface;
import Callbacks.EnemyDeathCallback;
import Callbacks.MessageCallback;
import Game.Position;
import Tiles.Units.Enemies.Monster;
import Tiles.Units.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {

    Warrior warrior;
    Position position1;
    Position position2;
    UserInterface ui;
    Monster monster;

    @BeforeEach
    void setUp() {
        warrior = new Warrior("Jon Snow", 300, 30, 4, 3);
        warrior = warrior.constantWarrior();
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
        monster.initializeOnLevel(position2, (int x, int y) -> {
            return warrior;
        }, new EnemyDeathCallback() {
            @Override
            public void call() {

            }
        });
        List<Enemy> list= new ArrayList<>();
        list.add(monster);

        ui = new UserInterface();
        warrior.initilizeOnLevel(position1, (int x, int y) -> {return monster;}, (int r) -> {return list;});
        warrior.initializeMessageCallBack(new MessageCallback() {
            @Override
            public void send(String m) {
                ui.print(m);
            }
        });
    }

    @Test
    void testLevelUp() {
        warrior.levelUp();
        assertEquals(2,warrior.getPlayerLevel());
        assertEquals(42,warrior.getAttackPoints());
    }

    @Test
    void testOnGameTick() {
        warrior.onGameTick();
        warrior.castAbility();
        assertEquals(4, warrior.getRemainingCooldown());
    }

    @Test
    void testCastAbility() {
        warrior.castAbility();
        assertTrue(monster.health.healthAmount < 80);
    }
}
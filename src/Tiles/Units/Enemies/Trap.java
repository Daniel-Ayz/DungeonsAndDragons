package Tiles.Units.Enemies;

import Game.Position;
import Tiles.Units.Enemy;

public class Trap extends Enemy {
    private static final char CHARACTER_EMPTY= '.';
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;

    public Trap(char character, String name, int healthCapacity, int attackPoints, int defensePoints, int experienceValue, int visibilityTime, int invisibilityTime) {
        super(character, name, healthCapacity, attackPoints, defensePoints, experienceValue);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
        this.visible = true;
    }

    public void takeTurn(Position playerPosition){
        //if player in range attack him!
        visible = ticksCount < visibilityTime;
        if (ticksCount == (visibilityTime + invisibilityTime))
            ticksCount = 0;
        else
            ticksCount = ticksCount + 1;
        //fix:
//        if (Range.range(this.position, player) < 2)
//            attack(player);
    }

    private boolean closeEnemy(){
        //implement
        return false;
    }

    @Override
    public String toString(){
        return visible ? super.toString() : String.valueOf(CHARACTER_EMPTY);
    }

    @Override
    public String getDescription(){ //override it in each subclass
        return String.format("%s \t\t Health: %s \t\t Attack: %d \t\t Defense: %d \t\t Experience: %d", getName(), health.healthAmount, attackPoints, defensePoints);
    }
}

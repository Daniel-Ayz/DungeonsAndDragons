package Tiles.Units.Enemies;

import Game.Position;
import Tiles.Units.Enemy;

import java.util.Random;


public class Monster extends Enemy {
    private int visionRange;

    public Monster(char character, String name, int healthCapacity, int attackPoints, int defensePoints, int experienceValue, int visionRange) {
        super(character, name, healthCapacity, attackPoints, defensePoints, experienceValue);
        this.visionRange = visionRange;
    }

    public void takeTurn(Position playerPosition){
        //if sees player -> follow him
        //else moveRandomly()
    }

    protected void onGameTick(){

    }

    private void moveRandomly(){
        Random rand = new Random();
        int randomMove = rand.nextInt(5);

        switch (randomMove){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

    @Override
    public String getDescription(){ //override it in each subclass
        return String.format("%s \t\t Health: %s \t\t Attack: %d \t\t Defense: %d \t\t Experience: %d", getName(), health.healthAmount, attackPoints, defensePoints);
    }
}

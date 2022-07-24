package Tiles.Units.Enemies;

import Game.Position;
import Tiles.Units.Enemy;
import Tiles.Units.Player;

import java.util.Random;


public class Monster extends Enemy {
    protected int visionRange;

    public Monster(char character, String name, int healthCapacity, int attackPoints, int defensePoints, int experienceValue, int visionRange) {
        super(character, name, healthCapacity, attackPoints, defensePoints, experienceValue);
        this.visionRange = visionRange;
    }

    public void takeTurn(Position playerPosition){
        if(position.range(playerPosition)<visionRange)
            moveTowardsPlayer(playerPosition);
        else
            moveRandomly();
    }


    //?
    protected void onGameTick(){

    }

    protected void moveTowardsPlayer(Position playerPosition){
        int dx= position.getX()-playerPosition.getX();
        int dy= position.getY()-playerPosition.getX();
        if(Math.abs(dx)>Math.abs(dy)){
            if(dx>0)
                moveLeft();
            else
                moveRight();
        }
        else{
            if(dy>0)
                moveUp();
            else
                moveDown();
        }
    }

    protected void moveRandomly(){
        int randomMove = random.nextInt(5);

        switch (randomMove){
            case 0:
                //do nothing
                break;
            case 1:
                moveLeft();
                break;
            case 2:
                moveRight();
                break;
            case 3:
                moveUp();
                break;
            case 4:
                moveDown();
                break;
        }
    }

    @Override
    public String getDescription(){ //override it in each subclass
        return String.format("%s \t\t Health: %d/%d \t\t Attack: %d \t\t Defense: %d \t\t Experience Value: %d \t\t Vision range: %d", getName(), health.healthAmount, health.healthPool, attackPoints, defensePoints, experienceValue, visionRange);
    }
}

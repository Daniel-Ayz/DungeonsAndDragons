package Tiles;

import Callbacks.GetTileCallBack;
import Game.Position;
import Callbacks.MessageCallback;
import Tiles.Units.Enemy;
import Tiles.Units.Player;
import VisitorPattern.Visitor;

import java.util.Random;

public abstract class Unit extends Tile implements Visitor {

    public String name;
    public Health health;
    public int attackPoints;
    public int defensePoints;

    protected MessageCallback messageCallback;
    protected GetTileCallBack getTileCallBack;

    public Generator generator;

    public Unit(char character, String name, int healthCapacity, int attackPoints, int defensePoints) {
        super(character);
        this.name=name;
        this.health= new Health(healthCapacity);
        this.attackPoints=attackPoints;
        this.defensePoints=defensePoints;
        this.generator = new RandomGenerator();
    }

    public void swapPosition(Tile tile){
        Position temp=this.getPosition();
        this.setPosition(tile.getPosition());
        tile.setPosition(temp);
    }

    public boolean isDead(){
        return health.healthAmount<=0;
    }

    public void initializeOnLevel(Position position, GetTileCallBack getTileCallBack){
        setPosition(position);
        this.getTileCallBack=getTileCallBack;
    }

    public void initializeMessageCallBack(MessageCallback messageCallback){
        this.messageCallback=messageCallback;
    }

    protected void moveLeft(){
        interact(getTileCallBack.getTile(position.getX()-1, position.getY()));
    }

    protected void moveRight(){
        interact(getTileCallBack.getTile(position.getX()+1, position.getY()));
    }

    protected void moveUp(){
        interact(getTileCallBack.getTile(position.getX(), position.getY()-1));
    }

    protected void moveDown(){
        interact(getTileCallBack.getTile(position.getX(), position.getY()+1));
    }

//------------------------------------visitor--------------------------------
    // This unit attempts to interact with another tile.
    public void interact(Tile tile){
        tile.accept(this);
    }

    public void visit(Empty empty){
        swapPosition(empty);
    }

    public void visit(Wall wall){
        //do nothing
    }
//---------------------abstract-------------------------------------------
    public abstract void visit(Player p);
    public abstract void visit(Enemy e);
    // What happens when the unit dies
    public abstract void onDeath();
//--------------------------------------------------------------------------


    public int attackRoll(){
        int attackRoll= generator.generate(attackPoints+1);
        messageCallback.send(String.format("%s rolled %d attack points", getName(), attackRoll));
        return attackRoll;
    }

    public int defenseRoll(){
        int defenseRoll= generator.generate(defensePoints+1);
        messageCallback.send(String.format("%s rolled %d defense points", getName(), defenseRoll));
        return defenseRoll;
    }

    // Combat against another unit.
    public void battle(Unit u){
        messageCallback.send(String.format("%s engaged in combat with %s \n%s \n%s ", getName(), u.getName(), getDescription(), u.getDescription()));
        int damage= Math.max(attackRoll()-u.defenseRoll(),0);
        u.health.reduceHealth(damage);
        messageCallback.send(String.format("%s dealt %d damage to %s", getName(), damage, u.getName()));
    }

    public String getName(){
        return name;
    }

    public String getDescription(){ //override it in each subclass
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), health.healthAmount, attackPoints, defensePoints);
    }

    public class Health{
        public int healthPool;
        public int healthAmount;

        public Health(int healthPool) {
            this.healthPool = healthPool;
            this.healthAmount = healthPool;
        }

        public void healthPoolIncrease(int amount){
            healthPool+=amount;
        }

        public void healthAmountIncrease(int amount){
            healthAmount+=amount;
        }

        public void setHealthAmount(int amount){
            healthAmount=amount;
        }

        public void maxHeal(){
            healthAmount=healthPool;
        }

        public void reduceHealth(int amountToReduce){
            healthAmount-=amountToReduce;
        }

    }

}

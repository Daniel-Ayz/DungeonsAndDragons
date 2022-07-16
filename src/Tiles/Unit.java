package Tiles;

import Game.Position;
import Callbacks.MessageCallback;
import Tiles.Units.Enemy;
import Tiles.Units.Player;

public abstract class Unit extends Tile {

    public String name;
    public Health health;
    public int attackPoints;
    public int defensePoints;

    public Unit(char character, String name, int healthCapacity, int attackPoints, int defensePoints) {
        super(character);
        this.name=name;
        this.health= new Health(healthCapacity);
        this.attackPoints=attackPoints;
        this.defensePoints=defensePoints;
    }

    public void swapPosition(Tile tile){
        Position temp=this.getPosition();
        this.setPosition(tile.getPosition());
        tile.setPosition(temp);
    }

    public boolean isDead(){
        return health.healthAmount<=0;
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
    // Should be automatically called once the unit finishes its turn
    public abstract void processStep();

    // What happens when the unit dies
    public abstract void onDeath();
//--------------------------------------------------------------------------


//-----------------------------------not implemented----------------------------
    public void initialize(Position position, MessageCallback messageCallback){

    }
    public int attack(){
        return -1;
    }

    public int defend(){
        return -1;
    }

    // Combat against another unit.
    public void battle(Unit u){

    }
//------------------------------------------------------------------end

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

        public Health getHealth(){return this; }

        public void healthPoolIncrease(int amount){
            healthPool+=amount;
        }

        public void healthAmountIncrease(int amount){
            healthAmount+=amount;
        }

        public void maxHeal(){
            healthAmount=healthPool;
        }

        public void reduceHealth(int amountToReduce){
            healthAmount-=amountToReduce;
            //if(healthAmount<=0)
                //onDeath
        }

    }

}

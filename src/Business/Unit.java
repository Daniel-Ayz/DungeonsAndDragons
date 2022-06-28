package Business;

public abstract class Unit extends Tile{

    protected String name;
    protected Health health;
    protected int attackPoints;
    protected int defensePoints;

    protected Unit(char character, String name, int healthCapacity, int attackPoints, int defensePoints) {
        super(character);
        this.name=name;
        this.health= new Health(healthCapacity);
        this.attackPoints=attackPoints;
        this.defensePoints=defensePoints;
    }

    protected void swapPosition(Tile tile){
        Position temp=this.getPosition();
        this.setPosition(tile.getPosition());
        tile.setPosition(temp);
    }

    protected boolean isDead(){
        return health.healthAmount<=0;
    }


//------------------------------------visitor--------------------------------
    // This unit attempts to interact with another tile.
    protected void interact(Tile tile){
        tile.accept(this);
    }

    protected void visit(Empty empty){
        swapPosition(empty);
    }

    protected void visit(Wall wall){
        //do nothing
    }
//---------------------abstract-------------------------------------------
    protected abstract void visit(Player p);
    protected abstract void visit(Enemy e);
    // Should be automatically called once the unit finishes its turn
    protected abstract void processStep();

    // What happens when the unit dies
    protected abstract void onDeath();
//--------------------------------------------------------------------------


//-----------------------------------not implemented----------------------------
    protected void initialize(Position position, MessageCallback messageCallback){

    }
    protected int attack(){
        return -1;
    }

    protected int defend(){
        return -1;
    }

    // Combat against another unit.
    protected void battle(Unit u){

    }
//------------------------------------------------------------------end

    protected String getName(){
        return name;
    }

    protected String getDescription(){ //override it in each subclass
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), health.healthAmount, attackPoints, defensePoints);
    }

    class Health{
        protected int healthPool;
        protected int healthAmount;

        protected Health(int healthPool) {
            this.healthPool = healthPool;
            this.healthAmount = healthPool;
        }

        protected Health getHealth(){return this; }

        protected void healthPoolIncrease(int amount){
            healthPool+=amount;
        }

        protected void healthAmountIncrease(int amount){
            healthAmount+=amount;
        }

        protected void maxHeal(){
            healthAmount=healthPool;
        }

        protected void reduceHealth(int amountToReduce){
            healthAmount-=amountToReduce;
            //if(healthAmount<=0)
                //onDeath
        }

    }

}

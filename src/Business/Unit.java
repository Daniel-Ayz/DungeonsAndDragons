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

    protected String getName(){
        return name;
    }

    protected String getDescription(){ //override it in each subclass
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), health.healthAmount, attackPoints, defensePoints);
    }

    //-----------------------------------not implemented----------------------------
    protected void initialize(Position position, MessageCallback messageCallback){

    }

    protected int attack(){
        return -1;
    }

    public int defend(){
        return -1;
    }

    // Should be automatically called once the unit finishes its turn
    public abstract void processStep();

    // What happens when the unit dies
    public abstract void onDeath();

    // This unit attempts to interact with another tile.
    public void interact(Tile tile){

    }

    public void visit(Empty e){

    }

    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

    // Combat against another unit.
    protected void battle(Unit u){

    }
    //------------------------------------------------------------------end

    class Health{
        protected int healthPool;
        protected int healthAmount;

        protected Health(int healthPool) {
            this.healthPool = healthPool;
            this.healthAmount = healthPool;
        }

        public void healthPoolIncrease(int amount){
            healthPool+=amount;
        }

        protected void healthAmountIncrease(int amount){
            healthAmount+=amount;
        }

        protected void maxHeal(){
            healthAmount=healthPool;
        }

    }

}

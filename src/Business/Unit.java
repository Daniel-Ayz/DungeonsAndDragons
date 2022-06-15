package Business;

public abstract class Unit extends Tile{

    class Health{
        private int healthPool;
        private int healthAmount;

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


}

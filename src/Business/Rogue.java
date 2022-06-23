package Business;

public class Rogue extends Player{

    protected int cost;
    protected int currentEnergy;

    protected Rogue(String name, int healthCapacity, int attackPoints, int defensePoints, int cost) {
        super(name, healthCapacity, attackPoints, defensePoints);
        this.cost=cost;
        this.currentEnergy=100;
    }

    protected void levelUp() throws Exception {
        super.levelUp();
        currentEnergy= 100;
        attackPoints+=(3*(playerLevel-1));
    }

    @Override
    protected void onGameTick() {
        currentEnergy=Math.min(currentEnergy+10,100);
    }

    @Override
    protected void castAbility() throws Exception {
        if(currentEnergy<cost)
            throw new Exception("cannot cast ability has energy of: " + String.valueOf(currentEnergy));
        currentEnergy-=cost;
        //- For each enemy within range < 2, deal damage (reduce health value) equals to the rogue’s
        //attack points (each enemy will try to defend itself).
    }
}

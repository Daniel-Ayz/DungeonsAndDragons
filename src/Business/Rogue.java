package Business;

public class Rogue extends Player{
    protected static final int ROGUE_ATTACK_BONUS = 3;

    protected int cost;
    protected int currentEnergy;

    protected Rogue(String name, int healthCapacity, int attackPoints, int defensePoints, int cost) {
        super(name, healthCapacity, attackPoints, defensePoints);
        this.cost=cost;
        this.currentEnergy=100;
    }

    protected void levelUp() {
        super.levelUp();
        currentEnergy= 100;
        attackPoints+=(ROGUE_ATTACK_BONUS*playerLevel);
    }

    @Override
    protected void onGameTick() {
        currentEnergy=Math.min(currentEnergy+10,100);
    }

    @Override
    protected void castAbility(){
        currentEnergy-=cost;
        //- For each enemy within range < 2, deal damage (reduce health value) equals to the rogue’s
        //attack points (each enemy will try to defend itself).
    }
}

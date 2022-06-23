package Business;

public class Warrior extends Player{

    private int abilityCooldown;
    private int remainingCooldown;


    protected Warrior(String name, int healthCapacity, int attackPoints, int defensePoints, int abilityCooldown) {
        super(name, healthCapacity, attackPoints, defensePoints);
        this.abilityCooldown=abilityCooldown;
        this.remainingCooldown=0;
    }

    protected void levelUp() throws Exception {
        super.levelUp();
        remainingCooldown=0;
        health.healthPoolIncrease(5*(playerLevel-1));
        attackPoints+=2*(playerLevel-1);
        defensePoints+=(playerLevel-1);
    }

    protected void onGameTick(){
        remainingCooldown--;
    }

    @Override
    protected void castAbility() throws Exception {
        if(remainingCooldown>0)
            throw new Exception("cannot cast ability has remaining cool down of: " + String.valueOf(remainingCooldown));
        remainingCooldown=abilityCooldown;
        health.healthAmountIncrease(Math.min(health.healthPool, health.healthAmount+(10*defensePoints)));
        //- Randomly hits one enemy within range < 3 for an amount equals to 10% of the warrior’s
        //health pool
    }
}

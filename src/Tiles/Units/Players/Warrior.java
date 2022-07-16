package Tiles.Units.Players;

import Tiles.Units.Player;

public class Warrior extends Player {
    protected static final int WARRIOR_HEALTH_BONUS = 5;
    protected static final int WARRIOR_ATTACK_BONUS = 2;
    protected static final int WARRIOR_DEFENSE_BONUS = 1;

    private int abilityCooldown;
    private int remainingCooldown;


    public Warrior(String name, int healthCapacity, int attackPoints, int defensePoints, int abilityCooldown) {
        super(name, healthCapacity, attackPoints, defensePoints);
        this.abilityCooldown=abilityCooldown;
        this.remainingCooldown=0;
    }

    protected void levelUp() {
        super.levelUp();
        remainingCooldown=0;
        health.healthPoolIncrease(WARRIOR_HEALTH_BONUS*playerLevel);
        attackPoints+=WARRIOR_ATTACK_BONUS*playerLevel;
        defensePoints+=WARRIOR_DEFENSE_BONUS*playerLevel;
    }

    protected void onGameTick(){
        remainingCooldown--;
    }

    @Override
    protected void castAbility() {
        remainingCooldown=abilityCooldown;
        health.healthAmountIncrease(Math.min(health.healthPool, health.healthAmount+(10*defensePoints)));
        //- Randomly hits one enemy within range < 3 for an amount equals to 10% of the warrior’s
        //health pool
    }
}

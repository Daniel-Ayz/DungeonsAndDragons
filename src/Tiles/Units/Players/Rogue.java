package Tiles.Units.Players;

import Tiles.Units.Enemy;
import Tiles.Units.Player;

import java.util.List;

public class Rogue extends Player {
    protected static final int ROGUE_ATTACK_BONUS = 3;
    protected static final int ROGUE_MAX_ENERGY = 100;

    protected int cost;
    protected int currentEnergy;

    public Rogue(String name, int healthCapacity, int attackPoints, int defensePoints, int cost) {
        super(name, healthCapacity, attackPoints, defensePoints);
        this.cost=cost;
        this.currentEnergy=ROGUE_MAX_ENERGY;
    }

    protected void levelUp() {
        int beforeHealth= health.healthPool;
        int beforeAttack= attackPoints;
        int beforeDefense= defensePoints;
        super.levelUp();
        currentEnergy= ROGUE_MAX_ENERGY;
        attackPoints+=(ROGUE_ATTACK_BONUS*playerLevel);
        messageCallback.send(String.format("%s Leveled up to level: %d. gained +%d Health, +%d Attack, +%d Defense", name ,playerLevel , health.healthPool-beforeHealth, attackPoints-beforeAttack, defensePoints-beforeDefense));
    }

    @Override
    public void onGameTick() {
        currentEnergy=Math.min(currentEnergy+10,ROGUE_MAX_ENERGY);
    }

    @Override
    public void castAbility(){
        if(currentEnergy<cost)
            messageCallback.send(String.format("Can't cast ability, current energy: %d",currentEnergy));
        else{
            currentEnergy-=cost;
            messageCallback.send(String.format("%s cast Fan of Knives.",getName()));
            List<Enemy> enemies= enemiesInRangeCallBack.getEnemies(2);
            if(!enemies.isEmpty()){
                for (Enemy e:enemies) {
                    specialAbilityAttack(e);
                }
            }
        }
    }

    @Override
    protected int getAbilityDamage() {
        return attackPoints;
    }

    @Override
    public String getDescription(){ //override it in each subclass
        return String.format("%s \t\t Health: %d/%d \t\t Attack: %d \t\t Defense: %d \t\t Level: %d \t\t Experience: %d/%d \t\t Energy: %d/%d", getName(), health.healthAmount, health.healthPool, attackPoints, defensePoints, playerLevel, experience,levelUpReq(),currentEnergy,ROGUE_MAX_ENERGY);
    }
}

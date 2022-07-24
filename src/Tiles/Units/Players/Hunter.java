package Tiles.Units.Players;

import Tiles.Units.Enemy;
import Tiles.Units.Player;

import java.util.Comparator;
import java.util.List;

public class Hunter extends Player {

    protected static final int HUNTER_ATTACK_BONUS = 2;
    protected static final int HUNTER_DEFENSE_BONUS = 1;
    protected static final int HUNTER_ARROW_BONUS = 10;

    protected int shootingRange;
    protected int arrowsCount;
    protected int ticksCount;

    public Hunter(String name, int healthCapacity, int attackPoints, int defensePoints, int shootingRange) {
        super(name, healthCapacity, attackPoints, defensePoints);
        this.shootingRange=shootingRange;
        this.arrowsCount=HUNTER_ARROW_BONUS*playerLevel;
        this.ticksCount=0;
    }

    protected void levelUp() {
        int beforeHealth= health.healthPool;
        int beforeAttack= attackPoints;
        int beforeDefense= defensePoints;
        int beforeArrowCount=arrowsCount;
        super.levelUp();
        arrowsCount+= 10;
        attackPoints+=(HUNTER_ATTACK_BONUS*playerLevel);
        defensePoints+=(HUNTER_DEFENSE_BONUS*playerLevel);
        arrowsCount+=(HUNTER_ARROW_BONUS*playerLevel);
        messageCallback.send(String.format("%s Leveled up to level: %d. gained +%d Health, +%d Attack, +%d Defense, +%d Arrows", name ,playerLevel , health.healthPool-beforeHealth, attackPoints-beforeAttack, defensePoints-beforeDefense, arrowsCount-beforeArrowCount));
    }

    @Override
    public void onGameTick() {
        if(ticksCount==10){
            arrowsCount+=playerLevel;
            ticksCount=0;
        }
        else
            ticksCount++;
    }

    @Override
    public void castAbility(){
        List<Enemy> enemies= enemiesInRangeCallBack.getEnemies(shootingRange+1);
        if(arrowsCount<=0)
            messageCallback.send(String.format("%s Can't cast ability, no arrows",getName()));
        else if(enemies.isEmpty())
            messageCallback.send(String.format("%s Can't cast ability, no enemies in range",getName()));
        else{
            arrowsCount--;
            Enemy closest= enemies.stream().min(Comparator.comparingInt(x->x.getPosition().range(getPosition()))).orElse(enemies.get(0));
            messageCallback.send(String.format("%s fired an arrow at %s",getName(),closest.getName()));
            specialAbilityAttack(closest);
        }
    }

    @Override
    protected int getAbilityDamage() {
        return attackPoints;
    }

    @Override
    public String getDescription(){ //override it in each subclass
        return String.format("%s \t\t Health: %d/%d \t\t Attack: %d \t\t Defense: %d \t\t Level: %d \t\t Experience: %d/%d \t\t Arrows: %d \t\t Range: %d", getName(), health.healthAmount, health.healthPool, attackPoints, defensePoints, playerLevel, experience,levelUpReq(),arrowsCount,shootingRange);
    }

}

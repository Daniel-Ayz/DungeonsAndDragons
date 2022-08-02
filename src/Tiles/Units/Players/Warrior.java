package Tiles.Units.Players;

import Tiles.ConstantGenerator;
import Tiles.Units.Enemy;
import Tiles.Units.Player;

import java.util.List;

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

    public Warrior constantWarrior(){
        Warrior warrior = new Warrior(this.name, this.health.healthPool, this.attackPoints, this.defensePoints, this.abilityCooldown);
        warrior.generator = new ConstantGenerator();
        return warrior;
    }

    public int getRemainingCooldown(){
        return this.remainingCooldown;
    }

    protected void levelUp() {
        int beforeHealth= health.healthPool;
        int beforeAttack= attackPoints;
        int beforeDefense= defensePoints;
        super.levelUp();
        remainingCooldown=0;
        health.healthPoolIncrease(WARRIOR_HEALTH_BONUS*playerLevel);
        attackPoints+=WARRIOR_ATTACK_BONUS*playerLevel;
        defensePoints+=WARRIOR_DEFENSE_BONUS*playerLevel;
        messageCallback.send(String.format("%s Leveled up to level: %d. gained +%d Health, +%d Attack, +%d Defense", name ,playerLevel , health.healthPool-beforeHealth, attackPoints-beforeAttack, defensePoints-beforeDefense));
    }

    public void onGameTick(){
        remainingCooldown=Math.max(remainingCooldown-1,0);
    }

    @Override
    public void castAbility() {
        if(remainingCooldown>0)
            messageCallback.send(String.format("Can't cast ability remaining cooldown: %d",remainingCooldown));
        else{
            int previousHP= health.healthAmount;
            remainingCooldown=abilityCooldown+1;
            health.setHealthAmount(Math.min(health.healthPool, health.healthAmount+(10*defensePoints)));
            int afterHealHp= health.healthAmount;
            messageCallback.send(String.format("%s cast Avenger's Shield, healing for %d",getName(),afterHealHp-previousHP));
            List<Enemy> enemies= enemiesInRangeCallBack.getEnemies(3);
            if(!enemies.isEmpty()){
                int randomInd=super.generator.generate(enemies.size());
                Enemy e=enemies.get(randomInd);
                specialAbilityAttack(e);
            }
        }
    }

    @Override
    protected int getAbilityDamage() {
        return health.healthPool;
    }

    @Override
    public String getDescription(){ //override it in each subclass
        return String.format("%s \t\t Health: %d/%d \t\t Attack: %d \t\t Defense: %d \t\t Level: %d \t\t Experience: %d/%d Cooldown: %d/%d ", getName(), health.healthAmount, health.healthPool, attackPoints, defensePoints, playerLevel, experience,levelUpReq(), remainingCooldown,abilityCooldown);
    }
}

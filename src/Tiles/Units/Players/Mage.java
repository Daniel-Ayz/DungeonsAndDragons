package Tiles.Units.Players;

import Tiles.Units.Enemy;
import Tiles.Units.Player;

import java.util.List;

public class Mage extends Player {
    protected static final int MAGE_MANA_POOL_BONUS = 25;
    protected static final int MAGE_SPELL_POWER_BONUS = 10;

    protected int manaPool;
    protected int currentMana;
    protected int manaCost;
    protected int spellPower;
    protected int hitsCount;
    protected int abilityRange;

    public Mage(String name, int healthCapacity, int attackPoints, int defensePoints, int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange) {
        super(name, healthCapacity, attackPoints, defensePoints);
        this.manaPool=manaPool;
        this.currentMana=manaPool/4;
        this.manaCost=manaCost;
        this.spellPower=spellPower;
        this.hitsCount=hitsCount;
        this.abilityRange=abilityRange;
    }

    protected void levelUp() {
        int beforeHealth= health.healthPool;
        int beforeAttack= attackPoints;
        int beforeDefense= defensePoints;
        int beforeManaPool= manaPool;
        int beforeSpellPower= spellPower;
        super.levelUp();
        manaPool+=(MAGE_MANA_POOL_BONUS*playerLevel);
        currentMana=Math.min(currentMana+(manaPool/4),manaPool);
        spellPower+=(MAGE_SPELL_POWER_BONUS*playerLevel);
        messageCallback.send(String.format("%s Leveled up to level: %d. gained +%d Health, +%d Attack, +%d Defense, +%d Max Mana, +%d Spell Power", name ,playerLevel , health.healthPool-beforeHealth, attackPoints-beforeAttack, defensePoints-beforeDefense, manaPool-beforeManaPool, spellPower-beforeSpellPower ));
    }

    public void onGameTick(){
        currentMana=Math.min(manaPool,currentMana*playerLevel);
    }

    @Override
    protected void castAbility() {
        if(currentMana<manaCost)
            messageCallback.send(String.format("Can't cast ability, current energy: %d",currentMana));
        else{
            currentMana-=manaCost;
            messageCallback.send(String.format("%s cast Blizzard.",getName()));
            List<Enemy> enemies= enemiesInRangeCallBack.getEnemies(abilityRange);
            int hits=0;
            while(hits<hitsCount & !enemies.isEmpty()){ // && (∃ living enemy s.t. range(enemy, player) < ability range)
                int randomInd=super.random.nextInt(0,enemies.size());
                Enemy e=enemies.get(randomInd);
                specialAbilityAttack(e);
                hits++;
                enemies= enemiesInRangeCallBack.getEnemies(abilityRange);
            }
        }
    }

    @Override
    protected int getAbilityDamage() {
        return spellPower;
    }

    @Override
    public String getDescription(){ //override it in each subclass
        return String.format("%s \t\t Health: %d/%d \t\t Attack: %d \t\t Defense: %d \t\t Level: %d \t\t Experience: %d/%d \t\t Mana: %d/%d \t\t SpellPower: %d ", getName(), health.healthAmount, health.healthPool, attackPoints, defensePoints, playerLevel, experience,levelUpReq(),currentMana,manaPool,spellPower);
    }
}

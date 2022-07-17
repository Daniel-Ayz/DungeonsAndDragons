package Tiles.Units.Players;

import Tiles.Units.Player;

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
        messageCallback.send(String.format("%s Leveled up to level: %d. gained +%d Health, +%d Attack, +% Defense, +%d Max Mana, +%d Spell Power", name ,playerLevel , health.healthPool-beforeHealth, attackPoints-beforeAttack, defensePoints-beforeDefense, manaPool-beforeManaPool, spellPower-beforeSpellPower ));
    }

    protected void onGameTick(){
        currentMana=Math.min(manaPool,currentMana*playerLevel);
    }

    @Override
    protected void castAbility() {
        currentMana-=manaCost;
        int hits=0;
        while(hits<hitsCount ){ // && (∃ living enemy s.t. range(enemy, player) < ability range)
            //- Select random enemy within ability range.
            //- Deal damage (reduce health value) to the chosen enemy for an amount equal to spell power
            //(each enemy may try to defend itself).
            hits++;
        }
    }
}

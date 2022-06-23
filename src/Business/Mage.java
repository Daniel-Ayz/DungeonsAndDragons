package Business;

public class Mage extends Player{

    protected int manaPool;
    protected int currentMana;
    protected int manaCost;
    protected int spellPower;
    protected int hitsCount;
    protected int abilityRange;

    protected Mage(String name, int healthCapacity, int attackPoints, int defensePoints, int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange) {
        super(name, healthCapacity, attackPoints, defensePoints);
        this.manaPool=manaPool;
        this.currentMana=manaPool/4;
        this.manaCost=manaCost;
        this.spellPower=spellPower;
        this.hitsCount=hitsCount;
        this.abilityRange=abilityRange;
    }

    protected void levelUp() throws Exception {
        super.levelUp();
        manaPool+=(25*(playerLevel-1));
        currentMana=Math.min(currentMana+(manaPool/4),manaPool);
        spellPower+=(10*(playerLevel-1));
    }

    protected void onGameTick(){
        currentMana=Math.min(manaPool,currentMana*playerLevel);
    }

    @Override
    protected void castAbility() throws Exception {
        if(currentMana<manaCost)
            throw new Exception("cannot cast ability with current mana: " + String.valueOf(currentMana));
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

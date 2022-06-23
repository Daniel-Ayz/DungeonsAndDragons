package Business;

public abstract class Player extends Unit{
    protected int experience;
    protected int playerLevel;

    protected Player(String name, int healthCapacity, int attackPoints, int defensePoints) {
        super('@', name, healthCapacity, attackPoints, defensePoints);
        this.experience=0;
        this.playerLevel=1;
    }

    protected void levelUp() throws Exception {
        if(experience>=50*playerLevel){
            experience-=playerLevel*50;
            playerLevel++;
            health.healthPoolIncrease(10*playerLevel);
            health.maxHeal();
            attackPoints+=4*playerLevel;
            defensePoints+=playerLevel;
        }
        else throw new Exception("can't level up, there is no enough exp");
    }

    protected abstract void onGameTick();
    protected abstract void castAbility() throws Exception;
}

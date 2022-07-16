package Business;

public abstract class Player extends Unit{
    protected static final char PLAYER_TILE='@';
    protected static final int REQ_EXP = 50;
    protected static final int ATTACK_BONUS = 4;
    protected static final int DEFENSE_BONUS = 1;
    protected static final int HEALTH_BONUS = 10;

    protected int experience;
    protected int playerLevel;

    protected Player(String name, int healthCapacity, int attackPoints, int defensePoints) {
        super(PLAYER_TILE, name, healthCapacity, attackPoints, defensePoints);
        this.experience=0;
        this.playerLevel=1;
    }

    protected void levelUp() {
        experience -= levelUpReq();
        playerLevel++;
        health.healthPoolIncrease(HEALTH_BONUS*playerLevel);
        health.maxHeal();
        attackPoints+=ATTACK_BONUS*playerLevel;
        defensePoints+=DEFENSE_BONUS*playerLevel;
    }

    //------------------------------------visitor--------------------------------
    protected void accept(Unit unit){
        unit.visit(this);
    }

    public void visit(Player p){
        //do nothing - will never happen.
    }

    public void visit(Enemy e){
        super.battle(e);
        if(e.isDead()){
            super.swapPosition(e);
            onKill(e);
        }
    }
    //--------------------------------------------------------------------



    protected void onKill(Enemy enemy){
        //int exp= enemy.getExperience();
        //messagecallback
        //addExperience(exp);
        enemy.onDeath();
    }

    protected void onDeath(){
        //messagecallback
        //ondeathcallback
    }

    protected void processStep(){
        //to do
    }

    protected int levelUpReq(){
        return playerLevel*REQ_EXP;
    }

    protected void addExperience(int experienceToAdd){
        this.experience += experienceToAdd;
        int levelUpReq= levelUpReq();
        while(experience>=levelUpReq){
            levelUp();
            levelUpReq=levelUpReq();
        }
    }

    //--------------------------abstract--------------------------
    protected abstract void onGameTick();
    protected abstract void castAbility();
    //------------------------------------------------------------
}

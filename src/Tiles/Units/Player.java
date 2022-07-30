package Tiles.Units;

import Callbacks.EnemiesInRangeCallBack;
import Callbacks.GetTileCallBack;
import Callbacks.PlayerDeathCallback;
import Game.Action;
import Game.Position;
import Tiles.Unit;

public abstract class Player extends Unit implements HeroicUnit{
    protected static final char PLAYER_TILE='@';
    protected static final int REQ_EXP = 50;
    protected static final int ATTACK_BONUS = 4;
    protected static final int DEFENSE_BONUS = 1;
    protected static final int HEALTH_BONUS = 10;

    protected int experience;
    protected int playerLevel;

    //do we need it?
    //protected PlayerDeathCallback playerDeathCallback;
    protected EnemiesInRangeCallBack enemiesInRangeCallBack;


    protected Player(String name, int healthCapacity, int attackPoints, int defensePoints) {
        super(PLAYER_TILE, name, healthCapacity, attackPoints, defensePoints);
        this.experience=0;
        this.playerLevel=1;
    }

    public void initilizeOnLevel(Position position,GetTileCallBack getTileCallBack ,EnemiesInRangeCallBack enemiesInRangeCallBack){
        super.initializeOnLevel(position,getTileCallBack);
        this.enemiesInRangeCallBack= enemiesInRangeCallBack;
        //this.playerDeathCallBack=playerDeathCallBack;
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

    protected void specialAbilityAttack(Enemy enemy){
        int damage= Math.max(getAbilityDamage()-enemy.defenseRoll(),0);
        enemy.health.reduceHealth(damage);
        messageCallback.send(String.format("%s dealt %d special ability damage to %s ", getName(), damage, enemy.getName()));
        if(enemy.isDead()){
            onKill(enemy);
        }
    }

    protected void onKill(Enemy enemy){
        int exp= enemy.getExperienceValue();
        messageCallback.send(String.format("%s is died. %s gained %d experience", enemy.getName(),getName(), exp));
        addExperience(exp);
        enemy.onDeath();
    }

    public void onDeath(){
        messageCallback.send("You lost.");
        //playerDeathCallback.call(this);
    }

    public void TakeTurn(Action action){
        switch (action){
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case SPECIAL_ABILITY:
                castAbility();
                break;
            case NONE:
                break;
        }
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

    @Override
    public String toString(){
        return isDead() ? "X" : super.toString();
    }

    //--------------------------abstract--------------------------
    public abstract void onGameTick();
    public abstract void castAbility();
    protected abstract int getAbilityDamage();
    //------------------------------------------------------------
    //?
    public void processStep(){
    }
}

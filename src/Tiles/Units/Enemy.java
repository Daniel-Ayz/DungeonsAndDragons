package Tiles.Units;

import Callbacks.EnemyDeathCallback;
import Callbacks.GetTileCallBack;
import Callbacks.MessageCallback;
import Game.Position;
import Tiles.Unit;

public abstract class Enemy extends Unit {

    protected int experienceValue;

    protected EnemyDeathCallback enemyDeathCallback;

    protected Enemy(char character, String name, int healthCapacity, int attackPoints, int defensePoints, int experienceValue) {
        super(character, name, healthCapacity, attackPoints, defensePoints);
        this.experienceValue=experienceValue;
    }

    public void initializeOnLevel(Position position, GetTileCallBack getTileCallBack, EnemyDeathCallback enemyDeathCallback) {
        super.initializeOnLevel(position, getTileCallBack);
        this.enemyDeathCallback= enemyDeathCallback;
    }

    public int getExperienceValue() {
        return experienceValue;
    }

    @Override
    public void visit(Player p) {
        super.battle(p);
        if(p.isDead()){
            messageCallback.send(String.format("%s killed %s.",getName(),p.getName()));
            p.onDeath();
        }
    }

    @Override
    public void visit(Enemy e) {
        //do nothing
    }



    @Override
    public void onDeath() {
        enemyDeathCallback.call();
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }

    //?
    @Override
    public void processStep() {

    }

    public abstract void takeTurn(Position playerPosition);
}

package Tiles.Units;

import Tiles.Unit;

public abstract class Enemy extends Unit {
    protected Enemy(char character, String name, int healthCapacity, int attackPoints, int defensePoints, int experienceValue) {
        super(character, name, healthCapacity, attackPoints, defensePoints);
    }

    @Override
    public void visit(Player p) {
        battle(p);
        if(p.isDead()){
            //messages
            p.onDeath();
        }
    }

    @Override
    public void visit(Enemy e) {
        //do nothing
    }

    @Override
    public void processStep() {

    }

    @Override
    public void onDeath() {

    }


    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
}

package Business;

public class Enemy extends Unit{
    protected Enemy(char character, String name, int healthCapacity, int attackPoints, int defensePoints) {
        super(character, name, healthCapacity, attackPoints, defensePoints);
    }

    @Override
    protected void visit(Player p) {
        battle(p);
        if(p.isDead()){
            //messages
            p.onDeath();
        }
    }

    @Override
    protected void visit(Enemy e) {
        //do nothing
    }

    @Override
    protected void processStep() {

    }

    @Override
    protected void onDeath() {

    }


    @Override
    protected void accept(Unit unit) {
        unit.visit(this);
    }
}

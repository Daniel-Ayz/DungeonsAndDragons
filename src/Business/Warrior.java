package Business;

public class Warrior extends Player{
    protected Warrior(char character, String name, int healthCapacity, int attackPoints, int defensePoints) {
        super(character, name, healthCapacity, attackPoints, defensePoints);
    }

    @Override
    protected void castAbility() {

    }
}

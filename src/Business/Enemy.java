package Business;

public abstract class Enemy extends Unit{
    protected int experienceValue;

    protected Enemy(char character, String name, int healthCapacity, int attackPoints, int defensePoints, int experienceValue) {
        super(character, name, healthCapacity, attackPoints, defensePoints);
        this.experienceValue = experienceValue;
    }

    protected abstract void onGameTick();
}

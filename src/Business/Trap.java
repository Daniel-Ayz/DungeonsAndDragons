package Business;

public class Trap extends Enemy{
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;

    protected Trap(char character, String name, int healthCapacity, int attackPoints, int defensePoints, int experienceValue, int visibilityTime, int invisibilityTime) {
        super(character, name, healthCapacity, attackPoints, defensePoints, experienceValue);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
        this.visible = true;
    }

    protected void onGameTick(){
        visible = ticksCount < visibilityTime;
        if (ticksCount == (visibilityTime + invisibilityTime))
            ticksCount = 0;
        else
            ticksCount = ticksCount + 1;
        if (Range.range(this.position, player) < 2)
            attack(player);
    }

    private boolean closeEnemy(){

    }
}

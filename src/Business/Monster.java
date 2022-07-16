package Business;
import java.util.Random;


public class Monster extends Enemy{
    private int visionRange;

    protected Monster(char character, String name, int healthCapacity, int attackPoints, int defensePoints, int experienceValue, int visionRange) {
        super(character, name, healthCapacity, attackPoints, defensePoints, experienceValue);
        this.visionRange = visionRange;
    }

    protected void onGameTick(){

    }

    private void moveRandomly(){
        Random rand = new Random();
        int randomMove = rand.nextInt(5);

        switch (randomMove){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }
}

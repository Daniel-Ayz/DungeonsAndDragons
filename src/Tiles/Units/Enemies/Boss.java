package Tiles.Units.Enemies;

import Callbacks.GetPlayerCallBack;
import Game.Position;
import Tiles.Units.Enemy;
import Tiles.Units.HeroicUnit;
import Tiles.Units.Player;

public class Boss extends Monster implements HeroicUnit {

    protected GetPlayerCallBack getPlayerCallBack;
    protected int abilityFrequency;
    protected int combatTicks;

    public Boss(char character, String name, int healthCapacity, int attackPoints, int defensePoints, int experienceValue, int visionRange, int abilityFrequency) {
        super(character, name, healthCapacity, attackPoints, defensePoints, experienceValue,visionRange);
        this.abilityFrequency = abilityFrequency;
        this.combatTicks=0;
    }

    //Who will set this? and how?
    public void setGetPlayerCallBack(GetPlayerCallBack getPlayerCallBack) {
        this.getPlayerCallBack = getPlayerCallBack;
    }

    @Override
    public void takeTurn(Position playerPosition) {
        if(position.range(playerPosition)<visionRange){
            if(combatTicks==abilityFrequency){
                combatTicks=0;
                castAbility();
            }
            else{
                combatTicks++;
                moveTowardsPlayer(playerPosition);
            }
        }
        else{
            combatTicks=0;
            moveRandomly();
        }
    }

    @Override
    public void castAbility() {
        Player p= getPlayerCallBack.getPlayer();
        int damage= Math.max(attackPoints-p.defenseRoll(),0);
        p.health.reduceHealth(damage);
        messageCallback.send(String.format("%s dealt %d special ability damage to %s ", getName(), damage, p.getName()));
        if(p.isDead()){
            messageCallback.send(String.format("%s killed %s.",getName(),p.getName()));
            p.onDeath();
        }
    }
}

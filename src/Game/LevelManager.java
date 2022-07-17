package Game;

import Tiles.Units.Enemy;
import Tiles.Units.Player;

import java.util.List;

public class LevelManager {
    protected GameBoard board;
    protected List<Enemy> enemies;
    protected Player player;
    //CLI

    public LevelManager(){

    }

    public boolean playLevel(){
        while(!player.isDead() && !enemies.isEmpty()){
            //player action
            //loop enemies and do action
            //CLI - print
        }
        return !player.isDead();
    }



}

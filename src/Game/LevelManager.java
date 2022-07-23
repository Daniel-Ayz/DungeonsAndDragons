package Game;

import Tiles.Units.Enemy;
import Tiles.Units.Player;

import java.util.List;
import java.util.stream.Collectors;

public class LevelManager {
    protected GameBoard board;
    protected List<Enemy> enemies;
    protected Player player;
    //CLI

    public LevelManager(Player player, GameBoard board, List<Enemy> enemies){
        this.board= board;
        this.enemies = enemies;
        this.player = player;
        initializeLevel();
    }

    public void initializeLevel(){
        player.setEnemiesInRangeCallBack((requestedRange)->enemies.stream().filter(x->x.getPosition().range(player.getPosition())<requestedRange).collect(Collectors.toList()));
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

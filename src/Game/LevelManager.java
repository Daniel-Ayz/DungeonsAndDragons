package Game;

import CLI.UserInterface;
import Tiles.Units.Enemy;
import Tiles.Units.Player;

import java.util.List;
import java.util.stream.Collectors;

public class LevelManager {
    protected GameBoard board;
    protected List<Enemy> enemies;
    protected Player player;
    UserInterface ui;

    public LevelManager(Player player, GameBoard board, List<Enemy> enemies, UserInterface ui){
        this.board= board;
        this.enemies = enemies;
        this.player = player;
        this.ui=ui;
        initializeLevel();
    }

    public void initializeLevel(){
        player.setCallBacks((requestedRange)->enemies.stream().filter(x->x.getPosition().range(player.getPosition())<requestedRange).collect(Collectors.toList()), (x,y)-> board.get(x,y) );
    }

    public void remove(Enemy enemy){
        enemies.remove(enemy);
        board.remove(enemy);
    }

    public boolean playLevel(){
        while(!player.isDead() && !enemies.isEmpty()){
            ui.print(player.getDescription());
            player.TakeTurn(ui.getAction());
            player.onGameTick(); // regen the ability
            for (Enemy e:enemies) {
                e.takeTurn(player.getPosition());
            }
            ui.print(board.toString());
        }
        return !player.isDead();
    }



}

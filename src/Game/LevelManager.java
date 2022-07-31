package Game;

import CLI.UserInterface;
import Tiles.Tile;
import Tiles.Units.Enemy;
import Tiles.Units.Player;

import java.util.List;
import java.util.stream.Collectors;

public class LevelManager {
    protected GameBoard board;
    protected List<Enemy> enemies;
    protected Player player;
    protected UserInterface ui;

    public LevelManager(){

    }

    public void SetLevelManager(Player player,Position playerPosition, GameBoard board, List<Enemy> enemies, UserInterface ui){
        this.board= board;
        this.enemies = enemies;
        this.player = player;
        this.ui=ui;
        initializeLevel(playerPosition);
    }

    public void initializeLevel(Position position){
        player.initilizeOnLevel(position, (x,y)-> this.get(x,y), (requestedRange)->enemies.stream().filter(x->x.getPosition().range(player.getPosition())<requestedRange).collect(Collectors.toList()));
    }

    public void remove(Enemy enemy){
        enemies.remove(enemy);
        board.remove(enemy);
    }

    public Tile get(int x, int y){
        return board.get(x,y);
    }

    public boolean playLevel(){
        while(!player.isDead() && !enemies.isEmpty()){
            ui.print(board.toString());
            ui.print(player.getDescription());
            player.TakeTurn(ui.getAction());
            player.onGameTick(); // regen the ability
            for (Enemy e:enemies) {
                e.takeTurn(player.getPosition());
            }
        }
        ui.print(board.toString());
        return !player.isDead();
    }



}

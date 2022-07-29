package Game;

import CLI.UserInterface;
import Tiles.Empty;
import Tiles.Tile;
import Tiles.Units.Enemy;
import Tiles.Units.Player;
import Tiles.Wall;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameManager {
    private UserInterface ui;
    private TileFactory tileFactory;
    private List<List<String>> levelsList;
    private Player player;


    public GameManager(String path){
        ui = new UserInterface();
        tileFactory = new TileFactory(ui);
        player = tileFactory.producePlayer(ui.getPlayerChoice()-1);
        levelsList = new ArrayList<>();
        buildLevels(path);
    }

    public List<String> readAllLines(String path) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(path));
        }
        catch (IOException e) {
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
        }
        return lines;
    }

    public void buildLevels(String path){
        int level = 1;
        String filePath = path + "\\level" + level + ".txt";
        File file = new File(filePath);
        while (file.exists()){
            levelsList.add(readAllLines(filePath));
            level++;
            filePath = path + "\\level" + level + ".txt";
            file = new File(filePath);
        }
    }

    public LevelManager startLevel(List<String> level){
        List<Tile> tiles = new ArrayList<Tile>();
        List<Enemy> enemies = new ArrayList<Enemy>();
        Position playerPosition = new Position(0,0);
        for (int i = 0; i < level.size(); i++){
            for (int j = 0; j < level.get(0).length(); j++){
                switch (level.get(i).charAt(j)){
                    case '.':
                        Empty empty = tileFactory.produceEmpty(new Position(i, j));
                        tiles.add(empty);
                        break;
                    case '#':
                        Wall wall = tileFactory.produceWall(new Position(i, j));
                        tiles.add(wall);
                        break;
                    case '@':
                        playerPosition = new Position(i, j);
                        tiles.add(player);
                        break;
                    default:
                        Enemy enemy = tileFactory.produceEnemy(level.get(i).charAt(j), new Position(i, j));
                        tiles.add(enemy);
                        enemies.add(enemy);
                        break;
                }
            }
        }

        return new LevelManager(player, playerPosition, new GameBoard(tiles), enemies, ui);
    }

    public void playGame(){
        boolean isAlive = true;
        for (int i = 0; i < levelsList.size() && isAlive; i++){
            LevelManager level = startLevel(levelsList.get(i));
            isAlive = level.playLevel();
        }
    }
}

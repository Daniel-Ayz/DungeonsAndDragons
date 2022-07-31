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
        ui.print(String.format("You have selected: %s", player.getName()));
        tileFactory.setPlayer(player);
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

        LevelManager levelManager=new LevelManager();
        tileFactory.setLevelManager(levelManager);

        for (int y = 0; y < level.size(); y++){
            for (int x = 0; x < level.get(0).length(); x++){
                switch (level.get(y).charAt(x)){
                    case '.':
                        Empty empty = tileFactory.produceEmpty(new Position(x, y));
                        tiles.add(empty);
                        break;
                    case '#':
                        Wall wall = tileFactory.produceWall(new Position(x, y));
                        tiles.add(wall);
                        break;
                    case '@':
                        playerPosition = new Position(x, y);
                        tiles.add(player);
                        break;
                    default:
                        Enemy enemy = tileFactory.produceEnemy(level.get(y).charAt(x), new Position(x, y));
                        tiles.add(enemy);
                        enemies.add(enemy);
                        break;
                }
            }
        }

        levelManager.SetLevelManager(player, playerPosition, new GameBoard(tiles), enemies, ui);
        return levelManager;
    }

    public void playGame(){
        boolean isAlive = true;
        for (int i = 0; i < levelsList.size() && isAlive; i++){
            LevelManager level = startLevel(levelsList.get(i));
            isAlive = level.playLevel();
        }
    }
}

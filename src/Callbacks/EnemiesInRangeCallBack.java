package Callbacks;

import Tiles.Units.Enemy;

import java.util.List;

public interface EnemiesInRangeCallBack {
    List<Enemy> getEnemies(int range);
}

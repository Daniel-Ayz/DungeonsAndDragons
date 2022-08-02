package Tiles;

import java.util.Random;
import java.util.Scanner;

public class RandomGenerator implements Generator{
    @Override
    public int generate(int x){
        Random random = new Random();
        return random.nextInt(x);
    }
}

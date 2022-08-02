package Tiles;

public class ConstantGenerator implements Generator{
    @Override
    public int generate(int x) {
        return x/2;
    }
}

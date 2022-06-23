package Business;

public class Position {
    private int x;
    private int y;

    protected Position(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int compareTo(Position position) {
        return Range.range(this,position);
    }
}

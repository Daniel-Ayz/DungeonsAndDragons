package Game;

public class Position implements Comparable<Position> {
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
        if(this.getX() == position.getX() && this.getY() == position.getY())
            return 0;
        if(this.x < position.getX() || (this.x == position.getX() && this.y < position.getY()))
            return -1;
        else
            return 1;
    }

    protected int range(Position position){
        return (int) Math.sqrt(Math.pow(this.getX()-position.getX(),2)+Math.pow(this.getY()-position.getY(),2));
    }

    protected boolean equals(Position p){
        return this.getX()==p.getX() && this.getY()==p.getY();
    }

    protected boolean samePosition(int x, int y){
        return this.getX()==x && this.getY()==y;
    }
}

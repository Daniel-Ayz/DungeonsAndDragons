package Game;

public class Position implements Comparable<Position> {
    private int x;
    private int y;

    public Position(int x, int y){
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
        if(this.getY() < position.getY() || (this.getY() == position.getY() && this.getX() < position.getX()))
            return -1;
        else
            return 1;
    }

    public int range(Position position){
        return (int) Math.sqrt(Math.pow(this.getX()-position.getX(),2)+Math.pow(this.getY()-position.getY(),2));
    }

    @Override
    public boolean equals(Object p){
        if (!(p instanceof Position))
            return false;
        return this.getX()==((Position)p).getX() && this.getY()==((Position)p).getY();
    }

    protected boolean samePosition(int x, int y){
        return this.getX()==x && this.getY()==y;
    }
}

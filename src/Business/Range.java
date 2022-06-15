package Business;

public final class Range {
    public static int range(Position p1, Position p2){
        return (int) Math.sqrt(Math.pow(p1.getX()-p2.getX(),2)+Math.pow(p1.getY()-p2.getY(),2));
    }
}

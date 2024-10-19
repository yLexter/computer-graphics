package geomtry.points;

public class Point2D {

    public double x, y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void updatePoint(Point2D point) {
        this.x = point.x;
        this.y = point.y;
    }

    @Override
    public String toString() {
       return "(" + x + ", " + y + ")";
    }

}

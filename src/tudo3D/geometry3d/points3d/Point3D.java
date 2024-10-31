package tudo3D.geometry3d.points3d;

public class Point3D {
    public double x, y, z;

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void updatePoint(Point3D point) {
        this.x = point.x;
        this.y = point.y;
        this.z = point.z;
    }

    @Override
    public String toString() {
       return "(" + x + ", " + y + ", " + z + ")";
    }
}

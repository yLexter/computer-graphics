package tudo3D.transformations3d;

import tudo3D.geometry3d.points3d.Point3D;

public class Translation3D {
    private double tx, ty, tz;

    // Construtor que define os valores de translação
    public Translation3D(double tx, double ty, double tz) {
        this.tx = tx;
        this.ty = ty;
        this.tz = tz;
    }

    // Método para aplicar a translação a um ponto e retornar o ponto resultante
    public Point3D apply(Point3D point) {
        double x = point.getX() + tx;
        double y = point.getY() + ty;
        double z = point.getZ() + tz;
        return new Point3D(x, y, z);
    }

    // Getters para acessar os valores de translação
    public double getTx() {
        return tx;
    }

    public double getTy() {
        return ty;
    }

    public double getTz() {
        return tz;
    }

    // toString para exibir a transformação
    @Override
    public String toString() {
        return "Translation3D(" + tx + ", " + ty + ", " + tz + ")";
    }
}

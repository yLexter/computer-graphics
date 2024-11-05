package tudo3D.transformations3d;

import tudo3D.geometry3d.points3d.Point3D;

import java.util.function.Consumer;

public abstract class BaseLine3D {
    protected Consumer<Point3D> callback;

    public BaseLine3D(Consumer<Point3D> callback) {
        this.callback = callback;
    }

    public BaseLine3D() {}

    // Método abstrato para desenhar uma linha entre dois pontos 3D
    public abstract void desenhaLinha(Point3D start, Point3D end);

    // Método para definir o callback caso não seja passado no construtor
    public void setCallback(Consumer<Point3D> callback) {
        this.callback = callback;
    }

    public Consumer<Point3D> getCallback() {
        return callback;
    }

}

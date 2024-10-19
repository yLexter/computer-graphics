package primitives;

import geometry.points.Point2D;

import java.util.function.Consumer;

public abstract class BasePrimitives {

    protected Consumer<Point2D> callback;

    public BasePrimitives(Consumer<Point2D> callback) {
        this.callback = callback;
    }

    public abstract void drawFigure();

}

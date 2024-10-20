package primitives.bases;


import geometry.points.Point2D;

import java.util.function.Consumer;

public abstract class BaseEllipse extends BasePrimitives {

    public BaseEllipse(Consumer<Point2D> callback) {
        super(callback);
    }

    public BaseEllipse() {}

    public abstract void drawEllipse(int a, int b);

}

package primitives;

import geomtry.points.Point2D;
import utils.BasePrimitives;
import geomtry.planeCartesians.CartesianPlane2D;

import java.util.function.Consumer;

public class TrigometricCircle extends BasePrimitives {

    public TrigometricCircle(Consumer<Point2D> callback) {
        super(callback);
    }

    public void drawCircle(int radius) {

        for (int angle = 0; angle < 360; angle++) {
            double radians = Math.toRadians(angle);
            double x = (radius * Math.cos(radians));
            double y = (radius * Math.sin(radians));

            callback.accept(new Point2D(x, y));
        }
    }

}

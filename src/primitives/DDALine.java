package primitives;

import geomtry.points.Point2D;
import pixel.Coordinates;
import utils.BasePrimitives;
import geomtry.planeCartesians.CartesianPlane2D;


import java.util.function.Consumer;

public class DDALine extends BasePrimitives {

    public DDALine(Consumer<Point2D> callback) {
        super(callback);
    }

    public void drawLine(Point2D start, Point2D end) {
        double x1 = start.x;
        double y1 = start.y;
        double x2 = end.x;
        double y2 = end.y;

        double dx = x2 - x1;
        double dy = y2 - y1;

        int steps = (int) Math.max(Math.abs(dx), Math.abs(dy));

        double xIncrement = dx / steps;
        double yIncrement = dy / steps;

        double x = x1;
        double y = y1;

        for (int i = 0; i <= steps; i++) {
            callback.accept(new Point2D(x, y));
            x += xIncrement;
            y += yIncrement;
        }
    }
}


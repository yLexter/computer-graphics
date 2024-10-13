package utils;

import geomtry.planeCartesians.CartesianPlane2D;
import geomtry.points.Point2D;

import java.awt.*;
import java.util.function.Consumer;

public class BasePrimitives {

    protected Consumer<Point2D> callback;

    public BasePrimitives(Consumer<Point2D> callback) {
        this.callback = callback;
    }

}

package transformations2d;

import geomtry.points.Point2D;
import view.utils.Matrix;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Rotation {

    public static Point2D rotatePoint(Point2D point, double angle) {
        double[][] pointHomogeneous = new double[][] {
                { point.x, point.y, 1 },
        };

        double[][] matrix = getMatrixRotation(angle);
        double[][] result = Matrix.multiply(pointHomogeneous, matrix);

        return new Point2D(
                result[0][0],
                result[0][1]
        );
    }

    public static double[][] getMatrixRotation(double angle) {
        double radians = Math.toRadians(angle);

        return new double[][] {
                { Math.cos(radians), -Math.sin(radians), 0 },
                { Math.sin(radians), Math.cos(radians), 0 },
                { 0, 0, 1 }
        };
    }



}

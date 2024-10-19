package geomtry.figures;

import geomtry.points.Point2D;
import primitives.MidpointCircle;

import java.awt.*;
import java.util.List;

public class Circle extends BaseFigure {

    private int radius;

    public Circle(int radius) {
        this.radius = radius;

        generatePoints();
    }

    public void generatePoints() {
        MidpointCircle midpointCircle = new MidpointCircle(
                (point2D -> this.points.add(point2D))
        );

        midpointCircle.drawCircle(radius);
    }

    @Override
    public String getID() {
        return String.format("Circulo [%d]", radius);
    }

    @Override
    public int getColor() {
        return Color.GREEN.getRGB();
    }

}

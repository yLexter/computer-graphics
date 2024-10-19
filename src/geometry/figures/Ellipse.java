package geomtry.figures;

import primitives.MidpointElipse;

import java.awt.*;

public class Ellipse extends BaseFigure {

    private int axisA, axisB;

    public Ellipse(int axisA, int axisB) {
        this.axisA = axisA;
        this.axisB = axisB;

        generatePoints();
    }

    public void generatePoints() {
        MidpointElipse midpointElipse = new MidpointElipse(
              point2D -> this.points.add(point2D)
        );

        midpointElipse.drawElipse(axisA, axisB);
    }

    @Override
    public String getID() {
        return String.format("Elipse [A: %d, B: %d]", axisA, axisB);
    }

    @Override
    public int getColor() {
        return Color.BLUE.getRGB();
    }


}

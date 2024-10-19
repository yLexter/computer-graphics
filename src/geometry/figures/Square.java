package geomtry.figures;

import geomtry.points.Point2D;
import primitives.MidpointLine;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Square extends BaseFigure {

    private Point2D[] vertices = new Point2D[4];

    public Square(Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
        vertices[0] = p1;
        vertices[1] = p2;
        vertices[2] = p3;
        vertices[3] = p4;

        generatePoints();
    }

    private void generatePoints() {
        MidpointLine midpointLine = new MidpointLine(
            (point2D -> this.points.add(point2D))
        );

        for (int i = 0; i < 4; i++) {
            Point2D start = vertices[i];
            Point2D end = vertices[(i + 1) % 4];
            midpointLine.desenhaLinha(start, end);
        }

    }

    public void setPoint1(Point2D p1) {
        vertices[0] = p1;
    }

    public void setPoint2(Point2D p2) {
        vertices[1] = p2;
    }

    public void setPoint3(Point2D p3) {
        vertices[2] = p3;
    }

    public void setPoint4(Point2D p4) {
        vertices[3] = p4;
    }

    public Point2D getPoint1() {
        return vertices[0];
    }

    public Point2D getPoint2() {
        return vertices[1];
    }

    public Point2D getPoint3() {
        return vertices[2];
    }

    public Point2D getPoint4() {
        return vertices[3];
    }


    @Override
    public String getID() {
        return String.format(
            "Quadrado [%s, %s, %s, %s]",
            vertices[0],
            vertices[1],
            vertices[2],
            vertices[3]
        );
    }



}

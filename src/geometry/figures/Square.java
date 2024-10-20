package geometry.figures;

import geometry.points.Point2D;
import primitives.MidpointLine;
import primitives.bases.BaseLine;

public class Square extends BaseFigure {

    private Point2D[] vertices = new Point2D[4];

    private BaseLine lineAlgorithm;

    public Square(Point2D[] vertices, BaseLine lineAlgorithm) {
        this.vertices = vertices;
        this.lineAlgorithm = lineAlgorithm;

        this.generatePoints();
    }

    public void generatePoints() {
        lineAlgorithm.setCallback(point2D -> this.points.add(point2D));

        for (int i = 0; i < 4; i++) {
            Point2D start = vertices[i];
            Point2D end = vertices[(i + 1) % 4];
            lineAlgorithm.drawLine(start, end);
        }

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

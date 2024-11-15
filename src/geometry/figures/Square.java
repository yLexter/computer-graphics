package geometry.figures;

import geometry.points.Point2D;
import primitives.bases.BaseLine;

import java.util.function.Consumer;

public class Square extends BaseFigure {

    private Point2D[] vertices;
    private BaseLine lineAlgorithm;
    public String id;

    public Square(Point2D[] vertices, BaseLine lineAlgorithm) {
        this.vertices = vertices;
        this.lineAlgorithm = lineAlgorithm;
        this.id = String.format(
                "Quadrado [%s, %s, %s, %s]",
                vertices[0],
                vertices[1],
                vertices[2],
                vertices[3]
        );
        this.generatePoints();
    }

    @Override
    public void getVertex(Consumer<Point2D> callback) {
        for (int i = 0; i < 4; i++) {
            callback.accept(this.vertices[i]);
        }
    }

    public void generatePoints() {
        lineAlgorithm.setCallback(point2D -> this.points.add(point2D));

        for (int i = 0; i < 4; i++) {
            Point2D start = vertices[i];
            Point2D end = vertices[(i + 1) % 4];
            lineAlgorithm.desenhaLinha(start, end);
        }
    }

    @Override
    public void iterateToPoints(Consumer<Point2D> callback) {
        for (Point2D point : points) {
            callback.accept(point);
        }
    }

    @Override
    public String getID() {
        return this.id;
    }
}

package geometry.planeCartesians.windowViewport;

import geometry.planeCartesians.bases.BaseCartesianPlane;
import geometry.planeCartesians.bases.BaseCartesianPlane2D;
import geometry.points.Point2D;
import utils.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;


public class WindowViewport extends BaseCartesianPlane2D {
    private Window window;
    private Viewport viewport;

    public WindowViewport() {
        super(Constants.WIDTH_CARTESIAN_PLANE, Constants.HEIGHT_CARTESIAN_PLANE);
        this.window =  new Window(0, width, 0, height);
        this.viewport = new Viewport(40, 300, 40, 300);

        drawCartesianPlane();
    }

    private Point2D transformToViewport(Point2D point) {
        double xWindow = point.x;
        double yWindow = point.y;

        // Transformação de coordenadas
        double screenX = viewport.xMin + ((xWindow - window.xMin) * (viewport.xMax - viewport.xMin)) / (window.xMax - window.xMin);
        double screenY = viewport.yMax - ((yWindow - window.yMin) * (viewport.yMax - viewport.yMin)) / (window.yMax - window.yMin);

        return new Point2D(screenX, screenY);
    }

    @Override
    public void setPixel(Point2D point, int rgb) {
        int screenX = (int) (point.x + image.getWidth() / 2);
        int screenY = (int) (image.getHeight() / 2 - point.y);

        if (screenX >= 0 && screenX < image.getWidth() && screenY >= 0 && screenY < image.getHeight()) {
            image.setRGB(screenX, screenY, rgb);
        }
    }

    @Override
    public void drawCartesianPlane() {

        super.drawCartesianPlane();

        for (int x = viewport.xMin; x < viewport.xMax; x++) {
            setPixel(new Point2D(x, viewport.yMin), Constants.COLOR_LINES_CARTESIAN_PLANE); // Linha inferior da viewport
            setPixel(new Point2D(x, viewport.yMax), Constants.COLOR_LINES_CARTESIAN_PLANE); // Linha superior da viewport
        }

        for (int y = viewport.yMin; y < viewport.yMax; y++) {
            setPixel(new Point2D(viewport.xMin, y), Constants.COLOR_LINES_CARTESIAN_PLANE); // Linha esquerda da viewport
            setPixel(new Point2D(viewport.xMax, y), Constants.COLOR_LINES_CARTESIAN_PLANE); // Linha direita da viewport
        }

        repaint();
    }

    @Override
    public BaseCartesianPlane2D reset() {
        return new WindowViewport();
    }

    @Override
    public void clearCartesianPlane() {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.drawCartesianPlane();
    }

}

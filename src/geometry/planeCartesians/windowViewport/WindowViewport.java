package geometry.planeCartesians;

import geometry.points.Point2D;
import utils.Constants;

class Window {
    public double xMin, xMax, yMin, yMax;

    public Window(double xMin, double xMax, double yMin, double yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    public double getXMin() {
        return xMin;
    }

    public double getXMax() {
        return xMax;
    }

    public double getYMin() {
        return yMin;
    }

    public double getYMax() {
        return yMax;
    }
}

class Viewport {
    public int xMin, xMax, yMin, yMax;

    public Viewport(int xMin, int xMax, int yMin, int yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    public int getXMin() {
        return xMin;
    }

    public int getXMax() {
        return xMax;
    }

    public int getYMin() {
        return yMin;
    }

    public int getYMax() {
        return yMax;
    }
}

class CartesianPlane2DWithWindow extends CartesianPlane2D {

    private Window window;
    private Viewport viewport;

    public CartesianPlane2DWithWindow(Window window, Viewport viewport, int width, int height) {
        super(width, height);
        this.window = window;
        this.viewport = viewport;
    }

    private Point2D transformToViewport(Point2D point) {
        double xWindow = point.x;
        double yWindow = point.y;

        // Transformação de coordenadas
        double screenX = viewport.xMin + ((xWindow - window.xMin) * (viewport.xMax - viewport.xMin)) / (window.xMax - window.xMin);
        double screenY = viewport.yMax - ((yWindow - window.yMin) * (viewport.yMax - viewport.yMin)) / (window.yMax - window.yMin); // Inverte o eixo Y

        return new Point2D(screenX, screenY);
    }

    @Override
    public void setPixel(Point2D point, int rgb) {
        Point2D viewportPoint = transformToViewport(point);

        int screenX = (int) viewportPoint.x;
        int screenY = (int) viewportPoint.y;

        if (screenX >= 0 && screenX < image.getWidth() && screenY >= 0 && screenY < image.getHeight()) {
            image.setRGB(screenX, screenY, rgb);
        }
    }

    @Override
    public void drawCartesianPlane() {
        super.drawCartesianPlane();

        for (int x = viewport.xMin; x < viewport.xMax; x++) {
            image.setRGB(x, viewport.yMin, Constants.COLOR_LINES_CARTESIAN_PLANE); // Linha inferior da viewport
            image.setRGB(x, viewport.yMax, Constants.COLOR_LINES_CARTESIAN_PLANE); // Linha superior da viewport
        }

        for (int y = viewport.yMin; y < viewport.yMax; y++) {
            image.setRGB(viewport.xMin, y, Constants.COLOR_LINES_CARTESIAN_PLANE); // Linha esquerda da viewport
            image.setRGB(viewport.xMax, y, Constants.COLOR_LINES_CARTESIAN_PLANE); // Linha direita da viewport
        }

        repaint();
    }


}

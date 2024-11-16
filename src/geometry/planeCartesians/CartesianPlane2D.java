package geometry.planeCartesians;

import clipping.CohenSutherlandLineClipper;
import geometry.planeCartesians.bases.BaseCartesianPlane2D;
import geometry.points.Point2D;
import utils.Constants;

import java.awt.image.BufferedImage;

public class CartesianPlane2D extends BaseCartesianPlane2D {

    private CohenSutherlandLineClipper clipper; // Instância do recorte de linha

    public CartesianPlane2D() {
        super(Constants.WIDTH_CARTESIAN_PLANE, Constants.HEIGHT_CARTESIAN_PLANE);
        clipper = new CohenSutherlandLineClipper(-50, -50, 50, 50); // Define a janela de recorte (exemplo)
        drawCartesianPlane();
    }

    @Override
    public void clearCartesianPlane() {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.drawCartesianPlane();
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
    public int getPixel(int x, int y) {
        int screenX = x + image.getWidth() / 2;
        int screenY = image.getHeight() / 2 - y;

        if (screenX >= 0 && screenX < image.getWidth() && screenY >= 0 && screenY < image.getHeight()) {
            return image.getRGB(screenX, screenY);
        }
        return Constants.BACKGROUND_CARTESIAN_PLANE;
    }

    // Método para desenhar linhas recortadas
    public void drawClippedLine(Point2D p1, Point2D p2, int rgb) {
        Point2D[] clippedPoints = clipper.clipLine((int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y);

        if (clippedPoints != null) {
            drawLine(clippedPoints[0], clippedPoints[1], rgb);
        }
    }

    // Método para desenhar uma linha entre dois pontos (ex.: usando Bresenham)
    private void drawLine(Point2D p1, Point2D p2, int rgb) {
        int x1 = (int) p1.x;
        int y1 = (int) p1.y;
        int x2 = (int) p2.x;
        int y2 = (int) p2.y;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;

        int err = dx - dy;

        while (true) {
            setPixel(new Point2D(x1, y1), rgb);

            if (x1 == x2 && y1 == y2) {
                break;
            }

            int e2 = 2 * err;

            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }

            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
    }
}

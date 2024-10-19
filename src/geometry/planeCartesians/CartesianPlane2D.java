package geomtry.planeCartesians;

import geomtry.points.Point2D;
import utils.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CartesianPlane2D extends BaseCartesianPlane {

    protected BufferedImage image;

    protected int height;

    protected int width;

    public CartesianPlane2D(int width, int height) {
        this.height = height;
        this.width = width;
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        setSize(width, height);

        drawCartesianPlane();
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public CartesianPlane2D() {
        this.width = Constants.WIDTH_CARTESIAN_PLANE;
        this.height = Constants.HEIGHT_CARTESIAN_PLANE;

        setSize(width, height);

        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        drawCartesianPlane();
    }

    @Override
    public void drawCartesianPlane() {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, Constants.BACKGROUND_CARTESIAN_PLANE);
            }
        }

        for (int x = 0; x < width; x++) {
            image.setRGB(x, height / 2, Constants.COLOR_LINES_CARTESIAN_PLANE);
        }

        for (int y = 0; y < height; y++) {
            image.setRGB(width / 2, y, Constants.COLOR_LINES_CARTESIAN_PLANE);
        }

        repaint();
    }

    @Override
    public BaseCartesianPlane reset() {
        return new CartesianPlane2D();
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }



}

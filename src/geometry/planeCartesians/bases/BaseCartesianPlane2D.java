package geometry.planeCartesians.bases;

import geometry.points.Point2D;
import utils.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class BaseCartesianPlane2D extends BaseCartesianPlane {

    protected BufferedImage image;
    protected int height;
    protected int width;

    public BaseCartesianPlane2D(int width, int height) {
        this.height = height;
        this.width = width;
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        setSize(width, height);
    }

    @Override
    public abstract void setPixel(Point2D coordinates, int rgb);
    
    @Override
    public int getPixel(int x, int y) {
        int screenX = (int) (x + image.getWidth() / 2);
        int screenY = (int) (image.getHeight() / 2 - y);

        if (screenX >= 0 && screenX < image.getWidth() && screenY >= 0 && screenY < image.getHeight()) {
            return image.getRGB(screenX, screenY);
        }

        return Constants.BACKGROUND_CARTESIAN_PLANE; // Retorna a cor de fundo
    }

    @Override
    public BaseCartesianPlane reset() {
        return new BaseCartesianPlane2D(width, height) {
            @Override
            public void setPixel(Point2D coordinates, int rgb) {
                // Implementação padrão
            }

            @Override
            public void drawCartesianPlane() {
                // Implementação padrão
            }

            @Override
            public void clearCartesianPlane() {
                // Implementação padrão
            }
        };
    }

    @Override
    public void clearCartesianPlane() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, Constants.BACKGROUND_CARTESIAN_PLANE);
            }
        }
        repaint();
    }

    @Override
    public void drawCartesianPlane() {
        for (int x = 0; x < width; x++) {
            image.setRGB(x, height / 2, Constants.COLOR_LINES_CARTESIAN_PLANE);
        }

        for (int y = 0; y < height; y++) {
            image.setRGB(width / 2, y, Constants.COLOR_LINES_CARTESIAN_PLANE);
        }
        repaint();
    }
}

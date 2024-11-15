package geometry.planeCartesians;

import java.awt.image.BufferedImage;

import geometry.planeCartesians.bases.BaseCartesianPlane2D;
import geometry.points.Point2D;
import utils.Constants;


public class CartesianPlane2D extends BaseCartesianPlane2D {

    public CartesianPlane2D() {
        super(Constants.WIDTH_CARTESIAN_PLANE, Constants.HEIGHT_CARTESIAN_PLANE);
        drawCartesianPlane();
    }

    @Override
    public void setPixel(Point2D coordinates, int rgb) {
        int screenX = (int) (coordinates.x + image.getWidth() / 2);
        int screenY = (int) (image.getHeight() / 2 - coordinates.y);

        if (screenX >= 0 && screenX < image.getWidth() && screenY >= 0 && screenY < image.getHeight()) {
            image.setRGB(screenX, screenY, rgb);
        }
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
    
    public BufferedImage getImage() {
        return this.image;
    }

}


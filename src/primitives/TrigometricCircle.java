package primitives;

import utils.BasePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TrigometricCircle extends BasePanel {

    private int width, height;

    public TrigometricCircle(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.width = width;
        this.height = height;
    }

    public void drawCircle(int centerX, int centerY, int radius) {
        for (int angle = 0; angle < 360; angle++) {
            double radians = Math.toRadians(angle);
            int x = (int) (centerX + radius * Math.cos(radians));
            int y = (int) (centerY + radius * Math.sin(radians));
            setPixel(x, y, Color.RED.getRGB());
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

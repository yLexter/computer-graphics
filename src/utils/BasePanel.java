package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class BasePanel extends JPanel {
    protected BufferedImage image;

    protected void setPixel(int x, int y, int rgb) {
        if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
            image.setRGB(x, y, rgb);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

}

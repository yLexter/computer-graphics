package primitives;

import utils.BasePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DDALine extends BasePanel {

    public DDALine(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        drawLine(10, 10, 200, 100);
    }

    private void drawLine(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            setPixel(Math.round(x), Math.round(y), Color.BLUE.getRGB());
            x += xIncrement;
            y += yIncrement;
        }
    }

}

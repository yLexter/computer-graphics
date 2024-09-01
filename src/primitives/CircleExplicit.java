package primitives;

import utils.BasePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CircleExplicit extends BasePanel {

    public CircleExplicit(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        drawCircle(400, 300, 100); // Exemplo: círculo com centro (400, 300) e raio 100
    }

    private void drawCircle(int centerX, int centerY, int radius) {
        int rSquared = radius * radius;

        for (int x = centerX - radius; x <= centerX + radius; x++) {
            int dx = x - centerX;
            double dxSquared = dx * dx;
            double ySquared = rSquared - dxSquared;

            if (ySquared >= 0) {
                int y1 = (int) (centerY + Math.sqrt(ySquared));
                int y2 = (int) (centerY - Math.sqrt(ySquared));
                setPixel(x, y1, Color.RED.getRGB());
                setPixel(x, y2, Color.RED.getRGB());
            }
        }
    }


}


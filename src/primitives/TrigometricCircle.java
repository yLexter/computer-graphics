package primitives;

import utils.BasePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TrigometricCircle extends BasePanel {

    public TrigometricCircle(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        drawCircle(400, 300, 100); // Exemplo: círculo com centro (400, 300) e raio 100
    }

    private void drawCircle(int centerX, int centerY, int radius) {
        // Desenha a circunferência usando o método trigonométrico
        int diameter = radius * 2;
        for (int angle = 0; angle < 360; angle++) {
            double radians = Math.toRadians(angle);
            int x = (int) (centerX + radius * Math.cos(radians));
            int y = (int) (centerY + radius * Math.sin(radians));
            setPixel(x, y, Color.RED.getRGB());
        }
    }

}

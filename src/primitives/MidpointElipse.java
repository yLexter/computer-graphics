package primitives;

import utils.BasePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MidpointElipse extends BasePanel {

    private int width, height;

    public MidpointElipse(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.width = width;
        this.height = height;
        drawAxes(); // Desenhar eixos inicialmente
    }

    @Override
    public void setPixel(int x, int y, int color) {
        int screenX = x + image.getWidth() / 2;
        int screenY = image.getHeight() / 2 - y;

        if (screenX >= 0 && screenX < image.getWidth() && screenY >= 0 && screenY < image.getHeight()) {
            image.setRGB(screenX, screenY, color);
        }
    }

    // Algoritmo de ponto médio para desenhar a elipse
    public void drawElipse(int cx, int cy, int a, int b) {
        int x = 0;
        int y = b;
        int d1 = b * b - a * a * b + a * a / 4;
        int dx = 2 * b * b * x;
        int dy = 2 * a * a * y;

        plotEllipsePoints(cx, cy, x, y);

        // Região 1
        while (dx < dy) {
            x++;
            dx += 2 * b * b;
            if (d1 < 0) {
                d1 += dx + b * b;
            } else {
                y--;
                dy -= 2 * a * a;
                d1 += dx - dy + b * b;
            }
            plotEllipsePoints(cx, cy, x, y);
        }

        // Região 2
        double d2 = b * b * (x + 0.5) * (x + 0.5) + a * a * (y - 1) * (y - 1) - a * a * b * b;
        while (y > 0) {
            y--;
            dy -= 2 * a * a;
            if (d2 > 0) {
                d2 += a * a - dy;
            } else {
                x++;
                dx += 2 * b * b;
                d2 += a * a - dy + dx;
            }
            plotEllipsePoints(cx, cy, x, y);
        }
    }

    // Plota os pontos da elipse em todos os octantes
    private void plotEllipsePoints(int cx, int cy, int x, int y) {
        setPixel(cx + x, cy + y, Color.RED.getRGB());
        setPixel(cx - x, cy + y, Color.RED.getRGB());
        setPixel(cx + x, cy - y, Color.RED.getRGB());
        setPixel(cx - x, cy - y, Color.RED.getRGB());
    }

    // Desenhar eixos X e Y
    public void drawAxes() {
        // Desenhar o eixo X (horizontal)
        drawLine(-getWidth() / 2, 0, getWidth() / 2, 0);
        // Desenhar o eixo Y (vertical)
        drawLine(0, -getHeight() / 2, 0, getHeight() / 2);
    }

    private void drawLine(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;
        int err = dx - dy;

        while (true) {
            setPixel(x1, y1, Color.BLACK.getRGB());
            if (x1 == x2 && y1 == y2) break;
            int e2 = err * 2;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

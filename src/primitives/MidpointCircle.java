package primitives;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MidpointCircle extends JPanel {
    private BufferedImage image;

    public MidpointCircle(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        drawAxes(); // Desenhar eixos inicialmente
    }

    public void setPixel(int x, int y, int color) {
        int screenX = x + image.getWidth() / 2;
        int screenY = image.getHeight() / 2 - y;

        if (screenX >= 0 && screenX < image.getWidth() && screenY >= 0 && screenY < image.getHeight()) {
            image.setRGB(screenX, screenY, color);
        }
    }

    // Algoritmo de ponto médio para desenhar a circunferência
    public void drawCircle(int cx, int cy, int radius) {
        int x = 0;
        int y = radius;
        int d = 1 - radius;

        plotCirclePoints(cx, cy, x, y);

        while (x < y) {
            x++;
            if (d < 0) {
                d += 2 * x + 1;
            } else {
                y--;
                d += 2 * (x - y) + 1;
            }
            plotCirclePoints(cx, cy, x, y);
        }
    }

    // Plota os pontos da circunferência em todos os octantes
    private void plotCirclePoints(int cx, int cy, int x, int y) {
        setPixel(cx + x, cy + y, Color.RED.getRGB());
        setPixel(cx - x, cy + y, Color.RED.getRGB());
        setPixel(cx + x, cy - y, Color.RED.getRGB());
        setPixel(cx - x, cy - y, Color.RED.getRGB());
        setPixel(cx + y, cy + x, Color.RED.getRGB());
        setPixel(cx - y, cy + x, Color.RED.getRGB());
        setPixel(cx + y, cy - x, Color.RED.getRGB());
        setPixel(cx - y, cy - x, Color.RED.getRGB());
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mid-Point Circle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);

        MidpointCircle panel = new MidpointCircle(700, 700);
        frame.add(panel);
        frame.setVisible(true);

        panel.drawCircle(50, 0, 100); // Desenhar uma circunferência com raio 100
        panel.repaint(); // Atualizar a interface para exibir a circunferência
    }
}

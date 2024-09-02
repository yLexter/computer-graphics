package primitives;

import utils.BasePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MidpointLine extends BasePanel {

    public MidpointLine(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void setPixel(int x, int y, int color) {
        int screenX = x + image.getWidth() / 2;
        int screenY = image.getHeight() / 2 - y;

        if (screenX >= 0 && screenX < image.getWidth() && screenY >= 0 && screenY < image.getHeight()) {
            image.setRGB(screenX, screenY, color);
        }
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;
        int err = dx - dy;

        while (true) {
            setPixel(x1, y1, Color.RED.getRGB());
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

    public void drawAxes() {
        // Desenhar o eixo X (horizontal)
        drawLine(-getWidth() / 2, 0, getWidth() / 2, 0);
        // Desenhar o eixo Y (vertical)
        drawLine(0, -getHeight() / 2, 0, getHeight() / 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mid-Point Line");
        MidpointLine panel = new MidpointLine(700, 700);
        frame.add(panel);
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        panel.drawAxes(); // Desenhar eixos X e Y
        panel.drawLine(40, 20, 60, 40);   // Oitante 1
        panel.drawLine(20, 40, 40, 50);   // Oitante 2
        panel.drawLine(-40, 60, -20, 40);  // Oitante 3
        panel.drawLine(-60, 40, -40, 20);  // Oitante 4
        panel.drawLine(-60, -20, -40, -20);  // Oitante 5
        panel.drawLine(-20, -20, -20, -80);  // Oitante 6
        panel.drawLine(20, -40, 20, -180);  // Oitante 7
        panel.drawLine(40, -20, 80, -60);  // Oitante 8
        panel.repaint(); // Atualizar a interface para exibir os eixos
    }
}

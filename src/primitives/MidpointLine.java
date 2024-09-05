package primitives;

import utils.BasePanel;

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

    // Função para determinar o octante e desenhar a linha
    public void desenhaLinha(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;

        // Caso especial: Linha vertical (dx = 0)
        if (dx == 0) {
            desenhaLinhaVertical(x1, y1, y2);
            return;
        }

        // Caso especial: Linha horizontal (dy = 0)
        if (dy == 0) {
            desenhaLinhaHorizontal(x1, x2, y1);
            return;
        }

        // Identifica o octante baseado em dx e dy
        int octante = getOctante(dx, dy);

        // Aplica a lógica de acordo com o octante
        switch (octante) {
            case 1:
                desenhaOctante1(x1, y1, x2, y2);
                break;
            case 2:
                desenhaOctante2(x1, y1, x2, y2);
                break;
            case 3:
                desenhaOctante3(x1, y1, x2, y2);
                break;
            case 4:
                desenhaOctante4(x1, y1, x2, y2);
                break;
            case 5:
                desenhaOctante5(x1, y1, x2, y2);
                break;
            case 6:
                desenhaOctante6(x1, y1, x2, y2);
                break;
            case 7:
                desenhaOctante7(x1, y1, x2, y2);
                break;
            case 8:
                desenhaOctante8(x1, y1, x2, y2);
                break;
        }
    }

    // Função para desenhar uma linha vertical
    private void desenhaLinhaVertical(int x, int y1, int y2) {
        int yStart = Math.min(y1, y2);
        int yEnd = Math.max(y1, y2);

        for (int y = yStart; y <= yEnd; y++) {
            setPixel(x, y, Color.RED.getRGB());
        }
    }

    // Função para desenhar uma linha horizontal
    private void desenhaLinhaHorizontal(int x1, int x2, int y) {
        int xStart = Math.min(x1, x2);
        int xEnd = Math.max(x1, x2);

        for (int x = xStart; x <= xEnd; x++) {
            setPixel(x, y, Color.RED.getRGB());
        }
    }
    // Determina em qual octante a linha está
    private int getOctante(int dx, int dy) {
        if (Math.abs(dy) <= Math.abs(dx)) {
            if (dx > 0 && dy >= 0) return 1;  // Octante 1
            if (dx > 0 && dy < 0) return 8;   // Octante 8
            if (dx < 0 && dy >= 0) return 4;  // Octante 4
            if (dx < 0 && dy < 0) return 5;   // Octante 5
        } else {
            if (dx > 0 && dy > 0) return 2;   // Octante 2
            if (dx < 0 && dy > 0) return 3;   // Octante 3
            if (dx > 0 && dy < 0) return 7;   // Octante 7
            if (dx < 0 && dy < 0) return 6;   // Octante 6
        }
        return 1; // Octante padrão
    }

    // Implementações para cada octante
    private void desenhaOctante1(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int d = 2 * dy - dx;
        int y = y1;
        for (int x = x1; x <= x2; x++) {
            setPixel(x, y, Color.RED.getRGB());
            if (d > 0) {
                y++;
                d -= 2 * dx;
            }
            d += 2 * dy;
        }
    }

    private void desenhaOctante2(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int d = 2 * dx - dy;
        int x = x1;
        for (int y = y1; y <= y2; y++) {
            setPixel(x, y, Color.RED.getRGB());
            if (d > 0) {
                x++;
                d -= 2 * dy;
            }
            d += 2 * dx;
        }
    }

    private void desenhaOctante3(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int d = 2 * dx - dy;
        int x = x1;
        for (int y = y1; y <= y2; y++) {
            setPixel(x, y, Color.RED.getRGB());
            if (d > 0) {
                x--;
                d -= 2 * dy;
            }
            d += 2 * dx;
        }
    }

    private void desenhaOctante4(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int d = 2 * dy - dx;
        int y = y1;
        for (int x = x1; x >= x2; x--) {
            setPixel(x, y, Color.RED.getRGB());
            if (d > 0) {
                y++;
                d -= 2 * dx;
            }
            d += 2 * dy;
        }
    }

    private void desenhaOctante5(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int d = 2 * dy - dx;
        int y = y1;
        for (int x = x1; x >= x2; x--) {
            setPixel(x, y, Color.RED.getRGB());
            if (d > 0) {
                y--;
                d -= 2 * dx;
            }
            d += 2 * dy;
        }
    }


    private void desenhaOctante6(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int d = 2 * dx - dy;
        int x = x1;
        for (int y = y1; y >= y2; y--) {
            setPixel(x, y, Color.RED.getRGB());
            if (d > 0) {
                x--;
                d -= 2 * dy;
            }
            d += 2 * dx;
        }
    }


    private void desenhaOctante7(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int d = 2 * dx - dy;
        int x = x1;
        for (int y = y1; y >= y2; y--) {
            setPixel(x, y, Color.RED.getRGB());
            if (d > 0) {
                x++;
                d -= 2 * dy;
            }
            d += 2 * dx;
        }
    }


    private void desenhaOctante8(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int d = 2 * dy - dx;
        int y = y1;
        for (int x = x1; x <= x2; x++) {
            setPixel(x, y, Color.RED.getRGB());
            if (d > 0) {
                y--;
                d -= 2 * dx;
            }
            d += 2 * dy;
        }
    }

//    public void drawAxes() {
//        // Desenhar o eixo X (horizontal)
//        desenhaLinha(-getWidth() / 2, 0, getWidth() / 2, 0);
//        // Desenhar o eixo Y (vertical)
//        desenhaLinha(0, -getHeight() / 2, 0, getHeight() / 2);
//    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}

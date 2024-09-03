package primitives;

import utils.BasePanel;
import pixel.Coordinates;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DDALine extends BasePanel {

    private int width, height;

    public DDALine(int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.width = width;
        this.height = height;
    }

    public void desenhaLinha(int x1, int y1, int x2, int y2) {
        Coordinates coords = new Coordinates();
        Graphics g = this.image.getGraphics();
        g.setColor(Color.BLUE); // Define a cor do retângulo

        int xMin = -250;
        int xMax = 250;
        int yMin = -250;
        int yMax = 250;
        int coordenadaInicialX = -1;
        int coordenadaFinalX = 1;
        int coordenadaInicialY = -1;
        int coordenadaFinalY = 1;

        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            float[] coordsNdcP1 = coords.inpToNdc(x, xMin, xMax, y, yMin, yMax, coordenadaInicialX, coordenadaFinalX, coordenadaInicialY, coordenadaFinalY);
            
            float[] coordsDcP1 = coords.ndcToDc(coordsNdcP1[0], coordsNdcP1[1], this.width, this.height, coordenadaInicialX, coordenadaFinalX, coordenadaInicialY, coordenadaFinalY);

            int xPixel = Math.round(coordsDcP1[0]);
            int yPixel = Math.round(this.height - coordsDcP1[1]);

            // Desenhar um pequeno retângulo (que simula um pixel)
            setPixel(xPixel, yPixel, Color.red.getRGB()); // Define o tamanho do retângulo

            // Incrementa x e y após desenhar o pixel
            x += xIncrement;
            y += yIncrement;

        }
        
        g.dispose(); // Libera os recursos gráficos
    }
}

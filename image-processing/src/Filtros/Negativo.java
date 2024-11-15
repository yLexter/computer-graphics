package Filtros;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Negativo {

    public BufferedImage negativoFiltro(BufferedImage ImagemOriginal) {

        int maxPixel = 255; //valor maximo de cor
        int width = ImagemOriginal.getWidth();
        int height = ImagemOriginal.getHeight();
        BufferedImage resultImage = new BufferedImage(width, height, ImagemOriginal.getType());

        // Aplica a convolução na imagem original
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int pixelColor = ImagemOriginal.getRGB(x, y);
                int gray = pixelColor & 0xFF;  // Extrai a intensidade do canal de cinza

                //calcula o negativo
                int negGray = maxPixel - gray;


                // Define o novo valor do pixel na imagem resultante (escala de cinza)
                int newPixelColor = (negGray << 16) | (negGray << 8) | negGray;  // Forma o valor RGB com intensidade igual
                resultImage.setRGB(x, y, newPixelColor);
            }
        }

        return resultImage;

    }

}

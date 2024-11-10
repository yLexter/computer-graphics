package Filtros;

import java.awt.image.BufferedImage;

public class Convolucao {
    public static BufferedImage ConvolucaoCalculo(BufferedImage imagemOriginal, float[][] mascara) {

        BufferedImage paddedImage = ImagePadding.addZeroPadding(imagemOriginal);

        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage resultImage = new BufferedImage(width, height, imagemOriginal.getType());

        int mascaraSize = mascara.length;  // mascara quadrada 3x3
        int offset = mascaraSize / 2;  // Deslocamento para aplicar a máscara

        // Aplica a convolução na imagem original
        for (int y = offset; y < height + offset; y++) {
            for (int x = offset; x < width + offset; x++) {
                float somaGray = 0;

                // Aplica a máscara 3x3 na área ao redor do pixel
                for (int j = 0; j < mascaraSize; j++) {
                    for (int i = 0; i < mascaraSize; i++) {
                        int pixelColor = paddedImage.getRGB(x + i - offset, y + j - offset);

                        // Extrai a intensidade do canal único (escala de cinza)
                        int gray = pixelColor & 0xFF;

                        // Multiplica o valor do pixel pelo valor da máscara
                        somaGray += gray * mascara[i][j];
                    }
                }

                // Normaliza o valor final e garante que esteja no intervalo [0, 255]
                int gray = Math.min(Math.max((int) somaGray, 0), 255);

                // Define o novo valor do pixel na imagem resultante (escala de cinza)
                int newPixelColor = (gray << 16) | (gray << 8) | gray;  // Forma o valor RGB com intensidade igual
                resultImage.setRGB(x - offset, y - offset, newPixelColor);
            }
        }

        return resultImage;
    }
}

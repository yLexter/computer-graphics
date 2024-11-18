package Filtros;

import java.awt.image.BufferedImage;

public class HighBoost {

    // Fórmula: High Boost = ImagemOriginal + A * MN
    public BufferedImage HB1(BufferedImage imagemOriginal, BufferedImage MN, double A) {
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();

        BufferedImage resultado = new BufferedImage(width, height, imagemOriginal.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Pega os valores dos pixels
                int pixelOriginal = imagemOriginal.getRGB(x, y) & 0xFF;
                int pixelMN = MN.getRGB(x, y) & 0xFF;

                // Calcula o valor do High Boost
                int valorHB = (int) (pixelOriginal + A * pixelMN);

                // Limita o valor entre 0 e 255
                valorHB = Math.min(Math.max(valorHB, 0), 255);

                // Define a nova cor no resultado
                int novaCor = (valorHB << 16) | (valorHB << 8) | valorHB;
                resultado.setRGB(x, y, novaCor);
            }
        }

        return resultado;
    }

    public BufferedImage HB2(BufferedImage imagemOriginal, double A) {
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();

        // Calcula o valor de w
        int w = (int) (9 * A - 1);

        // Máscara do filtro High Boost (método 2)
        int[][] filtroHighBoost = {
                {-1, -1, -1},
                {-1,  w, -1},
                {-1, -1, -1}
        };

        // Adiciona padding à imagem para evitar problemas de borda
        BufferedImage paddedImage = ImagePadding.addZeroPadding3x3(imagemOriginal);

        // Cria uma nova imagem para o resultado
        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Aplica a máscara do filtro High Boost
        for (int y = 1; y < height + 1; y++) {
            for (int x = 1; x < width + 1; x++) {
                int gradiente = 0;

                // Aplica a máscara do filtro (3x3)
                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        int pixelColor = paddedImage.getRGB(x + i, y + j) & 0xFF; // Intensidade do pixel
                        gradiente += filtroHighBoost[j + 1][i + 1] * pixelColor;
                    }
                }

                // Limita o valor do gradiente para garantir que ele esteja entre 0 e 255
                gradiente = Math.min(Math.max(gradiente, 0), 255);

                // Define o novo valor do pixel na imagem resultante
                int newPixelColor = (gradiente << 16) | (gradiente << 8) | gradiente;
                resultImage.setRGB(x - 1, y - 1, newPixelColor); // Ajusta para o tamanho original
            }
        }

        return resultImage;
    }
}

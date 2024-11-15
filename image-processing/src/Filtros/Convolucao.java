package Filtros;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Convolucao {
    public static BufferedImage ConvolucaoMedia(BufferedImage imagemOriginal, float[][] mascara) {

        BufferedImage paddedImage = ImagePadding.addZeroPadding3x3(imagemOriginal);



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
    public static BufferedImage ConvolucaoMediana(BufferedImage imagemOriginal, int mascaraSize) {

        // Adiciona padding zero
        BufferedImage paddedImage = ImagePadding.addZeroPadding3x3(imagemOriginal);

        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage resultImage = new BufferedImage(width, height, imagemOriginal.getType());

        int offset = mascaraSize / 2;  // Deslocamento para aplicar a máscara

        // Aplica a convolução na imagem original
        for (int y = offset; y < height + offset; y++) {
            for (int x = offset; x < width + offset; x++) {
                int[] mascaraArray = new int[mascaraSize * mascaraSize];  // Array para armazenar valores de pixels

                // Preenche a máscara 3x3 com os valores ao redor do pixel
                int index = 0;
                for (int j = 0; j < mascaraSize; j++) {
                    for (int i = 0; i < mascaraSize; i++) {
                        int pixelColor = paddedImage.getRGB(x + i - offset, y + j - offset);
                        int gray = pixelColor & 0xFF;  // Extrai a intensidade do canal de cinza
                        mascaraArray[index++] = gray;   // Adiciona ao array
                    }
                }

                // Ordena o array e encontra o valor mediano
                Arrays.sort(mascaraArray);
                int gray = mascaraArray[mascaraArray.length / 2];  // Valor mediano

                // Define o novo valor do pixel na imagem resultante (escala de cinza)
                int newPixelColor = (gray << 16) | (gray << 8) | gray;  // Forma o valor RGB com intensidade igual
                resultImage.setRGB(x - offset, y - offset, newPixelColor);
            }
        }

        return resultImage;
    }

    public static BufferedImage ConvolucaoErosao(BufferedImage imagemOriginal, int dimensaoKernel) {

        BufferedImage paddedImage = null;

        // Inicializa a variável com base na dimensão do kernel
        if (dimensaoKernel == 3) {
            paddedImage = ImagePadding.addZeroPadding3x3(imagemOriginal);
        } else if (dimensaoKernel == 5) {
            paddedImage = ImagePadding.addZeroPadding5x5(imagemOriginal);
        } else if (dimensaoKernel == 7) {
            paddedImage = ImagePadding.addZeroPadding7x7(imagemOriginal);
        }


        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage resultImage = new BufferedImage(width, height, imagemOriginal.getType());

        int offset = dimensaoKernel / 2;  // Deslocamento para aplicar a máscara

        // Aplica a convolução na imagem original
        for (int y = offset; y < height + offset; y++) {
            for (int x = offset; x < width + offset; x++) {
                int minGray = 255;
                int[] mascaraArray = new int[dimensaoKernel * dimensaoKernel];  // Array para armazenar valores de pixels

                // Preenche a máscara 3x3 com os valores ao redor do pixel
                int index = 0;
                for (int j = 0; j < dimensaoKernel; j++) {
                    for (int i = 0; i < dimensaoKernel; i++) {

                        int pixelColor = paddedImage.getRGB(x + i - offset, y + j - offset);
                        int gray = pixelColor & 0xFF;  // Extrai a intensidade do canal de cinza
                        mascaraArray[index++] = gray;   // Adiciona ao array
                        minGray = Math.min(minGray, gray);
                    }
                }

                // Ordena o array e encontra o primeiro valor (o mais baixo)
                Arrays.sort(mascaraArray);
                int gray = mascaraArray[0];

                // Define o novo valor do pixel na imagem resultante (escala de cinza)
                int newPixelColor = (minGray << 16) | (minGray << 8) | minGray;  // Forma o valor RGB com intensidade igual
                resultImage.setRGB(x - offset, y - offset, newPixelColor);
            }
        }

        return resultImage;
    }

    public static BufferedImage ConvolucaoDilatacao(BufferedImage imagemOriginal, int dimensaoKernel) {

        BufferedImage paddedImage = null;

        // Inicializa a variável com base na dimensão do kernel
        if (dimensaoKernel == 3) {
            paddedImage = ImagePadding.addZeroPadding3x3(imagemOriginal);
        } else if (dimensaoKernel == 5) {
            paddedImage = ImagePadding.addZeroPadding5x5(imagemOriginal);
        } else if (dimensaoKernel == 7) {
            paddedImage = ImagePadding.addZeroPadding7x7(imagemOriginal);
        }


        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage resultImage = new BufferedImage(width, height, imagemOriginal.getType());

        int offset = dimensaoKernel / 2;  // Deslocamento para aplicar a máscara

        // Aplica a convolução na imagem original
        for (int y = offset; y < height + offset; y++) {
            for (int x = offset; x < width + offset; x++) {

                int maxGray = 0;
                int[] mascaraArray = new int[dimensaoKernel * dimensaoKernel];  // Array para armazenar valores de pixels

                // Preenche a máscara 3x3 com os valores ao redor do pixel
                int index = 0;
                for (int j = 0; j < dimensaoKernel; j++) {
                    for (int i = 0; i < dimensaoKernel; i++) {

                        int pixelColor = paddedImage.getRGB(x + i - offset, y + j - offset);
                        int gray = pixelColor & 0xFF;  // Extrai a intensidade do canal de cinza
                        maxGray = Math.max(maxGray, gray);
                    }
                }



                // Define o novo valor do pixel na imagem resultante (escala de cinza)
                int newPixelColor = (maxGray << 16) | (maxGray << 8) | maxGray;  // Forma o valor RGB com intensidade igual
                resultImage.setRGB(x - offset, y - offset, newPixelColor);
            }
        }

        return resultImage;
    }
}

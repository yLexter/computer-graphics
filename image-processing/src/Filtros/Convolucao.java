package Filtros;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import static java.util.Arrays.asList;

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


        // se for imagem em escala de cinza
        if (imagemOriginal.getType()==BufferedImage.TYPE_BYTE_GRAY) {
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


        // Para imagem binaria
        for (int y = offset; y < height + offset; y++) {
            for (int x = offset; x < width + offset; x++) {
                boolean isEroded = true; // Assume inicialmente que o pixel pode ser erodido (é branco)


                for (int j = 0; j < dimensaoKernel; j++) {
                    for (int i = 0; i < dimensaoKernel; i++) {
                        int pixelColor = paddedImage.getRGB(x + i - offset, y + j - offset) & 0xFF; // Extração do valor (0 ou 255)
                        if (pixelColor == 0) { // Se qualquer valor da máscara for preto
                            isEroded = false; // O pixel não pode ser erodido
                            break;
                        }
                    }
                    if (!isEroded) break; // Sai do loop externo se  encontrou um preto
                }

                // Define o valor do pixel na imagem resultante
                int newPixelColor = isEroded ? 0xFFFFFF : 0x000000; // Branco (1) ou Preto (0)
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

        // se for imagem em escala de cinza
        if (imagemOriginal.getType()==BufferedImage.TYPE_BYTE_GRAY){
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

        // Para imagem binaria
        for (int y = offset; y < height + offset; y++) {
            for (int x = offset; x < width + offset; x++) {
                boolean isDilated = false; // Assume inicialmente que o pixel é preto

                // Verifica os valores ao redor do pixel (aplicando a máscara)
                for (int j = 0; j < dimensaoKernel; j++) {
                    for (int i = 0; i < dimensaoKernel; i++) {
                        int pixelColor = paddedImage.getRGB(x + i - offset, y + j - offset) & 0xFF; // Extração do valor (0 ou 255)
                        if (pixelColor == 255) { // Se qualquer valor da máscara for branco
                            isDilated = true; // O pixel será branco
                            break;
                        }
                    }
                    if (isDilated) break; // Sai do loop externo se já encontrou um branco
                }

                // Define o valor do pixel na imagem resultante
                int newPixelColor = isDilated ? 0xFFFFFF : 0x000000; // Branco (1) ou Preto (0)
                resultImage.setRGB(x - offset, y - offset, newPixelColor);
            }
        }

        return resultImage;
    }

    public static BufferedImage ConvolucaoRoberts(BufferedImage imagemOriginal, int dimensaoKernel) {
        // Adiciona padding de zeros ao redor da imagem original
        BufferedImage paddedImage = ImagePadding.addZeroPadding3x3(imagemOriginal);

        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage resultImage = new BufferedImage(width, height, imagemOriginal.getType());

        int offset = dimensaoKernel / 2; // Deslocamento para aplicar a máscara

        // Verifica se a imagem está em escala de cinza
        if (imagemOriginal.getType() == BufferedImage.TYPE_BYTE_GRAY) {
            // Aplica a convolução usando o operador de Roberts
            for (int y = offset; y < height + offset; y++) {
                for (int x = offset; x < width + offset; x++) {
                    // Extrai os pixels relevantes para Roberts
                    int p1 = paddedImage.getRGB(x, y) & 0xFF;       // Pixel atual
                    int p2 = paddedImage.getRGB(x + 1, y) & 0xFF;   // Direita
                    int p3 = paddedImage.getRGB(x, y + 1) & 0xFF;   // Abaixo
                    int p4 = paddedImage.getRGB(x + 1, y + 1) & 0xFF; // Diagonal

                    // Calcula os gradientes
                    int gradienteX = p1 - p4; // Gx
                    int gradienteY = p2 - p3; // Gy

                    // Calcula a magnitude do gradiente
                    int magnitude = Math.abs(gradienteX) + Math.abs(gradienteY);

                    // Define o novo valor do pixel na imagem resultante (escala de cinza)
                    int newPixelColor = (magnitude << 16) | (magnitude << 8) | magnitude;
                    resultImage.setRGB(x - offset, y - offset, newPixelColor);
                }
            }
        }
        return resultImage;
    }
    public static BufferedImage ConvolucaoSobel(BufferedImage imagemOriginal) {
        // Adiciona padding de zeros ao redor da imagem original
        BufferedImage paddedImage = ImagePadding.addZeroPadding3x3(imagemOriginal);

        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage resultImage = new BufferedImage(width, height, imagemOriginal.getType());

        // Máscaras de Sobel
        int[][] sobelX = {
                {-1, -2, -1},
                { 0,  0,  0},
                { 1,  2,  1}

        };

        int[][] sobelY = {
                {-1, 0, 1},
                {-2, 0, 2},
                {-1, 0, 1}
        };

        // Verifica se a imagem está em escala de cinza
        if (imagemOriginal.getType() == BufferedImage.TYPE_BYTE_GRAY) {
            // Aplica a convolução usando o operador de Sobel
            for (int y = 1; y < height + 1; y++) { // Ignora bordas
                for (int x = 1; x < width + 1; x++) {
                    int gradienteX = 0;
                    int gradienteY = 0;

                    // Aplica as máscaras de Sobel (3x3)
                    for (int j = -1; j <= 1; j++) {
                        for (int i = -1; i <= 1; i++) {
                            int pixelColor = paddedImage.getRGB(x + i, y + j) & 0xFF; // Intensidade do pixel
                            gradienteX += sobelX[j + 1][i + 1] * pixelColor;
                            gradienteY += sobelY[j + 1][i + 1] * pixelColor;
                        }
                    }

                    // Calcula a magnitude do gradiente
                    int magnitude = Math.abs(gradienteX) + Math.abs(gradienteY);
                    // Define o novo valor do pixel na imagem resultante (escala de cinza)
                    int newPixelColor = (magnitude << 16) | (magnitude << 8) | magnitude;
                    resultImage.setRGB(x - 1, y - 1, newPixelColor); // Ajusta para o tamanho original
                }
            }
        }
        return resultImage;
    }

    public static BufferedImage ConvolucaoPrewitt(BufferedImage imagemOriginal) {
        // Adiciona padding de zeros ao redor da imagem original
        BufferedImage paddedImage = ImagePadding.addZeroPadding3x3(imagemOriginal);

        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage resultImage = new BufferedImage(width, height, imagemOriginal.getType());

        // Máscaras de Prewitt
        int[][] prewittX = {
                {-1, -1, -1},
                {0, 0, 0},
                {1, 1, 1}
        };

        int[][] prewittY = {
                {-1, 0, 1},
                { -1,  0,  1},
                { -1,  0,  1}
        };

        // Verifica se a imagem está em escala de cinza
        if (imagemOriginal.getType() == BufferedImage.TYPE_BYTE_GRAY) {
            // Aplica a convolução usando o operador de Sobel
            for (int y = 1; y < height + 1; y++) { // Ignora bordas
                for (int x = 1; x < width + 1; x++) {
                    int gradienteX = 0;
                    int gradienteY = 0;

                    // Aplica as máscaras de Sobel (3x3)
                    for (int j = -1; j <= 1; j++) {
                        for (int i = -1; i <= 1; i++) {
                            int pixelColor = paddedImage.getRGB(x + i, y + j) & 0xFF; // Intensidade do pixel
                            gradienteX += prewittX[j + 1][i + 1] * pixelColor;
                            gradienteY += prewittY[j + 1][i + 1] * pixelColor;
                        }
                    }

                    // Calcula a magnitude do gradiente
                    int magnitude = Math.abs(gradienteX) + Math.abs(gradienteY);
                    // Define o novo valor do pixel na imagem resultante (escala de cinza)
                    int newPixelColor = (magnitude << 16) | (magnitude << 8) | magnitude;
                    resultImage.setRGB(x - 1, y - 1, newPixelColor); // Ajusta para o tamanho original
                }
            }
        }
        return resultImage;
    }

    public static BufferedImage ConvolucaoPassaAlta(BufferedImage imagemOriginal) {
        // Adiciona padding de zeros ao redor da imagem original
        BufferedImage paddedImage = ImagePadding.addZeroPadding3x3(imagemOriginal);

        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage resultImage = new BufferedImage(width, height, imagemOriginal.getType());

        // Máscara do filtro passa-alta (com -1 nas bordas e 8 no centro)
        int[][] filtroPassaAlta = {
                {-1, -1, -1},
                {-1,  8, -1},
                {-1, -1, -1}
        };

        // Aplica o filtro passa-alta
        for (int y = 1; y < height + 1; y++) { // Ignora bordas
            for (int x = 1; x < width + 1; x++) {
                int gradiente = 0;

                // Aplica a máscara do filtro passa-alta (3x3)
                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        int pixelColor = paddedImage.getRGB(x + i, y + j) & 0xFF; // Intensidade do pixel
                        gradiente += filtroPassaAlta[j + 1][i + 1] * pixelColor;
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

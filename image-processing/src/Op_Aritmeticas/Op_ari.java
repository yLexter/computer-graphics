package Op_Aritmeticas;

import java.awt.image.BufferedImage;

public class Op_ari {

    // Método para somar duas imagens (binárias ou escala de cinza)
    public BufferedImage soma(BufferedImage img1, BufferedImage img2) {
        return processar(img1, img2, (p1, p2) -> {
            // Soma para escala de cinza ou binária
            if (isBinaryImage(img1)) {
                return p1 == 0xFFFFFF || p2 == 0xFFFFFF ? 0xFFFFFF : 0x000000; // Se binária, resultado binário
            }
            return Math.min(p1 + p2, 255); // Se escala de cinza, soma com limite 255
        });
    }

    // Método para subtrair duas imagens (binárias ou escala de cinza)
    public BufferedImage subtracao(BufferedImage img1, BufferedImage img2) {
        return processar(img1, img2, (p1, p2) -> {
            // Subtração para escala de cinza ou binária
            if (isBinaryImage(img1)) {
                return p1 == 0xFFFFFF && p2 == 0x000000 ? 0xFFFFFF : 0x000000; // Se binária, resultado binário
            }
            return Math.max(p1 - p2, 0); // Se escala de cinza, subtração com limite 0
        });
    }

    // Método para multiplicar duas imagens (binárias ou escala de cinza)
    public BufferedImage multiplicacao(BufferedImage img1, BufferedImage img2) {
        return processar(img1, img2, (p1, p2) -> {
            // Multiplicação para escala de cinza ou binária
            if (isBinaryImage(img1)) {
                return p1 == 0xFFFFFF && p2 == 0xFFFFFF ? 0xFFFFFF : 0x000000; // Se binária, resultado binário
            }
            return Math.min(p1 * p2 / 255, 255); // Se escala de cinza, multiplicação normalizada
        });
    }

    // Método para dividir duas imagens (binárias ou escala de cinza)
    public BufferedImage divisao(BufferedImage img1, BufferedImage img2) {
        return processar(img1, img2, (p1, p2) -> {
            // Divisão para escala de cinza ou binária
            if (isBinaryImage(img1)) {
                return p2 == 0x000000 ? 0xFFFFFF : (p1 == 0xFFFFFF && p2 == 0xFFFFFF ? 0xFFFFFF : 0x000000); // Se binária, resultado binário
            }
            return p2 == 0 ? 255 : Math.min(p1 * 255 / p2, 255); // Se escala de cinza, divisão normalizada
        });
    }

    // Método genérico para processar pixels de duas imagens com uma operação
    private BufferedImage processar(BufferedImage img1, BufferedImage img2, PixelOperation operacao) {
        int largura = Math.min(img1.getWidth(), img2.getWidth());
        int altura = Math.min(img1.getHeight(), img2.getHeight());
        BufferedImage resultado = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                int rgb1 = img1.getRGB(x, y);
                int rgb2 = img2.getRGB(x, y);

                // Verificar e obter o valor de intensidade de cinza ou binário
                int p1 = getGrayValue(rgb1, img1);
                int p2 = getGrayValue(rgb2, img2);

                int resultadoPixel = operacao.apply(p1, p2);

                // Converte o valor calculado de volta para a cor apropriada
                int corResultado = isBinaryImage(img1) ? (resultadoPixel == 0 ? 0x000000 : 0xFFFFFF) : (resultadoPixel << 16) | (resultadoPixel << 8) | resultadoPixel;
                resultado.setRGB(x, y, corResultado);
            }
        }

        return resultado;
    }

    // Função para extrair o valor de intensidade de cinza (0 a 255) de um pixel ou verificar se é binário
    private int getGrayValue(int rgb, BufferedImage img) {
        if (isBinaryImage(img)) {
            return (rgb & 0xFF) == 0 ? 0x000000 : 0xFFFFFF; // Se binária, 0 para preto, 1 para branco
        }
        // Para imagens escala de cinza, calcula o valor de intensidade
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;
        return (int) (0.299 * r + 0.587 * g + 0.114 * b); // Converte para escala de cinza
    }

    // Verificar se a imagem é binária (valores 0 ou 255 para cada pixel)
    private boolean isBinaryImage(BufferedImage img) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                int grayValue = (rgb & 0xFF); // Obtém o valor do pixel
                if (grayValue != 0x000000 && grayValue != 0xFFFFFF) {
                    return false; // Se qualquer valor de pixel não for 0 ou 255, não é binário
                }
            }
        }
        return true; // Todos os pixels são 0 ou 255, então a imagem é binária
    }

    @FunctionalInterface
    interface PixelOperation {
        int apply(int p1, int p2);
    }
}

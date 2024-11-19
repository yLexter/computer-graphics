package Trans_intensidade;

import java.awt.image.BufferedImage;

public class OperacoesIntensidade {

    // Transformação Logarítmica
    public BufferedImage transformacaoLogaritmica(BufferedImage imagemOriginal, double a) {
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage result = new BufferedImage(width, height, imagemOriginal.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int corOriginal = imagemOriginal.getRGB(x, y) & 0xFF;
                int novaCor = (int) (a * Math.log(1 + corOriginal));
                novaCor = Math.min(255, novaCor); // Limita a intensidade máxima.
                result.setRGB(x, y, (novaCor << 16) | (novaCor << 8) | novaCor);
            }
        }
        return result;
    }

    // Cálculo de transferência de intensidade geral
    public BufferedImage transferenciaIntensidadeGeral(BufferedImage imagemOriginal) {
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage result = new BufferedImage(width, height, imagemOriginal.getType());

        // Cálculo de média (w) e desvio padrão (sigma)
        double soma = 0;
        double somaQuadrados = 0;
        int numPixels = width * height;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int corOriginal = imagemOriginal.getRGB(x, y) & 0xFF; // Nível de cinza
                soma += corOriginal;
                somaQuadrados += corOriginal * corOriginal;
            }
        }

        double w = soma / numPixels; // Média dos níveis de cinza
        double variancia = (somaQuadrados / numPixels) - (w * w); // Variância
        double sigma = Math.sqrt(variancia); // Desvio padrão

        // Aplicação da função de transferência de intensidade
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int corOriginal = imagemOriginal.getRGB(x, y) & 0xFF; // Nível de cinza
                double s = 255 / (1 + Math.exp(-(corOriginal - w) / sigma)); // Fórmula
                int novaCor = (int) Math.round(s);
                result.setRGB(x, y, (novaCor << 16) | (novaCor << 8) | novaCor); // Grava a nova cor
            }
        }

        return result;
    }

    // Transferência de Faixa Dinâmica
    // Cálculo de transformação de faixa dinâmica
    public BufferedImage transferenciaFaixaDinamica(BufferedImage imagemOriginal, double w) {
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage result = new BufferedImage(width, height, imagemOriginal.getType());

        // Encontrar rmin e rmax
        int rMin = Integer.MAX_VALUE;
        int rMax = Integer.MIN_VALUE;

        // Encontrar os valores mínimos e máximos de intensidade na imagem
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int corOriginal = imagemOriginal.getRGB(x, y) & 0xFF; // Nível de cinza
                if (corOriginal < rMin) {
                    rMin = corOriginal;
                }
                if (corOriginal > rMax) {
                    rMax = corOriginal;
                }
            }
        }

        // Aplicar a transformação para cada pixel
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int corOriginal = imagemOriginal.getRGB(x, y) & 0xFF; // Nível de cinza
                // Transformação de faixa dinâmica
                double s = ((corOriginal - rMin) * w) / (rMax - rMin);
                int novaCor = (int) Math.round(s);
                result.setRGB(x, y, (novaCor << 16) | (novaCor << 8) | novaCor); // Grava a nova cor
            }
        }

        return result;
    }

    // Transferência Linear
    public BufferedImage transferenciaLinear(BufferedImage imagemOriginal, double a, double b) {
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage result = new BufferedImage(width, height, imagemOriginal.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int corOriginal = imagemOriginal.getRGB(x, y) & 0xFF;
                int novaCor = (int) (a * corOriginal + b);
                novaCor = Math.max(0, Math.min(255, novaCor)); // Limita ao intervalo [0, 255].
                result.setRGB(x, y, (novaCor << 16) | (novaCor << 8) | novaCor);
            }
        }
        return result;
    }
}

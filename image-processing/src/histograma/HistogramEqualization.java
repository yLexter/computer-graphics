package histograma;

import java.awt.image.BufferedImage;

public class HistogramEqualization {

    public BufferedImage equalizeHistogram(BufferedImage imagem) {
        HistogramEqualization equalizer = new HistogramEqualization();
        return equalizer.equalize(imagem);
    }

    public BufferedImage equalize(BufferedImage imagem) {
        int width = imagem.getWidth();
        int height = imagem.getHeight();
        BufferedImage output = new BufferedImage(width, height, imagem.getType());

        // Calcula o histograma
        int[] histograma = new int[256];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = imagem.getRaster().getSample(x, y, 0);
                histograma[pixel]++;
            }
        }

        // Calcula a função de distribuição acumulada (CDF)
        int[] cdf = new int[256];
        cdf[0] = histograma[0];
        for (int i = 1; i < 256; i++) {
            cdf[i] = cdf[i - 1] + histograma[i];
        }

        // Normaliza a CDF
        int cdfMin = 0;
        for (int value : cdf) {
            if (value > 0) {
                cdfMin = value;
                break;
            }
        }

        int numPixels = width * height;
        int[] lut = new int[256];
        for (int i = 0; i < 256; i++) {
            lut[i] = (cdf[i] - cdfMin) * 255 / (numPixels - cdfMin);
            lut[i] = Math.max(0, Math.min(255, lut[i])); // Garante valores válidos
        }

        // Aplica a LUT na imagem
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = imagem.getRaster().getSample(x, y, 0);
                int novoPixel = lut[pixel];
                output.getRaster().setSample(x, y, 0, novoPixel);
            }
        }

        return output;
    }

    public double[] computeHistogram(BufferedImage imagem) {
        int width = imagem.getWidth();
        int height = imagem.getHeight();
        double[] histograma = new double[256];

        // Calcula o histograma
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = imagem.getRaster().getSample(x, y, 0);
                histograma[pixel]++;
            }
        }
        return histograma;
    }
}

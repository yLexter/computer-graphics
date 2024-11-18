package Op_Logicos;

import java.awt.image.BufferedImage;

public class Op_log {

    // Método para AND lógico entre duas imagens
    public BufferedImage and(BufferedImage img1, BufferedImage img2) {
        return processar(img1, img2, (p1, p2) -> p1 & p2);
    }

    // Método para OR lógico entre duas imagens
    public BufferedImage or(BufferedImage img1, BufferedImage img2) {
        return processar(img1, img2, (p1, p2) -> p1 | p2);
    }

    // Método para XOR lógico entre duas imagens
    public BufferedImage xor(BufferedImage img1, BufferedImage img2) {
        return processar(img1, img2, (p1, p2) -> p1 ^ p2);
    }

    // Método genérico para processar pixels de duas imagens com uma operação lógica
    private BufferedImage processar(BufferedImage img1, BufferedImage img2, PixelOperation operacao) {
        int largura = Math.min(img1.getWidth(), img2.getWidth());
        int altura = Math.min(img1.getHeight(), img2.getHeight());
        BufferedImage resultado = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                int rgb1 = img1.getRGB(x, y);
                int rgb2 = img2.getRGB(x, y);

                int r1 = (rgb1 >> 16) & 0xFF;
                int g1 = (rgb1 >> 8) & 0xFF;
                int b1 = rgb1 & 0xFF;

                int r2 = (rgb2 >> 16) & 0xFF;
                int g2 = (rgb2 >> 8) & 0xFF;
                int b2 = rgb2 & 0xFF;

                int r = operacao.apply(r1, r2);
                int g = operacao.apply(g1, g2);
                int b = operacao.apply(b1, b2);

                int corResultado = (r << 16) | (g << 8) | b;
                resultado.setRGB(x, y, corResultado);
            }
        }

        return resultado;
    }

    @FunctionalInterface
    interface PixelOperation {
        int apply(int p1, int p2);
    }
}

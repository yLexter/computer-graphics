package project_ip.joana.filtros;

import java.awt.image.BufferedImage;

public class TransGamma {

    public BufferedImage gammaFiltro(BufferedImage imagemOriginal, double c, double gamma) {
        // Verifica se o valor de gamma está dentro do intervalo válido
        if (gamma < 0 || gamma > 1) {
            throw new IllegalArgumentException("O valor de gamma deve estar entre 0 e 1.");
        }

        // Cria uma nova imagem com as mesmas dimensões da original
        BufferedImage imagemTransformada = new BufferedImage(imagemOriginal.getWidth(), imagemOriginal.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        // Itera sobre todos os pixels da imagem
        for (int y = 0; y < imagemOriginal.getHeight(); y++) {
            for (int x = 0; x < imagemOriginal.getWidth(); x++) {
                // Pega o valor do pixel original
                int corOriginal = imagemOriginal.getRGB(x, y);

                // Extrai o valor do nível de cinza (considerando a imagem em tons de cinza)
                int r = (corOriginal >> 16) & 0xFF; // R (vermelho)

                // Aplica a transformação gamma: S = c * r^gamma
                int s = (int) (c * Math.pow(r, gamma));

                // Limita o valor de S para estar entre 0 e 255
                s = Math.min(255, Math.max(0, s));

                // Cria o novo valor da cor com o mesmo valor para R, G e B (imagem em tons de cinza)
                int novaCor = (s << 16) | (s << 8) | s; // R, G, B são iguais

                // Define o novo valor de cor para o pixel
                imagemTransformada.setRGB(x, y, novaCor);
            }
        }

        return imagemTransformada;
    }

}

package Filtros;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class ImagePadding {

    public static BufferedImage addZeroPadding(BufferedImage ImagemOriginal) {
        // Tamanho da nova imagem com zero padding (borda de 1 pixel em cada lado)
        int widthWithPadding = ImagemOriginal.getWidth() + 2;
        int heightWithPadding = ImagemOriginal.getHeight() + 2;

        // Cria uma nova imagem com o tamanho aumentado
        BufferedImage paddedImage = new BufferedImage(widthWithPadding, heightWithPadding, ImagemOriginal.getType());

        // Define os pixels da nova imagem para zero (preto), ou seja, borda com 0
        for (int y = 0; y < heightWithPadding; y++) {
            for (int x = 0; x < widthWithPadding; x++) {
                paddedImage.setRGB(x, y, Color.BLACK.getRGB());  // Preenchendo com cor preta (0)
            }
        }

        // Copia a imagem original para o centro da nova imagem (sem alterar as bordas)
        for (int y = 0; y < ImagemOriginal.getHeight(); y++) {
            for (int x = 0; x < ImagemOriginal.getWidth(); x++) {
                paddedImage.setRGB(x + 1, y + 1, ImagemOriginal.getRGB(x, y)); // Coloca a imagem original no centro
            }
        }

        return paddedImage;
    }
}

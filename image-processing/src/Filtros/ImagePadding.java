package Filtros;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class ImagePadding {

    public static BufferedImage addZeroPadding3x3(BufferedImage ImagemOriginal) {
        return addZeroPadding(ImagemOriginal, 3);
    }

    public static BufferedImage addZeroPadding5x5(BufferedImage ImagemOriginal) {
        return addZeroPadding(ImagemOriginal, 5);
    }

    public static BufferedImage addZeroPadding7x7(BufferedImage ImagemOriginal) {
        return addZeroPadding(ImagemOriginal, 7);
    }

    // Método genérico para adicionar padding com base no tamanho do kernel
    private static BufferedImage addZeroPadding(BufferedImage ImagemOriginal, int dimensaoKernel) {
        int offset = dimensaoKernel / 2; // Deslocamento (padding em cada lado)
        int widthWithPadding = ImagemOriginal.getWidth() + 2 * offset;
        int heightWithPadding = ImagemOriginal.getHeight() + 2 * offset;

        // Cria uma nova imagem com o tamanho aumentado
        BufferedImage paddedImage = new BufferedImage(widthWithPadding, heightWithPadding, ImagemOriginal.getType());

        // Define os pixels da nova imagem para zero (preto)
        for (int y = 0; y < heightWithPadding; y++) {
            for (int x = 0; x < widthWithPadding; x++) {
                paddedImage.setRGB(x, y, Color.BLACK.getRGB()); // Preenchendo com preto (0)
            }
        }

        // Copia a imagem original para o centro da nova imagem
        for (int y = 0; y < ImagemOriginal.getHeight(); y++) {
            for (int x = 0; x < ImagemOriginal.getWidth(); x++) {
                paddedImage.setRGB(x + offset, y + offset, ImagemOriginal.getRGB(x, y));
            }
        }

        return paddedImage;
    }
}

package project_ip.bases;

import project_ip.interfaces.ICallbackFilter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePGM {

    private BufferedImage image;

    private int borderSize;

    public ImagePGM(BufferedImage image) {
        this.image = image;
        this.borderSize = 2;
        this.addPadding();
    }

    private int getOffSet() {
        return borderSize / 2;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void addPadding() {
        int widthWithPadding = image.getWidth() + this.borderSize;
        int heightWithPadding = image.getHeight() + this.borderSize;
        int offSet = getOffSet();

        // Cria uma nova imagem com o tamanho aumentado
        BufferedImage paddedImage = new BufferedImage(widthWithPadding, heightWithPadding, image.getType());

        // Define os pixels da nova imagem para zero (preto), ou seja, borda com 0
        for (int y = 0; y < heightWithPadding; y++) {
            for (int x = 0; x < widthWithPadding; x++) {
                paddedImage.setRGB(x, y, Color.BLACK.getRGB());  // Preenchendo com cor preta (0)
            }
        }

        // Copia a imagem original para o centro da nova imagem (sem alterar as bordas)
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                paddedImage.setRGB(x + offSet, y + offSet, image.getRGB(x, y)); // Coloca a imagem original no centro
            }
        }

        this.image = paddedImage;
    }

    public BufferedImage applyFunction(ICallbackFilter callback) {
        int offSet = getOffSet();
        int width = this.image.getWidth();
        int height = this.image.getHeight();

        BufferedImage resultImage = new BufferedImage(width, height, this.image.getType());

        for (int y = offSet; y < height - offSet; y++) {
            for (int x = offSet; x < width - offSet; x++) {
                int newPixel = callback.apply(x, y, this.image);

                newPixel = Math.min(Math.max(newPixel, 0), 255);

                Color grayColor = new Color(newPixel, newPixel, newPixel);
                resultImage.setRGB(x, y, grayColor.getRGB());
            }
        }

        return resultImage;
    }

    public int getPixel(int x, int y) {
        int rgbValue = this.image.getRGB(x, y);
        return (rgbValue >> 16) & 0xFF;
    }

    public BufferedImage applyConvolution(float[][] kernel) {
        int kernelSize = kernel.length;
        int offSet = this.getOffSet();

        int width = this.image.getWidth();
        int height = this.image.getHeight();

        BufferedImage resultImage = new BufferedImage(width, height, this.image.getType());

        for (int y = offSet; y < height - offSet; y++) {
            for (int x = offSet; x < width - offSet; x++) {
                float newPixelValue = 0;

                for (int ky = 0; ky < kernelSize; ky++) {
                    for (int kx = 0; kx < kernelSize; kx++) {
                        int pixelValue = this.getPixel(x + kx - offSet, y + ky - offSet);
                        newPixelValue += pixelValue * kernel[ky][kx];
                    }
                }

                int finalPixelValue = Math.min(Math.max(Math.round(newPixelValue), 0), 255);
                resultImage.setRGB(x, y, finalPixelValue);
            }
        }

        return resultImage;
    }



}



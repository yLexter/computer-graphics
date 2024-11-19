package trans_geometricas;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Op_geo {

    // Zoom In com fator de ampliação
    public BufferedImage zoomIn(BufferedImage imagemOriginal, double fator) {
        int width = (int) (imagemOriginal.getWidth() * fator);
        int height = (int) (imagemOriginal.getHeight() * fator);
        BufferedImage result = new BufferedImage(width, height, imagemOriginal.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int originalX = (int) (x / fator);
                int originalY = (int) (y / fator);
                result.setRGB(x, y, imagemOriginal.getRGB(originalX, originalY));
            }
        }
        return result;
    }

    // Zoom Out com fator de redução
    public BufferedImage zoomOut(BufferedImage imagemOriginal, double fator) {
        int width = (int) (imagemOriginal.getWidth() / fator);
        int height = (int) (imagemOriginal.getHeight() / fator);
        BufferedImage result = new BufferedImage(width, height, imagemOriginal.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int originalX = (int) (x * fator);
                int originalY = (int) (y * fator);
                result.setRGB(x, y, imagemOriginal.getRGB(originalX, originalY));
            }
        }
        return result;
    }

    // Rotação da imagem em torno do centro
    public BufferedImage rotacao(BufferedImage imagemOriginal, double angulo) {
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage result = new BufferedImage(width, height, imagemOriginal.getType());

        double radians = Math.toRadians(angulo);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);

        int centerX = width / 2;
        int centerY = height / 2;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int relX = x - centerX;
                int relY = y - centerY;

                int originalX = (int) (relX * cos + relY * sin) + centerX;
                int originalY = (int) (-relX * sin + relY * cos) + centerY;

                if (originalX >= 0 && originalX < width && originalY >= 0 && originalY < height) {
                    result.setRGB(x, y, imagemOriginal.getRGB(originalX, originalY));
                } else {
                    result.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }
        return result;
    }

    // Reflexão Vertical
    public BufferedImage reflexaoVertical(BufferedImage imagemOriginal) {
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage result = new BufferedImage(width, height, imagemOriginal.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result.setRGB(x, height - y - 1, imagemOriginal.getRGB(x, y));
            }
        }
        return result;
    }

    // Reflexão Diagonal Principal
    public BufferedImage reflexaoDiagonalPrincipal(BufferedImage imagemOriginal) {
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        int size = Math.min(width, height); // Assume uma matriz quadrada para simplificar
        BufferedImage result = new BufferedImage(width, height, imagemOriginal.getType());

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                result.setRGB(y, x, imagemOriginal.getRGB(x, y));
            }
        }
        return result;
    }

    // Reflexão Diagonal Secundária
    public BufferedImage reflexaoDiagonalSecundaria(BufferedImage imagemOriginal) {
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        int size = Math.min(width, height); // Assume uma matriz quadrada para simplificar
        BufferedImage result = new BufferedImage(width, height, imagemOriginal.getType());

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                result.setRGB(size - y - 1, size - x - 1, imagemOriginal.getRGB(x, y));
            }
        }
        return result;
    }

    // Reflexão Combinada (Horizontal e Vertical)
    public BufferedImage reflexaoCombinada(BufferedImage imagemOriginal) {
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage result = new BufferedImage(width, height, imagemOriginal.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result.setRGB(width - x - 1, height - y - 1, imagemOriginal.getRGB(x, y));
            }
        }
        return result;
    }
    // Reflexão Horizontal (Espelhamento)
    public BufferedImage reflexaoHorizontal(BufferedImage imagemOriginal) {
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage result = new BufferedImage(width, height, imagemOriginal.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result.setRGB(width - x - 1, y, imagemOriginal.getRGB(x, y));
            }
        }
        return result;
    }


}


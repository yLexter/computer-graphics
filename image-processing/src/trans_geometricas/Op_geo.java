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

    public BufferedImage escalamentoNaoUniforme(BufferedImage imagemOriginal, double escalaX, double escalaY) {
        int newWidth = (int) (imagemOriginal.getWidth() * escalaX);
        int newHeight = (int) (imagemOriginal.getHeight() * escalaY);
        BufferedImage result = new BufferedImage(newWidth, newHeight, imagemOriginal.getType());

        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                int srcX = (int) (x / escalaX);
                int srcY = (int) (y / escalaY);
                if (srcX < imagemOriginal.getWidth() && srcY < imagemOriginal.getHeight()) {
                    result.setRGB(x, y, imagemOriginal.getRGB(srcX, srcY));
                }
            }
        }
        return result;
    }

    public BufferedImage cisalhamentoHorizontal(BufferedImage imagemOriginal, double fator) {
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage result = new BufferedImage(width + (int) (height * fator), height, imagemOriginal.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int newX = x + (int) (y * fator);
                result.setRGB(newX, y, imagemOriginal.getRGB(x, y));
            }
        }
        return result;
    }

    public BufferedImage cisalhamentoVertical(BufferedImage imagemOriginal, double fator) {
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage result = new BufferedImage(width, height + (int) (width * fator), imagemOriginal.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int newY = y + (int) (x * fator);
                result.setRGB(x, newY, imagemOriginal.getRGB(x, y));
            }
        }
        return result;
    }

    public BufferedImage transformacaoPerspectiva(BufferedImage imagemOriginal) {
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage result = new BufferedImage(width, height, imagemOriginal.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int newX = (int) (x + 0.001 * x * y); // Exemplo de transformação
                int newY = (int) (y + 0.001 * x * y);
                if (newX < width && newY < height) {
                    result.setRGB(newX, newY, imagemOriginal.getRGB(x, y));
                }
            }
        }
        return result;
    }

    public BufferedImage curvatura(BufferedImage imagemOriginal) {
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage result = new BufferedImage(width, height, imagemOriginal.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int newX = (int) (x + 20 * Math.sin(2 * Math.PI * y / 200));
                int newY = y;
                if (newX >= 0 && newX < width) {
                    result.setRGB(newX, newY, imagemOriginal.getRGB(x, y));
                }
            }
        }
        return result;
    }
    public BufferedImage fishEye(BufferedImage imagemOriginal) {
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage result = new BufferedImage(width, height, imagemOriginal.getType());

        double centerX = width / 2.0;
        double centerY = height / 2.0;
        double radius = Math.min(width, height) / 2.0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double dx = (x - centerX) / radius;
                double dy = (y - centerY) / radius;
                double distance = Math.sqrt(dx * dx + dy * dy);

                if (distance < 1) {
                    double factor = (1 - Math.sqrt(1 - distance * distance)) * 2; // Função de Fish Eye
                    int srcX = (int) (centerX + dx * radius * factor);
                    int srcY = (int) (centerY + dy * radius * factor);

                    if (srcX >= 0 && srcX < width && srcY >= 0 && srcY < height) {
                        result.setRGB(x, y, imagemOriginal.getRGB(srcX, srcY));
                    }
                }
            }
        }
        return result;
    }

    public BufferedImage swirl(BufferedImage imagemOriginal, double strength) {
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage result = new BufferedImage(width, height, imagemOriginal.getType());

        double centerX = width / 2.0;
        double centerY = height / 2.0;
        double radius = Math.min(width, height) / 2.0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double dx = x - centerX;
                double dy = y - centerY;
                double distance = Math.sqrt(dx * dx + dy * dy);

                if (distance < radius) {
                    double angle = Math.atan2(dy, dx) + strength * (radius - distance) / radius; // Cálculo do ângulo de rotação
                    int srcX = (int) (centerX + distance * Math.cos(angle));
                    int srcY = (int) (centerY + distance * Math.sin(angle));

                    if (srcX >= 0 && srcX < width && srcY >= 0 && srcY < height) {
                        result.setRGB(x, y, imagemOriginal.getRGB(srcX, srcY));
                    }
                }
            }
        }
        return result;
    }

    public BufferedImage gridDistortion(BufferedImage imagemOriginal, int gridSize, double distortionFactor) {
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage result = new BufferedImage(width, height, imagemOriginal.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Calcula a posição da célula da grade
                int gridX = x / gridSize;
                int gridY = y / gridSize;

                // Aplica deslocamentos dentro da célula
                int offsetX = (int) (Math.sin(gridY * distortionFactor) * gridSize / 2);
                int offsetY = (int) (Math.cos(gridX * distortionFactor) * gridSize / 2);

                int srcX = Math.min(Math.max(x + offsetX, 0), width - 1);
                int srcY = Math.min(Math.max(y + offsetY, 0), height - 1);

                result.setRGB(x, y, imagemOriginal.getRGB(srcX, srcY));
            }
        }
        return result;
    }

    public BufferedImage rippleEffect(BufferedImage imagemOriginal, double waveLength, double amplitude) {
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        BufferedImage result = new BufferedImage(width, height, imagemOriginal.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Calcula a posição com base nas ondas senoidais
                int offsetX = (int) (amplitude * Math.sin(2 * Math.PI * y / waveLength));
                int offsetY = (int) (amplitude * Math.cos(2 * Math.PI * x / waveLength));

                int srcX = Math.min(Math.max(x + offsetX, 0), width - 1);
                int srcY = Math.min(Math.max(y + offsetY, 0), height - 1);

                result.setRGB(x, y, imagemOriginal.getRGB(srcX, srcY));
            }
        }
        return result;
    }

}


package Filtros;

import java.awt.image.BufferedImage;

public class Sobel {
    public BufferedImage sobelFiltro (BufferedImage ImagemOriginal){
        return Convolucao.ConvolucaoSobel(ImagemOriginal);
    }
}

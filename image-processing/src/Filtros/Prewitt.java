package Filtros;

import java.awt.image.BufferedImage;

public class Prewitt {
    public BufferedImage prewittFiltro (BufferedImage ImagemOriginal){
        return Convolucao.ConvolucaoPrewitt(ImagemOriginal);
    }
}

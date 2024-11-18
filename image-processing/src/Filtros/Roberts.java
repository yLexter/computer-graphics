package Filtros;

import java.awt.image.BufferedImage;

public class Roberts {
    public BufferedImage robertsFiltro (BufferedImage ImagemOriginal, int mascara_size){
        return Convolucao.ConvolucaoRoberts(ImagemOriginal,mascara_size);
    }
}

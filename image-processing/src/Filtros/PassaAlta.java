package Filtros;

import java.awt.image.BufferedImage;

public class PassaAlta {

    public BufferedImage passaAltaFiltro (BufferedImage ImagemOriginal){
        return Convolucao.ConvolucaoPassaAlta(ImagemOriginal);
    }
}

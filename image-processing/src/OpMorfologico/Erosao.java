package OpMorfologico;

import Filtros.Convolucao;

import java.awt.image.BufferedImage;

public class Erosao {

    public BufferedImage operadorErosao (BufferedImage ImagemOriginal,int dimensaoKernel){
        return Convolucao.ConvolucaoErosao(ImagemOriginal,dimensaoKernel);

    }

}

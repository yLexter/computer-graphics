package OpMorfologico;

import Filtros.Convolucao;

import java.awt.image.BufferedImage;

public class Dilatacao {

    public BufferedImage operadorDilatacao (BufferedImage ImagemOriginal, int dimensaoKernel){
        return Convolucao.ConvolucaoDilatacao(ImagemOriginal,dimensaoKernel);

    }
}

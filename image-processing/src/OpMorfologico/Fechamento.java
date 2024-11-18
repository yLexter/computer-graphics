package OpMorfologico;

import Filtros.Convolucao;

import java.awt.image.BufferedImage;

public class Fechamento {

    public BufferedImage operadorFechamento (BufferedImage ImagemOriginal){
        int dimensaoKernel = 3; // por padrão o kernal vai ser 3x3
        BufferedImage dilatacao = Convolucao.ConvolucaoDilatacao(ImagemOriginal,dimensaoKernel);
        return Convolucao.ConvolucaoErosao(dilatacao,dimensaoKernel);

    }
}

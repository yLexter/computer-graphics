package project_ip.joana.opMorfologico;

import project_ip.joana.filtros.*;
import java.awt.image.BufferedImage;

public class Abertura {

    public BufferedImage operadorAbertura (BufferedImage ImagemOriginal){
        int dimensaoKernel = 3; // por padrão o kernal vai ser 3x3
        BufferedImage erosao = Convolucao.ConvolucaoErosao(ImagemOriginal,dimensaoKernel);
        return Convolucao.ConvolucaoDilatacao(erosao,dimensaoKernel);

    }

}

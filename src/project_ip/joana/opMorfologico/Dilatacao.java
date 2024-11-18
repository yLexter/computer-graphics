package project_ip.joana.opMorfologico;

import project_ip.joana.filtros.*;
import java.awt.image.BufferedImage;

public class Dilatacao {

    public BufferedImage operadorDilatacao (BufferedImage ImagemOriginal, int dimensaoKernel){
        return Convolucao.ConvolucaoDilatacao(ImagemOriginal,dimensaoKernel);
    }
}

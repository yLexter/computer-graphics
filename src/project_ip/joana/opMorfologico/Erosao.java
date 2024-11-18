package project_ip.joana.opMorfologico;

import project_ip.joana.filtros.*;
import java.awt.image.BufferedImage;

public class Erosao {

    public BufferedImage operadorErosao (BufferedImage ImagemOriginal,int dimensaoKernel){
        return Convolucao.ConvolucaoErosao(ImagemOriginal,dimensaoKernel);

    }

}

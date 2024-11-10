package Filtros;

import java.awt.image.BufferedImage;
import Filtros.Convolucao;

public class Media {

    float[][] mascara = new float[3][3];
    float fator = 1.0f / 9.0f;


    public Media() {
        // Preenche a matriz `mascara` com o valor `fator`
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mascara[i][j] = fator;
            }
        }
    }

    public void mediaFiltro (BufferedImage ImagemOriginal){
        Convolucao.ConvolucaoCalculo(ImagemOriginal,mascara);


    }
}

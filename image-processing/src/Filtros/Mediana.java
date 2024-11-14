package Filtros;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
public class Mediana {


    //mascara 3x3
    int mascara_size = 3;


    public BufferedImage medianaFiltro (BufferedImage ImagemOriginal){
        return Convolucao.ConvolucaoMediana(ImagemOriginal,mascara_size);


    }


}


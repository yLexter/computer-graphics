package project_ip.joana.filtros;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
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

    public float[][] getMascara() {
        return mascara;
    }

    public BufferedImage mediaFiltro (BufferedImage ImagemOriginal){
       return Convolucao.ConvolucaoMedia(ImagemOriginal,mascara);
    }

    public static void main(String[] args) {
        try {
            // Caminho da imagem de entrada
            File inputFile = new File("C/Users/joana/Documents/computacao grafica/imagens (1)/lena.pgm");
            BufferedImage imagemOriginal = ImageIO.read(inputFile);

            if (imagemOriginal == null) {
                System.out.println("Erro ao carregar a imagem.");
                return;
            }

            // Cria uma instância da classe Media e aplica o filtro
            Media media = new Media();
            BufferedImage imagemFiltrada = media.mediaFiltro(imagemOriginal);

            // Exibe a imagem filtrada em um JFrame
            JFrame frame = new JFrame("Imagem Filtrada");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(imagemFiltrada.getWidth(), imagemFiltrada.getHeight());

            JLabel label = new JLabel(new ImageIcon(imagemFiltrada));
            frame.add(label);

            frame.pack();
            frame.setLocationRelativeTo(null); // Centraliza a janela
            frame.setVisible(true);

        } catch (IOException e) {
            System.out.println("Erro ao processar a imagem: " + e.getMessage());
        }
    }
}


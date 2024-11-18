package Funcoes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class CarregarImagens {

    // Método para carregar imagens PGM (formato P2) ou PBM (formato P1 ou P4)
    public static BufferedImage carregarImagemPGM(String caminhoArquivo) {
        try (Scanner scanner = new Scanner(new FileInputStream(caminhoArquivo))) {
            // Lê o cabeçalho
            String tipo = scanner.next();  // Deve ser "P2"
            if (!"P2".equals(tipo)) {
                throw new IOException("Formato PGM não suportado: " + tipo);
            }

            int largura = scanner.nextInt();
            int altura = scanner.nextInt();
            int maxValor = scanner.nextInt(); // valor maximo dos niveis de cinza

            BufferedImage imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_BYTE_GRAY); //Represents a unsigned byte grayscale image, non-indexed.

            // Preenche os pixels da imagem
            for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {
                    int pixelValue = scanner.nextInt();
                    int cor = (pixelValue * 255) / maxValor;
                    int rgb = new Color(cor, cor, cor).getRGB();
                    imagem.setRGB(x, y, rgb);
                }
            }
            return imagem;
        } catch (IOException e) {
            System.out.println("Erro ao carregar imagem PGM: " + e.getMessage());
            return null;
        }
    }

    public static  BufferedImage carregarImagemPNG(String caminhoArquivo) {
        try {
            // Carrega a imagem PNG
            BufferedImage imagem = ImageIO.read(new File(caminhoArquivo));

            // Verifica se a imagem é binária (branco e preto)
            BufferedImage imagemBinaria = new BufferedImage(
                    imagem.getWidth(),
                    imagem.getHeight(),
                    BufferedImage.TYPE_BYTE_BINARY
            );

            for (int y = 0; y < imagem.getHeight(); y++) {
                for (int x = 0; x < imagem.getWidth(); x++) {
                    // Obtém a cor original do pixel
                    int rgb = imagem.getRGB(x, y);

                    // Converte para preto ou branco baseado em um limite (grayscale)
                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = rgb & 0xFF;

                    // Calcula intensidade média (grayscale)
                    int intensidade = (r + g + b) / 3;

                    // Define como branco (255) ou preto (0) com base em um limite
                    int corBinaria = (intensidade > 127) ? 0xFFFFFF : 0x000000;
                    imagemBinaria.setRGB(x, y, corBinaria);
                }
            }
            return imagemBinaria;

        } catch (IOException e) {
            System.out.println("Erro ao carregar imagem PNG: " + e.getMessage());
            return null;
        }
    }


    public static  BufferedImage carregarImagemPBM(String caminhoArquivo) {
        try (FileInputStream fis = new FileInputStream(caminhoArquivo)) {
            Scanner scanner = new Scanner(fis);

            // Ignorar comentários no cabeçalho
            String tipo;
            do {
                tipo = scanner.next();
            } while (tipo.startsWith("#"));

            if (!"P1".equals(tipo) && !"P4".equals(tipo)) {
                throw new IOException("Formato PBM não suportado: " + tipo);
            }

            // Ignorar comentários e ler dimensões
            while (scanner.hasNext("#")) {
                scanner.nextLine();
            }
            int largura = scanner.nextInt();
            int altura = scanner.nextInt();

            BufferedImage imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_BYTE_BINARY);

            if ("P1".equals(tipo)) {
                // Leitura de texto (0 ou 1)
                for (int y = 0; y < altura; y++) {
                    for (int x = 0; x < largura; x++) {
                        int pixelValue = scanner.nextInt();
                        int rgb = (pixelValue == 1) ? 0xFFFFFF : 0x000000; // Branco ou Preto
                        imagem.setRGB(x, y, rgb);
                    }
                }
            } else if ("P4".equals(tipo)) {
                // Leitura binária
                fis.skip(scanner.match().end()); // Pula o cabeçalho lido pelo scanner
                byte[] dadosBinarios = fis.readAllBytes(); // Lê todos os dados binários

                int byteIndex = 0;
                for (int y = 0; y < altura; y++) {
                    for (int x = 0; x < largura; x++) {
                        int bitIndex = x % 8;
                        int pixelValue = (dadosBinarios[byteIndex] >> (7 - bitIndex)) & 1;
                        int rgb = (pixelValue == 1) ? 0xFFFFFF : 0x000000; // Branco ou Preto
                        imagem.setRGB(x, y, rgb);

                        // Avança para o próximo byte quando todos os bits de um byte são lidos
                        if (bitIndex == 7) byteIndex++;
                    }
                }
            }

            return imagem;
        } catch (IOException e) {
            System.out.println("Erro ao carregar imagem PBM: " + e.getMessage());
            return null;
        }
    }

}

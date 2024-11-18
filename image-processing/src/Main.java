import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.LineBorder;
import Filtros.*;
import OpMorfologico.Abertura;
import OpMorfologico.Dilatacao;
import OpMorfologico.Erosao;
import OpMorfologico.Fechamento;

public class Main extends JFrame {
    private JButton carregarImagemButton;
    private JMenuBar mb1;
    private JMenu escolherFiltroButton;
    private JMenuItem f1,f2,f3,f4,f5,f6,f7,f8;
    //private JButton escolherFiltroButton;
    private JPanel TelaPrincipal;
    private JLabel Titulo;
    private JLabel Output;
    private JLabel mascara;
    private BufferedImage imagemExibida;
    private BufferedImage imagemOutput;
    private int mouseX = -1, mouseY = -1; // Coordenadas do mouse
    private JLabel mascaraLabel;

    //operadores morfologicos
    private JMenuBar mb2;
    private JMenu escolherMorfologico;
    private JMenu dimensaoKernalErosao;
    private JMenu dimensaoKernalDilatacao;
    private JMenuItem dil,ero,fec,abe,tophat,bottomhat;
    private JMenuItem ero3x3,ero5x5,ero7x7;
    private JMenuItem dil3x3,dil5x5,dil7x7;


    public Main() {
        // Inicializa o painel
        TelaPrincipal = new JPanel();
        TelaPrincipal.setLayout(null);

        // Inicializa e configura os componentes
        mb1 = new JMenuBar();
        mb2 = new JMenuBar();
        carregarImagemButton = new JButton("Carregar Imagem");
        escolherFiltroButton = new JMenu("Filtros");
        escolherMorfologico = new JMenu("Operadores Morfológicos");
        dimensaoKernalErosao = new JMenu("Erosão");
        dimensaoKernalDilatacao = new JMenu("Dilatação");
        Titulo = new JLabel("Processamento de Imagem");
        Output = new JLabel("Output");

        // Inicializa o JLabel para a matriz máscara e define posição
        mascara = new JLabel("Máscara");
        mascaraLabel = new JLabel();
        mascara.setBounds(573, 330, 60, 30);
        mascaraLabel.setBounds(557, 349, 110, 90); // Ajuste o tamanho conforme necessário

        TelaPrincipal.add(mascaraLabel);


        // Define fontes para os componentes
        Font fonteTitulo = new Font("Impact", Font.BOLD, 24);
        Font fonteBotao = new Font("Impact", Font.PLAIN, 14);
        Font fonteLabel = new Font("Impact", Font.PLAIN, 18);

        // Aplica as fontes aos componentes
        mascaraLabel.setFont(fonteBotao);
        mascara.setFont(fonteBotao);
        Titulo.setFont(fonteTitulo);
        Output.setFont(fonteTitulo);
        carregarImagemButton.setFont(fonteBotao);
        escolherFiltroButton.setFont(fonteBotao);
        escolherMorfologico.setFont(fonteBotao);

        //Os filtros disponiveis
        f1 = new JMenuItem("Filtro da Média");
        f2 = new JMenuItem("Filtro da Mediana");
        f3 = new JMenuItem("Filtro Negativo");
        f4 = new JMenuItem("Transformção Gamma");

        // Adicionar os filtros no menu 1
        mb1.add(escolherFiltroButton);
        escolherFiltroButton.add(f1);
        escolherFiltroButton.add(f2);
        escolherFiltroButton.add(f3);
        escolherFiltroButton.add(f4);

        //OP morf disponiveis
        //dil = new JMenuItem("Dilatação");
        //ero = new JMenuItem("Erosão");
        fec = new JMenuItem("Fechamento");
        abe = new JMenuItem("Abertura");
        tophat = new JMenuItem("Top-Hat");
        bottomhat = new JMenuItem("Bottom-Hat");


        //dimensoes de kernal
        ero3x3 = new JMenuItem("Kernel 3x3");
        ero5x5 = new JMenuItem("Kernel 5x5");
        ero7x7 = new JMenuItem("Kernel 7x7");
        dil3x3 = new JMenuItem("Kernel 3x3");
        dil5x5 = new JMenuItem("Kernel 5x5");
        dil7x7 = new JMenuItem("Kernel 7x7");
        dimensaoKernalErosao.add(ero3x3);
        dimensaoKernalErosao.add(ero5x5);
        dimensaoKernalErosao.add(ero7x7);
        dimensaoKernalDilatacao.add(dil3x3);
        dimensaoKernalDilatacao.add(dil5x5);
        dimensaoKernalDilatacao.add(dil7x7);

        // Adicionar os filtros no menu 2
        mb2.add(escolherMorfologico);  //jmenu

        escolherMorfologico.add(dimensaoKernalDilatacao); //submenu
        escolherMorfologico.add(dimensaoKernalErosao);  //submenu
        escolherMorfologico.add(fec);
        escolherMorfologico.add(abe);
        escolherMorfologico.add(tophat);
        escolherMorfologico.add(bottomhat);


        //ações de selecionar os filtros

        f1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria uma instância da classe Media
                Media media = new Media();
                TelaPrincipal.add(mascara);
                TelaPrincipal.add(Output);
                // Exibe a matriz máscara no JLabel
                StringBuilder mascaraTexto = new StringBuilder("<html>");
                float[][] mascara = media.getMascara();  // Obtém a matriz através do método
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        mascaraTexto.append(String.format("%.3f ", mascara[i][j]));
                    }
                    mascaraTexto.append("<br>");
                }
                mascaraTexto.append("</html>");
                mascaraLabel.setText(mascaraTexto.toString());

                // Verifica se a imagem foi carregada antes de aplicar o filtro
                if (imagemExibida != null) {
                    imagemOutput = media.mediaFiltro(imagemExibida);   // Chama o filtro na imagem exibida
                    // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                    repaint();  // Repaint para atualizar a exibição da imagem filtrada
                } else {
                    System.out.println("Imagem não carregada.");
                }
            }
        });

        f2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TelaPrincipal.add(Output);

                Mediana mediana = new Mediana();

                // Verifica se a imagem foi carregada antes de aplicar o filtro
                if (imagemExibida != null) {
                    imagemOutput = mediana.medianaFiltro(imagemExibida);   // Chama o filtro na imagem exibida
                    // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                    repaint();  // Repaint para atualizar a exibição da imagem filtrada
                } else {
                    System.out.println("Imagem não carregada.");
                }
            }
        });
        ;

        f3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TelaPrincipal.add(Output);

                Negativo negativo = new Negativo();

                // Verifica se a imagem foi carregada antes de aplicar o filtro
                if (imagemExibida != null) {
                    imagemOutput = negativo.negativoFiltro(imagemExibida);   // Chama o filtro na imagem exibida
                    // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                    repaint();  // Repaint para atualizar a exibição da imagem filtrada
                } else {
                    System.out.println("Imagem não carregada.");
                }
            }
        });
        ;

        f4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    // Painel para entrada de parâmetros C e gama
                    JPanel panel = new JPanel(new GridLayout(2, 2));

                    // Ajustar o tamanho e estilo das fontes
                    JLabel cLabel = new JLabel("C: ");
                    cLabel.setFont(fonteLabel);
                    panel.add(cLabel);

                    JTextField cField = new JTextField();
                    panel.add(cField);

                    JLabel gammaLabel = new JLabel("Gama: ");
                    gammaLabel.setFont(fonteLabel);
                    panel.add(gammaLabel);

                    JTextField gammaField = new JTextField();
                    panel.add(gammaField);

                // Exibe o diálogo
                int result = JOptionPane.showConfirmDialog(
                        null,
                        panel,
                        "Definir parâmetros C e Gama",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );
             

                // Captura os valores inseridos pelo usuário
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        double cValue = Double.parseDouble(cField.getText());
                        double gammaValue = Double.parseDouble(gammaField.getText());
                        System.out.println("C: " + cValue + ", Gama: " + gammaValue);
                        TelaPrincipal.add(Output);

                        TransGamma gamma = new TransGamma();

                        // Verifica se a imagem foi carregada antes de aplicar o filtro
                        if (imagemExibida != null) {
                            imagemOutput = gamma.gammaFiltro(imagemExibida,cValue,gammaValue);   // Chama o filtro na imagem exibida
                            // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                            repaint();  // Repaint para atualizar a exibição da imagem filtrada
                        } else {
                            System.out.println("Imagem não carregada.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Por favor, insira valores numéricos válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });





        dil3x3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    TelaPrincipal.add(Output);

                    Dilatacao dilatacao = new Dilatacao();

                    // Verifica se a imagem foi carregada antes de aplicar o filtro
                    if (imagemExibida != null) {
                        imagemOutput = dilatacao.operadorDilatacao(imagemExibida, 3);   // Chama o filtro na imagem exibida
                        // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                        repaint();  // Repaint para atualizar a exibição da imagem filtrada
                    } else {
                        System.out.println("Imagem não carregada.");
                    }
                }
            });
            ;

            dil5x5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    TelaPrincipal.add(Output);

                    Dilatacao dilatacao = new Dilatacao();

                    // Verifica se a imagem foi carregada antes de aplicar o filtro
                    if (imagemExibida != null) {
                        imagemOutput = dilatacao.operadorDilatacao(imagemExibida, 5);   // Chama o filtro na imagem exibida
                        // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                        repaint();  // Repaint para atualizar a exibição da imagem filtrada
                    } else {
                        System.out.println("Imagem não carregada.");
                    }
                }
            });
            ;

            dil7x7.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    TelaPrincipal.add(Output);

                    Dilatacao dilatacao = new Dilatacao();

                    // Verifica se a imagem foi carregada antes de aplicar o filtro
                    if (imagemExibida != null) {
                        imagemOutput = dilatacao.operadorDilatacao(imagemExibida, 7);   // Chama o filtro na imagem exibida
                        // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                        repaint();  // Repaint para atualizar a exibição da imagem filtrada
                    } else {
                        System.out.println("Imagem não carregada.");
                    }
                }
            });
            ;

            ero3x3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    TelaPrincipal.add(Output);

                    Erosao erosao = new Erosao();

                    // Verifica se a imagem foi carregada antes de aplicar o filtro
                    if (imagemExibida != null) {
                        imagemOutput = erosao.operadorErosao(imagemExibida, 3);   // Chama o filtro na imagem exibida
                        // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                        repaint();  // Repaint para atualizar a exibição da imagem filtrada
                    } else {
                        System.out.println("Imagem não carregada.");
                    }
                }
            });
            ;

            ero5x5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    TelaPrincipal.add(Output);

                    Erosao erosao = new Erosao();

                    // Verifica se a imagem foi carregada antes de aplicar o filtro
                    if (imagemExibida != null) {
                        imagemOutput = erosao.operadorErosao(imagemExibida, 5);   // Chama o filtro na imagem exibida
                        // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                        repaint();  // Repaint para atualizar a exibição da imagem filtrada
                    } else {
                        System.out.println("Imagem não carregada.");
                    }
                }
            });
            ;

            ero7x7.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    TelaPrincipal.add(Output);

                    Erosao erosao = new Erosao();

                    // Verifica se a imagem foi carregada antes de aplicar o filtro
                    if (imagemExibida != null) {
                        imagemOutput = erosao.operadorErosao(imagemExibida, 7);   // Chama o filtro na imagem exibida
                        // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                        repaint();  // Repaint para atualizar a exibição da imagem filtrada
                    } else {
                        System.out.println("Imagem não carregada.");
                    }
                }
            });
            ;


            abe.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    TelaPrincipal.add(Output);

                    Abertura abertura = new Abertura();

                    // Verifica se a imagem foi carregada antes de aplicar o filtro
                    if (imagemExibida != null) {
                        imagemOutput = abertura.operadorAbertura(imagemExibida);   // Chama o filtro na imagem exibida
                        // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                        repaint();  // Repaint para atualizar a exibição da imagem filtrada
                    } else {
                        System.out.println("Imagem não carregada.");
                    }
                }
            });
            ;

            fec.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    TelaPrincipal.add(Output);

                    Fechamento fechamento = new Fechamento();

                    // Verifica se a imagem foi carregada antes de aplicar o filtro
                    if (imagemExibida != null) {
                        imagemOutput = fechamento.operadorFechamento(imagemExibida);   // Chama o filtro na imagem exibida
                        // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                        repaint();  // Repaint para atualizar a exibição da imagem filtrada
                    } else {
                        System.out.println("Imagem não carregada.");
                    }
                }
            });
            ;

            // Define posições dos componentes setBounds(int x-coordinate, int y-coordinate, int width, int height)
            Titulo.setBounds(425, 55, 390, 36);
            Output.setBounds(255, 730, 170, 24);
            carregarImagemButton.setBounds(215, 410, 170, 30);
            mb1.setBounds(582, 177, 104, 30);
            mb2.setBounds(582, 340, 170, 30);
            //escolherFiltroButton.setBounds(582,177,104,30);

            // Configurações dos botoes
            carregarImagemButton.setBorder(new LineBorder(Color.BLACK));
            carregarImagemButton.setOpaque(false);
            carregarImagemButton.setContentAreaFilled(false);
            carregarImagemButton.setFocusPainted(false);

            escolherFiltroButton.setBorder(new LineBorder(Color.BLACK));
            escolherFiltroButton.setOpaque(false);
            escolherFiltroButton.setContentAreaFilled(false);
            escolherFiltroButton.setFocusPainted(false);


            escolherMorfologico.setBorder(new LineBorder(Color.BLACK));
            escolherMorfologico.setOpaque(false);
            escolherMorfologico.setContentAreaFilled(false);
            escolherMorfologico.setFocusPainted(false);

            dimensaoKernalDilatacao.setOpaque(false);
            dimensaoKernalDilatacao.setContentAreaFilled(false);
            dimensaoKernalDilatacao.setFocusPainted(false);


            dimensaoKernalErosao.setOpaque(false);
            dimensaoKernalErosao.setContentAreaFilled(false);
            dimensaoKernalErosao.setFocusPainted(false);


            // Adiciona os componentes ao painel
            TelaPrincipal.add(Titulo);
            TelaPrincipal.add(carregarImagemButton);
            TelaPrincipal.add(mb1);
            TelaPrincipal.add(mb2);
            //TelaPrincipal.add(escolherFiltroButton);

            // Configura o JFrame
            setContentPane(TelaPrincipal);
            setTitle("Processamento de Imagem");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(1200, 800);
            setLocationRelativeTo(null);

            // Adiciona ação ao botão
            carregarImagemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens PGM,PBM e PNG", "pgm", "pbm", "png");
                    JFileChooser selecionarImagem = new JFileChooser();
                    selecionarImagem.setFileFilter(filter);

                    int result = selecionarImagem.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        String caminhoArquivo = selecionarImagem.getSelectedFile().getAbsolutePath();
                        System.out.println("Imagem selecionada: " + caminhoArquivo);

                        if (caminhoArquivo.endsWith(".pgm")) {
                            imagemExibida = carregarImagemPGM(caminhoArquivo);
                        } else if (caminhoArquivo.endsWith(".pbm")) {
                            imagemExibida = carregarImagemPBM(caminhoArquivo);
                        } else if (caminhoArquivo.endsWith(".png")) {
                            imagemExibida = carregarImagemPNG(caminhoArquivo);
                        }


                        repaint();  // Chama repaint para atualizar a exibição da imagem
                    }
                }
            });

            setVisible(true);
        }


    // Método para carregar imagens PGM (formato P2) ou PBM (formato P1 ou P4)
    private BufferedImage carregarImagemPGM(String caminhoArquivo) {
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

    private BufferedImage carregarImagemPNG(String caminhoArquivo) {
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


    private BufferedImage carregarImagemPBM(String caminhoArquivo) {
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






    // Método para desenhar a imagem e a malha adjacente
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (imagemExibida != null) {
            // Redimensiona a imagem para que não ultrapasse 260x260
            Image imagemRedimensionada = imagemExibida.getScaledInstance(260, 260, Image.SCALE_SMOOTH);

            // Desenha a imagem redimensionada
            g.drawImage(imagemRedimensionada, 180, 160, null);

            // Exibe a malha 3x3 em uma área separada à direita
            if (mouseX >= 0 && mouseY >= 0 && mouseX < imagemExibida.getWidth() && mouseY < imagemExibida.getHeight()) {
                g.setColor(Color.BLACK);
                g.drawString("Valores de Pixel", 900, 150);
            }

            // Exibe a imagem filtrada ao lado da imagem original (também limitando o tamanho)
            if (imagemOutput != null) {
                Image imagemFiltradaRedimensionada = imagemOutput.getScaledInstance(260, 260, Image.SCALE_SMOOTH);
                g.drawImage(imagemFiltradaRedimensionada, 180, 490, null); // Exibe a imagem filtrada em uma posição diferente
            }
        }
    }


    public static void main(String[] args) {
        new Main();
    }
}

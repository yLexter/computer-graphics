import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.LineBorder;
import Filtros.*;
import OpMorfologico.Dilatacao;
import OpMorfologico.Erosao;

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
        Output= new JLabel("Output");

        // Inicializa o JLabel para a matriz máscara e define posição
        mascara = new JLabel("Máscara");
        mascaraLabel = new JLabel();
        mascara.setBounds(573, 330, 60, 30);
        mascaraLabel.setBounds(557, 349, 110, 90); // Ajuste o tamanho conforme necessário

        TelaPrincipal.add(mascaraLabel);


        // Define fontes para os componentes
        Font fonteTitulo = new Font("Impact", Font.BOLD, 24);
        Font fonteBotao = new Font("Impact", Font.PLAIN, 14);

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

        // Adicionar os filtros no menu 1
        mb1.add(escolherFiltroButton);
        escolherFiltroButton.add(f1);
        escolherFiltroButton.add(f2);
        escolherFiltroButton.add(f3);

        //OP morf disponiveis
        dil = new JMenuItem("Dilatação");
        ero = new JMenuItem("Erosão");
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
        mb2.add(escolherMorfologico);
        //mb2.add(dimensaoKernalDilatacao);
        //mb2.add(dimensaoKernalErosao);
        escolherMorfologico.add(dimensaoKernalDilatacao);
        escolherMorfologico.add(dimensaoKernalErosao);
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
        });;

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
        });;

        dil3x3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TelaPrincipal.add(Output);

                Dilatacao dilatacao = new Dilatacao();

                // Verifica se a imagem foi carregada antes de aplicar o filtro
                if (imagemExibida != null) {
                    imagemOutput = dilatacao.operadorDilatacao(imagemExibida,3);   // Chama o filtro na imagem exibida
                    // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                    repaint();  // Repaint para atualizar a exibição da imagem filtrada
                } else {
                    System.out.println("Imagem não carregada.");
                }
            }
        });;

        dil5x5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TelaPrincipal.add(Output);

                Dilatacao dilatacao = new Dilatacao();

                // Verifica se a imagem foi carregada antes de aplicar o filtro
                if (imagemExibida != null) {
                    imagemOutput = dilatacao.operadorDilatacao(imagemExibida,5);   // Chama o filtro na imagem exibida
                    // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                    repaint();  // Repaint para atualizar a exibição da imagem filtrada
                } else {
                    System.out.println("Imagem não carregada.");
                }
            }
        });;

        dil7x7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TelaPrincipal.add(Output);

                Dilatacao dilatacao = new Dilatacao();

                // Verifica se a imagem foi carregada antes de aplicar o filtro
                if (imagemExibida != null) {
                    imagemOutput = dilatacao.operadorDilatacao(imagemExibida,7);   // Chama o filtro na imagem exibida
                    // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                    repaint();  // Repaint para atualizar a exibição da imagem filtrada
                } else {
                    System.out.println("Imagem não carregada.");
                }
            }
        });;

        ero3x3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TelaPrincipal.add(Output);

                Erosao erosao = new Erosao();

                // Verifica se a imagem foi carregada antes de aplicar o filtro
                if (imagemExibida != null) {
                    imagemOutput = erosao.operadorErosao(imagemExibida,3);   // Chama o filtro na imagem exibida
                    // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                    repaint();  // Repaint para atualizar a exibição da imagem filtrada
                } else {
                    System.out.println("Imagem não carregada.");
                }
            }
        });;

        ero5x5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TelaPrincipal.add(Output);

                Erosao erosao = new Erosao();

                // Verifica se a imagem foi carregada antes de aplicar o filtro
                if (imagemExibida != null) {
                    imagemOutput = erosao.operadorErosao(imagemExibida,5);   // Chama o filtro na imagem exibida
                    // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                    repaint();  // Repaint para atualizar a exibição da imagem filtrada
                } else {
                    System.out.println("Imagem não carregada.");
                }
            }
        });;

        ero7x7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TelaPrincipal.add(Output);

                Erosao erosao = new Erosao();

                // Verifica se a imagem foi carregada antes de aplicar o filtro
                if (imagemExibida != null) {
                    imagemOutput = erosao.operadorErosao(imagemExibida,7);   // Chama o filtro na imagem exibida
                    // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                    repaint();  // Repaint para atualizar a exibição da imagem filtrada
                } else {
                    System.out.println("Imagem não carregada.");
                }
            }
        });;

        // Define posições dos componentes setBounds(int x-coordinate, int y-coordinate, int width, int height)
        Titulo.setBounds(425, 55, 390, 36);
        Output.setBounds(255,730,170,24);
        carregarImagemButton.setBounds(215, 410, 170, 30);
        mb1.setBounds(582,177,104,30);
        mb2.setBounds(582,340,170,30);
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
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens PGM e PNG", "pgm", "png");
                JFileChooser selecionarImagem = new JFileChooser();
                selecionarImagem.setFileFilter(filter);

                int result = selecionarImagem.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String caminhoArquivo = selecionarImagem.getSelectedFile().getAbsolutePath();
                    System.out.println("Imagem selecionada: " + caminhoArquivo);

                    if (caminhoArquivo.endsWith(".pgm")) {
                        imagemExibida = carregarImagemPGM(caminhoArquivo);
                    } else if (caminhoArquivo.endsWith(".png")) {
                        try {
                            imagemExibida = ImageIO.read(new File(caminhoArquivo));
                        } catch (IOException ex) {
                            System.out.println("Erro ao carregar a imagem PNG: " + ex.getMessage());
                        }
                    }

                    repaint();  // Chama repaint para atualizar a exibição da imagem
                }
            }
        });

//        escolherFiltroButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//
//        });

        // Adiciona o mouse motion listener para capturar a posição do mouse
        // td bugado a imagem fica piscando sempre que mexe o mouse
//        addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                // Armazena a posição do mouse
//                mouseX = e.getX() - 114;
//                mouseY = e.getY() - 124;
//                repaint();
//            }
//        });

        setVisible(true);
    }

    // Método para carregar imagens PGM (formato P2)
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

    // Método para desenhar a imagem e a malha adjacente
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (imagemExibida != null) {
            // Desenha a imagem
            g.drawImage(imagemExibida, 180, 160, null);



            // Exibe a malha 3x3 em uma área separada à direita
            if (mouseX >= 0 && mouseY >= 0 && mouseX < imagemExibida.getWidth() && mouseY < imagemExibida.getHeight()) {
                g.setColor(Color.BLACK);
                g.drawString("Valores de Pixel", 900, 150);

                // ta td feioso
//                // Desenha uma malha 3x3 de pixels
//                for (int j = -1; j <= 1; j++) {
//                    for (int i = -1; i <= 1; i++) {
//                        int px = Math.min(Math.max(mouseX + i, 0), imagemExibida.getWidth() - 1);
//                        int py = Math.min(Math.max(mouseY + j, 0), imagemExibida.getHeight() - 1);
//                        int cor = new Color(imagemExibida.getRGB(px, py)).getRed();
//
//                        // Desenha cada valor de pixel na posição correta
//                        g.drawRect(900 + (i + 1) * 30, 180 + (j + 1) * 30, 30, 30);
//                        g.drawString(String.valueOf(cor), 910 + (i + 1) * 30, 200 + (j + 1) * 30);
//                    }
//                }
            }

            // Exibe a imagem filtrada ao lado da imagem original
            if (imagemOutput != null) {
                g.drawImage(imagemOutput, 180, 490, null); // Exibe a imagem filtrada em uma posição diferente
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}

package Op_Aritmeticas;

import Filtros.Mediana;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import Funcoes.CarregarImagens;


public class OperadoresWindow extends JFrame {
    BufferedImage imagemExibida1;
    BufferedImage imagemExibida2;
    BufferedImage imagemOutput;
    public OperadoresWindow() {
        // tela op ari
        JPanel TelaArit = new JPanel();
        TelaArit.setLayout(null);
        setContentPane(TelaArit);
        setTitle("Operadores Aritméticos");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);



        Font fonteTitulo = new Font("Impact", Font.BOLD, 24);
        Font fonteBotao = new Font("Impact", Font.PLAIN, 14);
        Font fonteLabel = new Font("Impact", Font.PLAIN, 18);


        JButton carregarImagemButton1 =  new JButton("Carregar Imagem 1");
        JButton carregarImagemButton2 =  new JButton("Carregar Imagem 2");
        JLabel Output = new JLabel("Output");
        carregarImagemButton1.setFont(fonteBotao);
        carregarImagemButton2.setFont(fonteBotao);
        Output.setFont(fonteTitulo);
        carregarImagemButton1.setBounds(105, 466, 170, 30);
        carregarImagemButton2.setBounds(430, 466, 170, 30);
        Output.setBounds(883,466,170,30);


        // Adiciona ação ao botão
        carregarImagemButton1.addActionListener(new ActionListener() {
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
                        imagemExibida1 = CarregarImagens.carregarImagemPGM(caminhoArquivo);
                    } else if (caminhoArquivo.endsWith(".pbm")) {
                        imagemExibida1 = CarregarImagens.carregarImagemPBM(caminhoArquivo);
                    } else if (caminhoArquivo.endsWith(".png")) {
                        imagemExibida1 = CarregarImagens.carregarImagemPNG(caminhoArquivo);
                    }


                    repaint();  // Chama repaint para atualizar a exibição da imagem
                }
            }
        });

        // Adiciona ação ao botão
        carregarImagemButton2.addActionListener(new ActionListener() {
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
                        imagemExibida2 = CarregarImagens.carregarImagemPGM(caminhoArquivo);
                    } else if (caminhoArquivo.endsWith(".pbm")) {
                        imagemExibida2 = CarregarImagens.carregarImagemPBM(caminhoArquivo);
                    } else if (caminhoArquivo.endsWith(".png")) {
                        imagemExibida2 = CarregarImagens.carregarImagemPNG(caminhoArquivo);
                    }


                    repaint();  // Chama repaint para atualizar a exibição da imagem
                }
            }
        });



        // Menu Bar para operações
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Operações Aritméticas");
        JMenuItem soma = new JMenuItem("Soma");
        JMenuItem subtracao = new JMenuItem("Subtração");
        JMenuItem multiplicacao = new JMenuItem("Multiplicação");
        JMenuItem divisao = new JMenuItem("Divisão");

        menuBar.setBounds(658,247,170,30);

        menuBar.setOpaque(false);

        menu.setBorder(new LineBorder(Color.BLACK));
        menu.setOpaque(false);
        menu.setContentAreaFilled(false);
        menu.setFocusPainted(false);
        menu.setFont(fonteBotao);

        carregarImagemButton1.setBorder(new LineBorder(Color.BLACK));
        carregarImagemButton1.setOpaque(false);
        carregarImagemButton1.setContentAreaFilled(false);
        carregarImagemButton1.setFocusPainted(false);

        carregarImagemButton2.setBorder(new LineBorder(Color.BLACK));
        carregarImagemButton2.setOpaque(false);
        carregarImagemButton2.setContentAreaFilled(false);
        carregarImagemButton2.setFocusPainted(false);

        menu.add(soma);
        menu.add(subtracao);
        menu.add(multiplicacao);
        menu.add(divisao);
        menuBar.add(menu);


        soma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagemExibida1 != null && imagemExibida2 != null) {
                    Op_ari operacao = new Op_ari();
                    imagemOutput = operacao.soma(imagemExibida1, imagemExibida2);
                    repaint(); // Atualiza a exibição da imagem resultante
                } else {
                    System.out.println("Ambas as imagens precisam ser carregadas.");
                }
            }
        });

        subtracao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagemExibida1 != null && imagemExibida2 != null) {
                    Op_ari operacao = new Op_ari();
                    imagemOutput = operacao.subtracao(imagemExibida1, imagemExibida2);
                    repaint();
                } else {
                    System.out.println("Ambas as imagens precisam ser carregadas.");
                }
            }
        });

        multiplicacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagemExibida1 != null && imagemExibida2 != null) {
                    Op_ari operacao = new Op_ari();
                    imagemOutput = operacao.multiplicacao(imagemExibida1, imagemExibida2);
                    repaint();
                } else {
                    System.out.println("Ambas as imagens precisam ser carregadas.");
                }
            }
        });

        divisao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagemExibida1 != null && imagemExibida2 != null) {
                    Op_ari operacao = new Op_ari();
                    imagemOutput = operacao.divisao(imagemExibida1, imagemExibida2);
                    repaint();
                } else {
                    System.out.println("Ambas as imagens precisam ser carregadas.");
                }
            }
        });

        //setJMenuBar(menuBar);

        TelaArit.add(carregarImagemButton1);
        TelaArit.add(carregarImagemButton2);
        TelaArit.add(Output);
        TelaArit.add(menuBar);



        // Exibir janela
        setVisible(true);
    }
    // Método para desenhar a imagem e a malha adjacente
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (imagemExibida1 != null) {
            // Redimensiona a imagem para que não ultrapasse 260x260
            Image imagemRedimensionada1 = imagemExibida1.getScaledInstance(260, 260, Image.SCALE_SMOOTH);
            // Desenha a imagem redimensionada
            g.drawImage(imagemRedimensionada1, 60, 160, null);


        }

        if (imagemExibida2 != null) {
            Image imagemRedimensionada2 = imagemExibida2.getScaledInstance(260, 260, Image.SCALE_SMOOTH);

            // Desenha a imagem redimensionada

            g.drawImage(imagemRedimensionada2, 382, 160, null);
        }

        // Exibe a imagem filtrada ao lado da imagem original (também limitando o tamanho)
        if (imagemOutput != null) {
            Image imagemFiltradaRedimensionada = imagemOutput.getScaledInstance(260, 260, Image.SCALE_SMOOTH);
            g.drawImage(imagemFiltradaRedimensionada, 838, 160, null); // Exibe a imagem filtrada em uma posição diferente
        }
    }
}

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.LineBorder;

import Filtros.*;
import OpMorfologico.Abertura;
import OpMorfologico.Dilatacao;
import OpMorfologico.Erosao;
import OpMorfologico.Fechamento;
import Op_Aritmeticas.Op_ari;
import Op_Aritmeticas.OperadoresWindow;
import Op_Logicos.OperadoresWindow2;

import Funcoes.CarregarImagens;
import trans_geometricas.Op_geo;

public class Main extends JFrame {
    // upar iamgem
    private JButton carregarImagemButton;

    // filtros
    private JMenuBar mb1;
    private JMenu escolherFiltroButton;
    private JMenuItem f1, f2, f3, f4,f5,  f6, f7, f8,f9,f10;


    // tela
    private JPanel TelaPrincipal;
    private JLabel Titulo;
    private JLabel Output;
    private JLabel mascara;
    private BufferedImage imagemExibida;
    private BufferedImage imagemOutput;
    private int mouseX = -1, mouseY = -1; // Coordenadas do mouse
    private JLabel mascaraLabel;

    //pra a funcao que personaliza os "botoes"
    AbstractButton componente;

    //operadores morfologicos
    private JMenuBar mb2;
    private JMenu escolherMorfologico;
    private JMenu dimensaoKernalErosao;
    private JMenu dimensaoKernalDilatacao;
    private JMenuItem dil, ero, fec, abe, tophat, bottomhat;
    private JMenuItem ero3x3, ero5x5, ero7x7;
    private JMenuItem dil3x3, dil5x5, dil7x7;


    //operadores aritmeticos
    private JMenuItem operadoresAritmeticos;

    //operadores aritmeticos
    private JMenuItem operadoresLogicos;

    //transformacoes geometricos
    private JMenuBar mb3;
    private JMenu escolherGeometrico;
    private JMenuItem zoomIn, zoomOut, rotacao, reflexao, warping;
    private Image imagemGeometrica;


    public Main() {
        // Inicializa o painel
        TelaPrincipal = new JPanel();
        TelaPrincipal.setLayout(null);

        // Inicializa e configura os componentes
        mb1 = new JMenuBar();
        mb2 = new JMenuBar();
        mb3 = new JMenuBar();


        carregarImagemButton = new JButton("Carregar Imagem");
        escolherFiltroButton = new JMenu("Filtros");


        operadoresAritmeticos= new JMenuItem("Operadores Aritméticos");
        operadoresLogicos= new JMenuItem("Operadores Lógicos");
        escolherGeometrico = new JMenu("Transformações Geométricas");
        escolherMorfologico = new JMenu("Operadores Morfológicos");


        dimensaoKernalErosao = new JMenu("Erosão");
        dimensaoKernalDilatacao = new JMenu("Dilatação");
        Titulo = new JLabel("Processamento de Imagem");
        Output = new JLabel("Output");

        // Inicializa o JLabel para a matriz máscara e define posição
        mascara = new JLabel("Máscara da Média");
        mascaraLabel = new JLabel();
        mascara.setBounds(590, 184, 110, 30); // o texto da mascara
        mascaraLabel.setBounds(595, 210, 110, 90); // matriz da mascara


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
        escolherGeometrico.setFont(fonteBotao);
        operadoresAritmeticos.setFont(fonteBotao);
        operadoresLogicos.setFont(fonteBotao);



        //Os filtros disponiveis
        f1 = new JMenuItem("Filtro da Média");
        f2 = new JMenuItem("Filtro da Mediana");
        f3 = new JMenuItem("Filtro Negativo");
        f4 = new JMenuItem("Transformção Gamma");
        f5 = new JMenuItem("High Boost");
        f6 = new JMenuItem("Operador de Roberts");
        f7 = new JMenuItem("Operador de Sobel");
        f8 = new JMenuItem("Operador de Prewitt");
        f9 = new JMenuItem("Filtro Passa-Alta");

        // Adicionar os filtros no menu 1
        mb1.add(escolherFiltroButton);
        escolherFiltroButton.add(f1);
        escolherFiltroButton.add(f2);
        escolherFiltroButton.add(f3);
        escolherFiltroButton.add(f4);

        escolherFiltroButton.add(f5);

        escolherFiltroButton.add(f6);
        escolherFiltroButton.add(f7);
        escolherFiltroButton.add(f8);
        escolherFiltroButton.add(f9);

        //OP morf disponiveis
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

        // adicionar os op geometricos

        zoomIn = new JMenuItem("Ampliação");
        zoomOut = new JMenuItem("Redução");
        rotacao = new JMenuItem("Rotação");
        reflexao = new JMenuItem("Reflexão");
        warping = new JMenuItem("Warping");


        mb3.add(escolherGeometrico);
        escolherGeometrico.add(zoomIn);
        escolherGeometrico.add(zoomOut);
        escolherGeometrico.add(rotacao);
        escolherGeometrico.add(reflexao);
        escolherGeometrico.add(warping);


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
                            imagemOutput = gamma.gammaFiltro(imagemExibida, cValue, gammaValue);   // Chama o filtro na imagem exibida
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

        f5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Painel para entrada do parâmetro A
                JPanel panel = new JPanel(new GridLayout(1, 2));
                JLabel aLabel = new JLabel("Valor de A: ");
                JTextField aField = new JTextField();

                panel.add(aLabel);
                panel.add(aField);

                // Exibe o diálogo
                int result = JOptionPane.showConfirmDialog(
                        null,
                        panel,
                        "Definir valor de A",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                // Captura o valor inserido pelo usuário
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        double aValue = Double.parseDouble(aField.getText());
                        System.out.println("Valor de A: " + aValue);
                        Media media = new Media();
                        BufferedImage imgPB = media.mediaFiltro(imagemExibida);

                        Op_ari operacao = new Op_ari();
                        BufferedImage MN = operacao.subtracao(imagemExibida, imgPB);

                        HighBoost highBoost = new HighBoost();
                        imagemOutput = highBoost.HB1(imagemExibida,MN, aValue); // Aplica High Boost


                        repaint(); // Atualiza a exibição da imagem resultante

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Por favor, insira um valor numérico válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        f6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TelaPrincipal.add(Output);

                Roberts roberts = new Roberts();

                // Verifica se a imagem foi carregada antes de aplicar o filtro
                if (imagemExibida != null) {
                    imagemOutput = roberts.robertsFiltro(imagemExibida,3);   // Chama o filtro na imagem exibida
                    // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                    repaint();  // Repaint para atualizar a exibição da imagem filtrada
                } else {
                    System.out.println("Imagem não carregada.");
                }
            }
        });

        f7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TelaPrincipal.add(Output);

                Sobel sobel = new Sobel();

                // Verifica se a imagem foi carregada antes de aplicar o filtro
                if (imagemExibida != null) {
                    imagemOutput = sobel.sobelFiltro(imagemExibida);   // Chama o filtro na imagem exibida
                    // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                    repaint();  // Repaint para atualizar a exibição da imagem filtrada
                } else {
                    System.out.println("Imagem não carregada.");
                }
            }
        });
        f8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TelaPrincipal.add(Output);

                Prewitt prewitt = new Prewitt();

                // Verifica se a imagem foi carregada antes de aplicar o filtro
                if (imagemExibida != null) {
                    imagemOutput = prewitt.prewittFiltro(imagemExibida);   // Chama o filtro na imagem exibida
                    // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                    repaint();  // Repaint para atualizar a exibição da imagem filtrada
                } else {
                    System.out.println("Imagem não carregada.");
                }
            }
        });

        f9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TelaPrincipal.add(Output);

                PassaAlta passaAlta = new PassaAlta();

                // Verifica se a imagem foi carregada antes de aplicar o filtro
                if (imagemExibida != null) {
                    imagemOutput = passaAlta.passaAltaFiltro(imagemExibida);   // Chama o filtro na imagem exibida
                    // g.drawImage(media.mediaFiltro(imagemExibida), 180, 497, null);
                    repaint();  // Repaint para atualizar a exibição da imagem filtrada
                } else {
                    System.out.println("Imagem não carregada.");
                }
            }
        });
        ;

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

        tophat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagemExibida != null) {
                    Abertura abertura = new Abertura();
                    BufferedImage imagemAbertura = abertura.operadorAbertura(imagemExibida);

                    Op_ari operacao = new Op_ari();
                    imagemOutput = operacao.subtracao(imagemExibida, imagemAbertura);

                    repaint(); // Atualiza a exibição da imagem resultante
                } else {
                    System.out.println("A imagem precisa ser carregada antes de realizar a operação.");
                }
            }
        });


        bottomhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagemExibida != null) {
                    Fechamento fechamento = new Fechamento();
                    BufferedImage imagemFechamento = fechamento.operadorFechamento(imagemExibida);

                    Op_ari operacao = new Op_ari();
                    imagemOutput = operacao.subtracao(imagemFechamento, imagemExibida);

                    repaint(); // Atualiza a exibição da imagem resultante
                } else {
                    System.out.println("A imagem precisa ser carregada antes de realizar a operação.");
                }
            }
        });


        zoomIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fatorStr = JOptionPane.showInputDialog("Digite o fator de ampliação (ex: 2 para dobrar o tamanho):");
                try {
                    double fator = Double.parseDouble(fatorStr);
                    if (fator <= 0) {
                        JOptionPane.showMessageDialog(null, "O fator deve ser maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (imagemExibida != null) {
                        Op_geo operacao = new Op_geo();
                        exibirImagemEmNovaJanela(operacao.zoomIn(imagemExibida, fator));
                    } else {
                        JOptionPane.showMessageDialog(null, "Imagem não carregada!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um valor numérico válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        zoomOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fatorStr = JOptionPane.showInputDialog("Digite o fator de redução (ex: 2 para reduzir pela metade):");
                try {
                    double fator = Double.parseDouble(fatorStr);
                    if (fator <= 0) {
                        JOptionPane.showMessageDialog(null, "O fator deve ser maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (imagemExibida != null) {
                        Op_geo operacao = new Op_geo();
                        exibirImagemEmNovaJanela(operacao.zoomOut(imagemExibida, fator));
                    } else {
                        JOptionPane.showMessageDialog(null, "Imagem não carregada!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um valor numérico válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        rotacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String anguloStr = JOptionPane.showInputDialog("Digite o ângulo de rotação (em graus):");
                try {
                    double angulo = Double.parseDouble(anguloStr);
                    if (imagemExibida != null) {
                        Op_geo operacao = new Op_geo();
                        exibirImagemEmNovaJanela(operacao.rotacao(imagemExibida, angulo));
                    } else {
                        JOptionPane.showMessageDialog(null, "Imagem não carregada!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um valor numérico válido para o ângulo.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        reflexao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagemExibida != null) {
                    // Criando as opções de reflexão, incluindo o espelhamento
                    String[] opcoes = {
                            "Reflexão Vertical",
                            "Reflexão Diagonal Principal",
                            "Reflexão Diagonal Secundária",
                            "Reflexão Combinada",
                            "Espelhamento Horizontal"  // Nova opção de espelhamento
                    };

                    // Exibindo um comboBox para selecionar a opção de reflexão
                    String opcaoSelecionada = (String) JOptionPane.showInputDialog(
                            null,
                            "Escolha uma opção de reflexão:",
                            "Escolher Reflexão",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            opcoes,
                            opcoes[0]
                    );

                    if (opcaoSelecionada != null) {
                        Op_geo operacao = new Op_geo();
                        BufferedImage imagemResultado = null;

                        // Realizando a operação de reflexão com base na opção selecionada
                        switch (opcaoSelecionada) {
                            case "Reflexão Vertical":
                                imagemResultado = operacao.reflexaoVertical(imagemExibida);
                                break;
                            case "Reflexão Diagonal Principal":
                                imagemResultado = operacao.reflexaoDiagonalPrincipal(imagemExibida);
                                break;
                            case "Reflexão Diagonal Secundária":
                                imagemResultado = operacao.reflexaoDiagonalSecundaria(imagemExibida);
                                break;
                            case "Reflexão Combinada":
                                imagemResultado = operacao.reflexaoCombinada(imagemExibida);
                                break;
                            case "Espelhamento Horizontal":  // Nova opção
                                imagemResultado = operacao.reflexaoHorizontal(imagemExibida);
                                break;
                        }

                        // Exibindo a imagem transformada
                        exibirImagemEmNovaJanela(imagemResultado);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Imagem não carregada!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        warping.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagemExibida != null) {

                    String[] opcoes = {
                            "Escalamento Não Uniforme",
                            "Cisalhamento Horizontal",
                            "Cisalhamento Vertical",
                            "Transformação de Perspectiva",
                            "Curvatura",
                            "Fish Eye",
                            "Swirl",
                            "Grid Distortion",
                            "Ripple Effect"
                    };

                    // Exibindo as opções de Warping
                    String opcaoSelecionada = (String) JOptionPane.showInputDialog(
                            null,
                            "Escolha uma transformação Warping:",
                            "Escolher Warping",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            opcoes,
                            opcoes[0]
                    );

                    if (opcaoSelecionada != null) {
                        Op_geo operacao = new Op_geo();
                        BufferedImage imagemResultado = null;

                        // Realizando a operação de Warping com base na opção selecionada
                        switch (opcaoSelecionada) {
                            case "Escalamento Não Uniforme":
                                imagemResultado = operacao.escalamentoNaoUniforme(imagemExibida, 1.5, 0.8); //  fator
                                break;
                            case "Cisalhamento Horizontal":
                                imagemResultado = operacao.cisalhamentoHorizontal(imagemExibida, 0.5); // fator
                                break;
                            case "Cisalhamento Vertical":
                                imagemResultado = operacao.cisalhamentoVertical(imagemExibida, 0.5); // fator
                                break;
                            case "Transformação de Perspectiva":
                                imagemResultado = operacao.transformacaoPerspectiva(imagemExibida);
                                break;
                            case "Curvatura":
                                imagemResultado = operacao.curvatura(imagemExibida);
                                break;
                            case "Fish Eye":
                                imagemResultado = operacao.fishEye(imagemExibida);
                                break;
                            case "Swirl":
                                imagemResultado = operacao.swirl(imagemExibida, 3.0); //  nível de distorção
                                break;
                            case "Grid Distortion":
                                imagemResultado = operacao.gridDistortion(imagemExibida, 20, 0.5); // gridSize e distortionFactor
                                break;
                            case "Ripple Effect":
                                imagemResultado = operacao.rippleEffect(imagemExibida, 50, 10); // waveLength e amplitude
                                break;
                        }

                        // Exibindo a imagem transformada
                        exibirImagemEmNovaJanela(imagemResultado);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Imagem não carregada!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });




        // Define posições dos componentes setBounds(int x-coordinate, int y-coordinate, int width, int height)
        Titulo.setBounds(425, 55, 390, 36);
        Output.setBounds(255, 730, 170, 24);

        carregarImagemButton.setBounds(215, 410, 180, 30);

        operadoresAritmeticos.setBounds(582, 360, 180, 30);
        operadoresLogicos.setBounds(582, 400, 180, 30);

        mb1.setBounds(582, 137, 104, 30);
        mb2.setBounds(582, 320, 180, 30);
        mb3.setBounds(582, 440, 180, 30);


        // Configurações dos botoes
        configBotao(carregarImagemButton);

        // op arit

        configBotao(operadoresAritmeticos);


        // op log

        configBotao(operadoresLogicos);


        //filtros
        mb1.setBorder(new LineBorder(Color.BLACK));
        mb1.setOpaque(false);


        // op morfologicos
        mb2.setBorder(new LineBorder(Color.BLACK));
        mb2.setOpaque(false);


        // trans geometricas
        mb3.setBorder(new LineBorder(Color.BLACK));
        mb3.setOpaque(false);

        //dimensao do kernal dos op morf
        // config desses "botoes" sao diferentes

        dimensaoKernalDilatacao.setOpaque(false);
        dimensaoKernalDilatacao.setContentAreaFilled(false);
        dimensaoKernalDilatacao.setFocusPainted(false);

        dimensaoKernalErosao.setOpaque(false);
        dimensaoKernalErosao.setContentAreaFilled(false);
        dimensaoKernalErosao.setFocusPainted(false);


        dimensaoKernalErosao.setOpaque(false);
        dimensaoKernalErosao.setContentAreaFilled(false);
        dimensaoKernalErosao.setFocusPainted(false);

        // Adiciona os componentes ao painel
        TelaPrincipal.add(Titulo);
        TelaPrincipal.add(carregarImagemButton);
        //TelaPrincipal.add(escolherFiltroButton);
        //TelaPrincipal.add(escolherMorfologico);
        TelaPrincipal.add(operadoresAritmeticos);
        TelaPrincipal.add(operadoresLogicos);
        TelaPrincipal.add(mb1);
        TelaPrincipal.add(mb2);
        TelaPrincipal.add(mb3);


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
                        imagemExibida = CarregarImagens.carregarImagemPGM(caminhoArquivo);
                    } else if (caminhoArquivo.endsWith(".pbm")) {
                        imagemExibida = CarregarImagens.carregarImagemPBM(caminhoArquivo);
                    } else if (caminhoArquivo.endsWith(".png")) {
                        imagemExibida = CarregarImagens.carregarImagemPNG(caminhoArquivo);
                    }


                    repaint();  // Chama repaint para atualizar a exibição da imagem
                }
            }
        });

        // Ação para abrir a nova janela
        operadoresAritmeticos.addActionListener(e -> new OperadoresWindow());
        operadoresLogicos.addActionListener(e -> new OperadoresWindow2());

        setVisible(true);
    }

    public void configBotao (AbstractButton componente) {
        // Centraliza o texto horizontal e verticalmente
        componente.setHorizontalAlignment(SwingConstants.CENTER);  // Centraliza horizontalmente
        componente.setVerticalAlignment(SwingConstants.CENTER);    // Centraliza verticalmente
        componente.setBorder(new LineBorder(Color.BLACK));      // Define a borda
        componente.setOpaque(false);                             // Deixa o fundo transparente
        componente.setContentAreaFilled(false);                  // Impede o preenchimento do fundo
        componente.setFocusPainted(false);                       // Remove o contorno de foco
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

    public void exibirImagemEmNovaJanela(BufferedImage imagemGeometrica) {
        JFrame frame = new JFrame("Imagem Modificada");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(imagemGeometrica.getWidth(), imagemGeometrica.getHeight());

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagemGeometrica, 0, 0, this);
            }
        };
        panel.setPreferredSize(new Dimension(imagemGeometrica.getWidth(), imagemGeometrica.getHeight()));
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centraliza a janela
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        new Main();
    }
}

package Op_Logicos;

import Funcoes.CarregarImagens;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public class OperadoresWindow2 extends JFrame {
    BufferedImage imagemExibida1;
    BufferedImage imagemExibida2;
    BufferedImage imagemOutput;
    public OperadoresWindow2() {
        // tela op ari
        JPanel TelaArit = new JPanel();
        TelaArit.setLayout(null);
        setContentPane(TelaArit);
        setTitle("Operadores Lógicos");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JScrollPane scrollOriginal1, scrollOriginal2, scrollOutput;
        JTable tabelaOriginal1, tabelaOriginal2, tabelaOutput;

        // Tabela para a imagem original 1
        tabelaOriginal1 = new JTable();
        tabelaOriginal1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Permitir rolagem horizontal
        scrollOriginal1 = new JScrollPane(tabelaOriginal1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollOriginal1.setBounds(39, 430, 344, 300); // Coordenadas e dimensões
        TelaArit.add(scrollOriginal1);

        // Tabela para a imagem original 2
        tabelaOriginal2 = new JTable();
        tabelaOriginal2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Permitir rolagem horizontal
        scrollOriginal2 = new JScrollPane(tabelaOriginal2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollOriginal2.setBounds(390, 430, 344, 300); // Coordenadas e dimensões
        TelaArit.add(scrollOriginal2);

        // Tabela para a imagem resultante
        tabelaOutput = new JTable();
        tabelaOutput.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Permitir rolagem horizontal
        scrollOutput = new JScrollPane(tabelaOutput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollOutput.setBounds(817, 430, 344, 300); // Coordenadas e dimensões
        TelaArit.add(scrollOutput);


        Font fonteTitulo = new Font("Impact", Font.BOLD, 24);
        Font fonteBotao = new Font("Impact", Font.PLAIN, 14);
        Font fonteLabel = new Font("Impact", Font.PLAIN, 18);


        JButton carregarImagemButton1 =  new JButton("Carregar Imagem 1");
        JButton carregarImagemButton2 =  new JButton("Carregar Imagem 2");
        JLabel Output = new JLabel("Output");
        carregarImagemButton1.setFont(fonteBotao);
        carregarImagemButton2.setFont(fonteBotao);
        Output.setFont(fonteTitulo);
        carregarImagemButton1.setBounds(105, 360, 170, 30);
        carregarImagemButton2.setBounds(460, 360, 170, 30);
        Output.setBounds(950,360,170,30);


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
                    updateTables(tabelaOriginal1, imagemExibida1);

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
                    updateTables(tabelaOriginal2, imagemExibida2);

                }
            }
        });



        // Menu Bar para operações
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Operações Lógicas");
        JMenuItem and = new JMenuItem("AND");
        JMenuItem or = new JMenuItem("OR");
        JMenuItem xor = new JMenuItem("XOR");


        menuBar.setBounds(658,247,170,30);

//        menuBar.setBorder(new LineBorder(Color.BLACK));
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

        menu.add(and);
        menu.add(or);
        menu.add(xor);
        menuBar.add(menu);


        and.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagemExibida1 != null && imagemExibida2 != null) {
                    Op_log operacao = new Op_log();
                    imagemOutput = operacao.and(imagemExibida1, imagemExibida2);
                    repaint(); // Atualiza a exibição da imagem resultante
                    updateTables(tabelaOutput, imagemOutput); // Atualiza a tabela de saída

                } else {
                    System.out.println("Ambas as imagens precisam ser carregadas.");
                }
            }
        });

        or.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagemExibida1 != null && imagemExibida2 != null) {
                    Op_log operacao = new Op_log();
                    imagemOutput = operacao.or(imagemExibida1, imagemExibida2);
                    repaint();
                    updateTables(tabelaOutput, imagemOutput); // Atualiza a tabela de saída

                } else {
                    System.out.println("Ambas as imagens precisam ser carregadas.");
                }
            }
        });

        xor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (imagemExibida1 != null && imagemExibida2 != null) {
                    Op_log operacao = new Op_log();
                    imagemOutput = operacao.xor(imagemExibida1, imagemExibida2);
                    repaint();
                    updateTables(tabelaOutput, imagemOutput); // Atualiza a tabela de saída

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


    // Obtém dados de pixel
    private Object[][] getPixelDataAsRows(BufferedImage image) {
        if (image == null) return new Object[0][0];

        int width = image.getWidth();
        int height = image.getHeight();
        Object[][] data = new Object[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                int gray = (pixel >> 16) & 0xFF;
                data[y][x] = gray;
            }
        }
        return data;
    }

    // Gera nomes de colunas
    private String[] generateColumnNames(int width) {
        String[] columnNames = new String[width];
        for (int i = 0; i < width; i++) {
            columnNames[i] = "X" + i;
        }
        return columnNames;
    }

    // Atualizar tabela de pixels
    private void updateTables(JTable tabela, BufferedImage imagem) {
        if (imagem != null) {
            tabela.setModel(new DefaultTableModel(getPixelDataAsRows(imagem), generateColumnNames(imagem.getWidth())));
        }
    }
    // Método para desenhar a imagem e a malha adjacente
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (imagemExibida1 != null) {
            // Redimensiona a imagem para que não ultrapasse 260x260
            Image imagemRedimensionada1 = imagemExibida1.getScaledInstance(260, 260, Image.SCALE_SMOOTH);
            // Desenha a imagem redimensionada
            g.drawImage(imagemRedimensionada1, 60, 120, null);


        }

        if (imagemExibida2 != null) {
            Image imagemRedimensionada2 = imagemExibida2.getScaledInstance(260, 260, Image.SCALE_SMOOTH);

            // Desenha a imagem redimensionada

            g.drawImage(imagemRedimensionada2, 382, 120, null);
        }

        // Exibe a imagem filtrada ao lado da imagem original (também limitando o tamanho)
        if (imagemOutput != null) {
            Image imagemFiltradaRedimensionada = imagemOutput.getScaledInstance(260, 260, Image.SCALE_SMOOTH);
            g.drawImage(imagemFiltradaRedimensionada, 838, 120, null); // Exibe a imagem filtrada em uma posição diferente
        }
    }
}
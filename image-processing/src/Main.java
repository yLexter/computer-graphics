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

public class Main extends JFrame {
    private JButton carregarImagemButton;
    private JPanel TelaPrincipal;
    private JLabel Titulo;
    private BufferedImage imagemExibida;
    private int mouseX = -1, mouseY = -1; // Coordenadas do mouse

    public Main() {
        // Inicializa o painel
        TelaPrincipal = new JPanel();
        TelaPrincipal.setLayout(null);

        // Inicializa e configura os componentes
        carregarImagemButton = new JButton("Carregar Imagem");
        Titulo = new JLabel("Processamento de Imagem");

        // Define fontes para os componentes
        Font fonteTitulo = new Font("Impact", Font.BOLD, 24);
        Font fonteBotao = new Font("Impact", Font.PLAIN, 14);

        // Aplica as fontes aos componentes
        Titulo.setFont(fonteTitulo);
        carregarImagemButton.setFont(fonteBotao);

        // Define posições dos componentes
        Titulo.setBounds(425, 55, 390, 36);
        carregarImagemButton.setBounds(215, 466, 170, 24);

        // Configurações do botão
        carregarImagemButton.setBorder(new LineBorder(Color.BLACK));
        carregarImagemButton.setOpaque(false);
        carregarImagemButton.setContentAreaFilled(false);
        carregarImagemButton.setFocusPainted(false);

        // Adiciona os componentes ao painel
        TelaPrincipal.add(Titulo);
        TelaPrincipal.add(carregarImagemButton);

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

        // Adiciona o mouse motion listener para capturar a posição do mouse
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                // Armazena a posição do mouse
                mouseX = e.getX() - 114;
                mouseY = e.getY() - 124;
                repaint();
            }
        });

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
            int maxValor = scanner.nextInt();

            BufferedImage imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_BYTE_GRAY);

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
            g.drawImage(imagemExibida, 114, 124, null);

            // Exibe a malha 3x3 em uma área separada à direita
            if (mouseX >= 0 && mouseY >= 0 && mouseX < imagemExibida.getWidth() && mouseY < imagemExibida.getHeight()) {
                g.setColor(Color.BLACK);
                g.drawString("Valores de Pixel", 900, 150);

                // Desenha uma malha 3x3 de pixels
                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        int px = Math.min(Math.max(mouseX + i, 0), imagemExibida.getWidth() - 1);
                        int py = Math.min(Math.max(mouseY + j, 0), imagemExibida.getHeight() - 1);
                        int cor = new Color(imagemExibida.getRGB(px, py)).getRed();

                        // Desenha cada valor de pixel na posição correta
                        g.drawRect(900 + (i + 1) * 30, 180 + (j + 1) * 30, 30, 30);
                        g.drawString(String.valueOf(cor), 910 + (i + 1) * 30, 200 + (j + 1) * 30);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}

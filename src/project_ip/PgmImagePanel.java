package project_ip;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PgmImagePanel extends JPanel {
    private JLabel imageLabel;
    private JTextArea arrayTextArea;
    private JScrollPane scrollPane;

    public PgmImagePanel() {
        setLayout(new BorderLayout());

        // Configurando o título do painel
        TitledBorder title = BorderFactory.createTitledBorder("Visualizador de Imagem PGM");
        title.setTitleJustification(TitledBorder.CENTER);
        setBorder(title);

        // Área para exibir a imagem
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // Botão para carregar a imagem
        JButton loadImageButton = new JButton("Carregar Imagem");
        loadImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadImage();
            }
        });
        add(loadImageButton, BorderLayout.SOUTH);

        // Área de texto para mostrar os valores dos pixels
        arrayTextArea = new JTextArea(10, 20);
        arrayTextArea.setEditable(false);
        scrollPane = new JScrollPane(arrayTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.EAST);
    }

    private void loadImage() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                // Carregando a imagem
                BufferedImage image = ImageIO.read(file);
                imageLabel.setIcon(new ImageIcon(image));

                // Exibindo os valores dos pixels
                displayImageValues(image);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao carregar a imagem.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void displayImageValues(BufferedImage image) {
        StringBuilder pixelValues = new StringBuilder();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int grayValue = new Color(image.getRGB(x, y)).getRed();  // PGM é grayscale
                pixelValues.append(grayValue).append(" ");
            }
            pixelValues.append("\n");
        }

        arrayTextArea.setText(pixelValues.toString());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Visualizador de Imagem PGM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new PgmImagePanel());
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

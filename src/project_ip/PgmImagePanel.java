package project_ip;

import project_ip.bases.ImagePGM;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class PgmImagePanel extends JPanel {
    private JLabel originalImageLabel;
    private JLabel filteredImageLabel;
    private JTextArea originalArrayTextArea;
    private JTextArea filteredArrayTextArea;
    private JScrollPane originalScrollPane;
    private JScrollPane filteredScrollPane;
    private ImagePGM originalImage;
    private ImagePGM filteredImage;
    private JComboBox<String> filterSelector;

    public PgmImagePanel() {
        setLayout(new BorderLayout());
        JPanel imagesPanel = new JPanel(new GridLayout(1, 2));

        TitledBorder title = BorderFactory.createTitledBorder("Visualizador de Imagem PGM com Filtros");
        title.setTitleJustification(TitledBorder.CENTER);
        setBorder(title);

        JPanel originalPanel = new JPanel(new BorderLayout());
        originalImageLabel = new JLabel();
        originalImageLabel.setHorizontalAlignment(JLabel.CENTER);
        originalPanel.add(originalImageLabel, BorderLayout.CENTER);

        originalArrayTextArea = new JTextArea(10, 20);
        originalArrayTextArea.setEditable(false);
        originalScrollPane = new JScrollPane(originalArrayTextArea);
        originalScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        originalPanel.add(originalScrollPane, BorderLayout.SOUTH);

        JPanel filteredPanel = new JPanel(new BorderLayout());
        filteredImageLabel = new JLabel();
        filteredImageLabel.setHorizontalAlignment(JLabel.CENTER);
        filteredPanel.add(filteredImageLabel, BorderLayout.CENTER);

        filteredArrayTextArea = new JTextArea(10, 20);
        filteredArrayTextArea.setEditable(false);
        filteredScrollPane = new JScrollPane(filteredArrayTextArea);
        filteredScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        filteredPanel.add(filteredScrollPane, BorderLayout.SOUTH);

        imagesPanel.add(originalPanel);
        imagesPanel.add(filteredPanel);
        add(imagesPanel, BorderLayout.CENTER);

        filterSelector = new JComboBox<>(new String[]{"Nenhum", "Filtro de Borrão", "Filtro de Detecção de Bordas"});
        filterSelector.addActionListener(e -> updateFilteredImage());

        add(filterSelector, BorderLayout.NORTH);

        JButton loadImageButton = new JButton("Carregar Imagem");
        loadImageButton.addActionListener(e -> loadImage());

        add(loadImageButton, BorderLayout.SOUTH);
    }

    private void loadImage() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                BufferedImage image = loadPgmImage(file);

                if (originalImage == null) {
                    this.originalImage = new ImagePGM(image);
                    this.originalImageLabel.setIcon(new ImageIcon(originalImage.getImage()));

                    displayImageValues(originalImage.getImage(), originalArrayTextArea);
                } else {
                    JOptionPane.showMessageDialog(this, "Imagem já carregada.", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar a imagem.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private BufferedImage loadPgmImage(File file) throws IOException {
        try (Scanner scanner = new Scanner(new FileInputStream(file))) {
            String magicNumber = scanner.nextLine();
            if (!magicNumber.equals("P2")) {
                return null;
            }

            while (scanner.hasNext("#")) {
                scanner.nextLine();
            }

            int width = scanner.nextInt();
            int height = scanner.nextInt();
            int maxVal = scanner.nextInt();

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int grayValue = scanner.nextInt() * 255 / maxVal;
                    int rgbValue = new Color(grayValue, grayValue, grayValue).getRGB();
                    image.setRGB(x, y, rgbValue);
                }
            }

            return image;
        }
    }

    private void displayImageValues(BufferedImage image, JTextArea textArea) {
        StringBuilder pixelValues = new StringBuilder();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int grayValue = new Color(image.getRGB(x, y)).getRed();
                pixelValues.append(grayValue).append(" ");
            }

            pixelValues.append("\n");
        }

        textArea.setText(pixelValues.toString());
    }

    private void updateFilteredImage() {
        if (originalImage == null) {
            return;
        }

        String selectedFilter = (String) filterSelector.getSelectedItem();

        float[][] kernel = {
                { 1 / 9, 1 / 9, 1 / 9},
                { 1 / 9, 1 / 9, 1 / 9},
                { 1 / 9, 1 / 9, 1 / 9},
        };

        var image = originalImage.applyConvolution(kernel);

        this.filteredImageLabel.setIcon(new ImageIcon(image));
        displayImageValues(image, filteredArrayTextArea);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Visualizador de Imagem PGM com Filtros");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new PgmImagePanel());
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


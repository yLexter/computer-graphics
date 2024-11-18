package project_ip.imageOperators;
import view.utils.BaseJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ImageOperator extends BaseJPanel {
    private BufferedImage image1;
    private BufferedImage image2;
    private BufferedImage resultImage;
    private int[][] matrix1;
    private int[][] matrix2;
    private int[][] resultMatrix;
    private JLabel imageLabel1;
    private JLabel imageLabel2;
    private JLabel resultLabel;
    private JTextArea matrixArea1;
    private JTextArea matrixArea2;
    private JTextArea resultMatrixArea;

    public ImageOperator() {
        setLayout(new BorderLayout());

        // Painel de Controle com botões e seleção de operação
        JPanel controlPanel = new JPanel();
        JButton loadImage1Button = new JButton("Carregar Imagem 1");
        JButton loadImage2Button = new JButton("Carregar Imagem 2");

        // JComboBox para seleção de operação
        String[] operations = {"Adição", "Subtração", "Multiplicação", "Divisão", "AND", "XOR", "OR"};
        JComboBox<String> operationSelect = new JComboBox<>(operations);
        operationSelect.setSelectedIndex(0);

        // Botão para aplicar operação
        JButton applyOperationButton = new JButton("Aplicar Operação");

        controlPanel.add(loadImage1Button);
        controlPanel.add(loadImage2Button);
        controlPanel.add(operationSelect);
        controlPanel.add(applyOperationButton);

        add(controlPanel, BorderLayout.NORTH);

        // Painel de Imagens e Matrizes
        JPanel imagesPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Espaçamento entre imagens e matrizes

        imageLabel1 = new JLabel();
        imageLabel1.setHorizontalAlignment(JLabel.CENTER);
        imageLabel2 = new JLabel();
        imageLabel2.setHorizontalAlignment(JLabel.CENTER);
        resultLabel = new JLabel();
        resultLabel.setHorizontalAlignment(JLabel.CENTER);

        matrixArea1 = new JTextArea(15, 20);
        matrixArea1.setEditable(false);
        matrixArea2 = new JTextArea(15, 20);
        matrixArea2.setEditable(false);
        resultMatrixArea = new JTextArea(15, 20);
        resultMatrixArea.setEditable(false);

        // Adiciona a imagem 1 e sua matriz ao painel
        gbc.gridx = 0;
        gbc.gridy = 0;
        imagesPanel.add(imageLabel1, gbc);
        gbc.gridy = 1;
        imagesPanel.add(new JScrollPane(matrixArea1), gbc);

        // Adiciona a imagem 2 e sua matriz ao painel
        gbc.gridx = 1;
        gbc.gridy = 0;
        imagesPanel.add(imageLabel2, gbc);
        gbc.gridy = 1;
        imagesPanel.add(new JScrollPane(matrixArea2), gbc);

        // Adiciona a imagem resultante e sua matriz ao painel
        gbc.gridx = 2;
        gbc.gridy = 0;
        imagesPanel.add(resultLabel, gbc);
        gbc.gridy = 1;
        imagesPanel.add(new JScrollPane(resultMatrixArea), gbc);

        add(imagesPanel, BorderLayout.CENTER);

        // Listeners para os botões de carregar imagens
        loadImage1Button.addActionListener(e -> {
            image1 = loadImage();

            if (image1 != null) {
                imageLabel1.setIcon(new ImageIcon(image1));
                matrix1 = getImageMatrix(image1);
                matrixArea1.setText(matrixToString(matrix1));
            }
        });

        loadImage2Button.addActionListener(e -> {
            image2 = loadImage();

            if (image2 != null) {
                imageLabel2.setIcon(new ImageIcon(image2));
                matrix2 = getImageMatrix(image2);
                matrixArea2.setText(matrixToString(matrix2));
            }

        });

        // Listener para aplicar a operação selecionada
        applyOperationButton.addActionListener(e -> {
            if (image1 != null && image2 != null) {
                String operation = (String) operationSelect.getSelectedItem();
                resultImage = applyOperation(operation);
                resultLabel.setIcon(new ImageIcon(resultImage));
                resultMatrix = getImageMatrix(resultImage);
                resultMatrixArea.setText(matrixToString(resultMatrix));
            }
        });
    }

    private BufferedImage loadImage() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                return loadPgmImage(file);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar a imagem.", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }

        return null;
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


    private int[][] getImageMatrix(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] matrix = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = new Color(image.getRGB(x, y)).getRed();
                matrix[y][x] = pixel;
            }
        }
        return matrix;
    }

    private BufferedImage applyOperation(String operation) {
        int width = image1.getWidth();
        int height = image1.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel1 = new Color(image1.getRGB(x, y)).getRed();
                int pixel2 = new Color(image2.getRGB(x, y)).getRed();

                int resultPixel = switch (operation) {
                    case "Adição" -> Math.min(255, pixel1 + pixel2);
                    case "Subtração" -> Math.max(0, pixel1 - pixel2);
                    case "Multiplicação" -> Math.min(255, pixel1 * pixel2);
                    case "Divisão" -> Math.max(0, pixel1 / pixel2);
                    case "XOR" -> Math.max(0, Math.min(255, pixel1 ^ pixel2));
                    case "AND" -> Math.max(0, Math.min(255, pixel1 & pixel2));
                    case "OR" -> Math.max(0, Math.min(255, pixel1 | pixel2));
                    default -> 0;
                };

                result.setRGB(x, y, new Color(resultPixel, resultPixel, resultPixel).getRGB());
            }
        }
        return result;
    }

    private String matrixToString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();

        for (int[] row : matrix) {
            for (int pixel : row) {
                sb.append(String.format("%3d", pixel)).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public ImageOperator  reset() {
        return new ImageOperator();
    }
}

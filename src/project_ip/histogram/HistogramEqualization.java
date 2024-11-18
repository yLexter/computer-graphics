package project_ip.histogram;

import project_cg.geometry.planeCartesians.bases.BaseCartesianPlane;
import view.utils.BaseJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class HistogramEqualization extends BaseJPanel {

    public HistogramEqualization() {
        // Labels para exibir imagens e histogramas
        JLabel originalImageLabel = new JLabel("", JLabel.CENTER);
        JLabel originalHistogramLabel = new JLabel("", JLabel.CENTER);
        JLabel equalizedImageLabel = new JLabel("", JLabel.CENTER);
        JLabel equalizedHistogramLabel = new JLabel("", JLabel.CENTER);

        // Painéis individuais para as imagens e histogramas
        JPanel originalPanel = createImagePanel(originalImageLabel, "Imagem Original");
        JPanel originalHistPanel = createImagePanel(originalHistogramLabel, "Histograma Original");
        JPanel equalizedPanel = createImagePanel(equalizedImageLabel, "Imagem Equalizada");
        JPanel equalizedHistPanel = createImagePanel(equalizedHistogramLabel, "Histograma Equalizado");

        // Painéis organizando os elementos em colunas
        JPanel leftColumn = new JPanel(new GridLayout(2, 1, 10, 10));
        leftColumn.add(originalPanel);
        leftColumn.add(originalHistPanel);

        JPanel rightColumn = new JPanel(new GridLayout(2, 1, 10, 10));
        rightColumn.add(equalizedPanel);
        rightColumn.add(equalizedHistPanel);

        // Painel principal para organizar as colunas lado a lado
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        mainPanel.add(leftColumn);
        mainPanel.add(rightColumn);

        // Configura o layout principal
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);

        // Botão para selecionar imagem
        JButton selectImageButton = new JButton("Selecionar Imagem PGM");
        this.add(selectImageButton, BorderLayout.SOUTH);

        // Adiciona funcionalidade ao botão
        selectImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    // Executa o script Python para processar a imagem
                    List<String> outputPaths = runPythonScript(selectedFile.getAbsolutePath());

                    // Exibe os resultados
                    originalImageLabel.setIcon(resizeImage(new File(outputPaths.get(2))));
                    originalHistogramLabel.setIcon(resizeImage(new File(outputPaths.get(0))));
                    equalizedImageLabel.setIcon(resizeImage(new File(outputPaths.get(3))));
                    equalizedHistogramLabel.setIcon(resizeImage(new File(outputPaths.get(1))));

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Erro ao processar a imagem.");
                }
            }
        });
    }

    private ImageIcon resizeImage(File imageFile) throws IOException {
        BufferedImage originalImage = ImageIO.read(imageFile);
        Image resizedImage = originalImage.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }



    private JPanel createImagePanel(JLabel imageLabel, String text) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label, BorderLayout.NORTH);
        panel.add(imageLabel, BorderLayout.CENTER);
        return panel;
    }

    private List<String> runPythonScript(String inputImagePath) throws IOException, InterruptedException {
        String pythonScript = "src/project_ip/histogram/equalize_histogram.py"; // Nome do script Python
        String outputDir = "src/project_ip/histogram/images"; // Diretório de saída para os arquivos gerados

        // Cria o comando para executar o script Python
        ProcessBuilder processBuilder = new ProcessBuilder(
                "python", pythonScript, inputImagePath, outputDir
        );

        processBuilder.redirectErrorStream(true);

        // Executa o script Python
        Process process = processBuilder.start();

        // Lê os caminhos dos arquivos gerados do stdout do script
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        List<String> outputPaths = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            outputPaths.add(line.trim());
        }

        // Aguarda o término do processo
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Erro ao executar o script Python");
        }

        return outputPaths;
    }

    @Override
    public HistogramEqualization reset() {
        return new HistogramEqualization();
    }
}

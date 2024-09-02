package view.inputsPanel.pixelInputs;

import pixel.Coordinates;
import pixel.PixelCartesianPlane;

import javax.swing.*;
import java.awt.*;

import static pixel.PixelCartesianPlane.SCREEN_HEIGHT;
import static pixel.PixelCartesianPlane.SCREEN_WIDTH;

public class PixelInputs extends JPanel {

    private static JTextField fieldX;
    private static JTextField fieldXm;
    private static JTextField fieldXM;
    private static JTextField fieldY;
    private static JTextField fieldYm;
    private static JTextField fieldYM;
    private static JTextField fieldInicialX;
    private static JTextField fieldFinalX;
    private static JTextField fieldInicialY;
    private static JTextField fieldFinalY;

    private static JPanel createLabeledField(String labelText, Font labelFont, JTextField textField) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        panel.add(label, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);
        return panel;
    }

    public PixelInputs() {
        // Inicializar os campos de texto
        fieldX = new JTextField(10);
        fieldXm = new JTextField(10);
        fieldXM = new JTextField(10);
        fieldY = new JTextField(10);
        fieldYm = new JTextField(10);
        fieldYM = new JTextField(10);
        fieldInicialX = new JTextField(10);
        fieldFinalX = new JTextField(10);
        fieldInicialY = new JTextField(10);
        fieldFinalY = new JTextField(10);

        // Configurar o layout GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.PLAIN, 12); // Fonte um pouco menor

        // Adicionar os campos de texto ao painel
        int row = 0;

        gbc.gridx = 0;
        gbc.gridy = row;
        add(createLabeledField("Digite o valor de X:", labelFont, fieldX), gbc);

        gbc.gridx = 1;
        add(createLabeledField("Digite o valor de Xm:", labelFont, fieldXm), gbc);

        row++;

        gbc.gridx = 0;
        gbc.gridy = row;
        add(createLabeledField("Digite o valor de XM:", labelFont, fieldXM), gbc);

        gbc.gridx = 1;
        add(createLabeledField("Digite o valor de Y:", labelFont, fieldY), gbc);

        row++;

        gbc.gridx = 0;
        gbc.gridy = row;
        add(createLabeledField("Digite o valor de Ym:", labelFont, fieldYm), gbc);

        gbc.gridx = 1;
        add(createLabeledField("Digite o valor de YM:", labelFont, fieldYM), gbc);

        row++;

        gbc.gridx = 0;
        gbc.gridy = row;
        add(createLabeledField("Digite o valor inicial NDC de X:", labelFont, fieldInicialX), gbc);

        gbc.gridx = 1;
        add(createLabeledField("Digite o valor final NDC de X:", labelFont, fieldFinalX), gbc);

        row++;

        gbc.gridx = 0;
        gbc.gridy = row;
        add(createLabeledField("Digite o valor inicial NDC de Y:", labelFont, fieldInicialY), gbc);

        gbc.gridx = 1;
        add(createLabeledField("Digite o valor final NDC de Y:", labelFont, fieldFinalY), gbc);
        row++;

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton calcularButton = new JButton("Calcular");
        calcularButton.setFont(new Font("Arial", Font.BOLD, 16));
        add(calcularButton, gbc);

        calcularButton.addActionListener(e -> {
            // ToDo Arrumare o bug dos NaN do NDY
            double X = Double.parseDouble(fieldX.getText());
            double Xm = Double.parseDouble(fieldXm.getText());
            double XM = Double.parseDouble(fieldXM.getText());
            double Y = Double.parseDouble(fieldY.getText());
            double Ym = Double.parseDouble(fieldYm.getText());
            double YM = Double.parseDouble(fieldYM.getText());

            double coordenadaInicialX = Double.parseDouble(fieldInicialX.getText());
            double coordenadaFinalX = Double.parseDouble(fieldFinalX.getText());
            double coordenadaInicialY = Double.parseDouble(fieldInicialY.getText());
            double coordenadaFinalY = Double.parseDouble(fieldFinalY.getText());

            Coordinates coordenadas = new Coordinates();
            double[] ndcValues = coordenadas.inpToNdc(X, Xm, XM, Y, Ym, YM, coordenadaInicialX, coordenadaFinalX, coordenadaInicialY, coordenadaFinalY);
            double NDCX = ndcValues[0];
            double NDCY = ndcValues[1];

            double[] dcValues = coordenadas.ndcToDc(NDCX, NDCY, SCREEN_WIDTH, SCREEN_HEIGHT, coordenadaInicialX, coordenadaFinalX, coordenadaInicialY, coordenadaFinalY);
            double DCX = dcValues[0];
            double DCY = dcValues[1];

            JFrame resultFrame = new JFrame("Resultado da Transformação");
            resultFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
            resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            PixelCartesianPlane panel = new PixelCartesianPlane((int) DCX, (int) (SCREEN_HEIGHT - DCY), NDCX, NDCY, DCX, DCY);
            resultFrame.add(panel, BorderLayout.CENTER);

            JPanel resultPanel = new JPanel();
            resultPanel.setLayout(new GridLayout(3, 1));

            JLabel ndcLabel = new JLabel(String.format("NDCX: %.3f, NDCY: %.3f", NDCX, NDCY));
            ndcLabel.setFont(labelFont);
            JLabel dcLabel = new JLabel(String.format("DCX: %.3f, DCY: %.3f", DCX, DCY));
            dcLabel.setFont(labelFont);

            resultPanel.add(ndcLabel);
            resultPanel.add(dcLabel);

            resultFrame.add(resultPanel, BorderLayout.SOUTH);
            resultFrame.setVisible(true);
        });

    }
}

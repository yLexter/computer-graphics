package view.inputsPanel.primitivesInputs;

import primitives.TrigometricCircle;

import javax.swing.*;
import java.awt.*;

public class TrigonometricCircleInputs extends JPanel {

    private JTextField radiusField;

    public TrigonometricCircleInputs() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel label = new JLabel("Digite o raio do círculo:");
        add(label, gbc);

        radiusField = new JTextField(15);
        radiusField.setToolTipText("Digite o raio do círculo");
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(radiusField, gbc);

        JButton calculateButton = new JButton("Calcular");
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(calculateButton, gbc);

        calculateButton.addActionListener(e -> {
            try {
                int radius = Integer.parseInt(radiusField.getText());

                JFrame frame = new JFrame("Algoritmo Circulo Trigonometrico");

                TrigometricCircle trigometricCircle = new TrigometricCircle(800, 600);
                trigometricCircle.drawCircle(trigometricCircle.getWidth() / 2, trigometricCircle.getHeight() / 2, radius);
                frame.add(trigometricCircle);

                frame.setSize(trigometricCircle.getWidth(), trigometricCircle.getHeight());

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um número válido.");
            }
        });
    }

}

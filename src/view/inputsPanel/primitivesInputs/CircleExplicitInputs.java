package view.inputsPanel.primitivesInputs;

import primitives.CircleExplicit;
import primitives.DDALine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CircleExplicitInputs extends JPanel {

    private JTextField radiusField;

    public CircleExplicitInputs() {
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

                // ToDo Mais números magicos
                JFrame frame = new JFrame("Algoritmo Circulo Explicito");
                CircleExplicit circleExplicit = new CircleExplicit(800, 600);

                circleExplicit.drawCircle(400, 300, radius);

                frame.add(circleExplicit);
                frame.setSize(800, 600);

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um número válido.");
            }
        });
    }

}


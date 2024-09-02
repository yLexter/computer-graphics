package view.inputsPanel.primitivesInputs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ToDo Talvez adaptar essa classe para as outras Classes

public abstract class ShapePanel extends JPanel {
    protected JButton calculateButton;

    public ShapePanel() {
        setLayout(new GridBagLayout()); // Usando GridBagLayout para centralização
        calculateButton = new JButton("Calcular");
        initializeInputs();
        addCalculateButton();
        calculateButton.addActionListener(e -> onCalculate());
    }

    protected abstract void initializeInputs();

    private void addCalculateButton() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE; // Próxima linha
        gbc.gridwidth = 2; // Botão ocupa duas colunas
        gbc.insets = new Insets(10, 0, 0, 0); // Margem superior de 10px
        gbc.anchor = GridBagConstraints.CENTER;
        add(calculateButton, gbc);
    }

    protected void addInputField(String labelText, JTextField textField, int gridy) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margem entre componentes
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(textField, gbc);
    }

    protected abstract void onCalculate();
}

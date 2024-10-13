package view.inputsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ToDo Talvez adaptar essa classe para as outras Classes

public abstract class ShapePanel extends JPanel {
    protected JButton calculateButton;
    private int currentGridY; // To keep track of the current row

    public ShapePanel() {
        setLayout(new GridBagLayout());
        calculateButton = new JButton("Calcular");
        currentGridY = 0; // Start at the first row

        initializeInputs();
        addCalculateButton();
        calculateButton.addActionListener(e -> onCalculate());
    }

    // Abstract method to be implemented by subclasses for adding inputs
    protected abstract void initializeInputs();

    // Method to add the "Calculate" button in the last row
    private void addCalculateButton() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = currentGridY++; // Use the current row and then increment
        gbc.gridwidth = 2; // The button spans two columns
        gbc.insets = new Insets(10, 0, 0, 0); // Top margin of 10px
        gbc.anchor = GridBagConstraints.CENTER;
        add(calculateButton, gbc);
    }

    // Method to add a label and text input field
    protected void addInputField(String labelText, JTextField textField) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margin between components
        gbc.anchor = GridBagConstraints.CENTER;

        // Label
        gbc.gridx = 0;
        gbc.gridy = currentGridY; // Use current row for label
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel(labelText), gbc);

        // Text field
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(textField, gbc);

        currentGridY++; // Move to the next row after adding the input field
    }

    protected void addComboBox(String labelText, JComboBox<String> comboBox) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margin between components
        gbc.anchor = GridBagConstraints.CENTER;
        // Label
        gbc.gridx = 0;
        gbc.gridy = currentGridY; // Use current row for label
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel(labelText), gbc);

        // Combo box
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(comboBox, gbc);

        currentGridY++; // Move to the next row after adding the combo box
    }

    // Abstract method for calculating action
    protected abstract void onCalculate();

    public void resetAll() {
        removeAll();

        // Mesma coisa que o construtor
        setLayout(new GridBagLayout());
        calculateButton = new JButton("Calcular");
        currentGridY = 0; // Start at the first row

        initializeInputs();
        addCalculateButton();
        calculateButton.addActionListener(e -> onCalculate());

        revalidate();
        repaint();
    }
}

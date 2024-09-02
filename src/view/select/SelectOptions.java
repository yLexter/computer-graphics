package view.select;

import view.inputsPanel.DataOptions;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;


public class SelectOptions extends JPanel {
    private static class OptionPanel extends JPanel {
        public OptionPanel() {
            setLayout(new BorderLayout());
        }

        public void updatePanel(JPanel newPanel) {
            removeAll();

            if (newPanel != null) {
                add(newPanel, BorderLayout.CENTER);
            }

            revalidate();
            repaint();
        }
    }

    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private DefaultComboBoxModel<String> modelComboBox2;
    private OptionPanel optionPanel;
    private final DataOptions dataOptions;

    public SelectOptions(DataOptions dataOptions) {
        this.dataOptions = dataOptions;

        drawPanel();
    }

    public void drawPanel() {
        setLayout(new BorderLayout());

        optionPanel = new OptionPanel();
        add(optionPanel, BorderLayout.NORTH);

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new GridLayout(1, 2, 10, 10));
        add(comboBoxPanel, BorderLayout.SOUTH);

        comboBox1 = new JComboBox<>(dataOptions.getFirstComboBoxOptions());
        comboBoxPanel.add(comboBox1);

        modelComboBox2 = new DefaultComboBoxModel<>();
        comboBox2 = new JComboBox<>(modelComboBox2);
        comboBoxPanel.add(comboBox2);

        comboBox1.addActionListener(e -> {
            String selectedCategory = (String) comboBox1.getSelectedItem();
            updateComboBox2(dataOptions.getSecondComboBoxOptions(selectedCategory));
        });

        comboBox2.addActionListener(e -> {
            String selectedOption = (String) comboBox2.getSelectedItem();
            optionPanel.updatePanel(dataOptions.getPanelInputsForOption(selectedOption));
        });

        updateComboBox2(
                dataOptions.getSecondComboBoxOptions(
                        Objects.requireNonNull(comboBox1.getSelectedItem()).toString())
        );
    }

    private void updateComboBox2(String[] items) {
        modelComboBox2.removeAllElements();

        for (String item : items) {
            modelComboBox2.addElement(item);
        }
    }

}



package view.select;

import project_cg.geometry.planeCartesians.bases.BaseCartesianPlane2D;
import utils.Constants;
import view.mainScreen.MainScreen;
import view.inputsPanel.DataOptions;
import project_cg.geometry.planeCartesians.bases.BaseCartesianPlane;
import view.mainScreen.MainScreenSingleton;

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

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new GridLayout(1, 2, 10, 10));
        add(comboBoxPanel, BorderLayout.SOUTH);

        comboBox1 = new JComboBox<>();

        comboBox1.addItem(Constants.DEFAULT_OPTION_CHECKBOX);

        for (String item : dataOptions.getFirstComboBoxOptions()) {
            comboBox1.addItem(item);
        }
        comboBoxPanel.add(comboBox1);

        modelComboBox2 = new DefaultComboBoxModel<>();
        comboBox2 = new JComboBox<>(modelComboBox2);
        comboBoxPanel.add(comboBox2);

        comboBox2.setEnabled(false);

        comboBox1.addActionListener(e -> {
            String selectedCategory = (String) comboBox1.getSelectedItem();

            if (Constants.DEFAULT_OPTION_CHECKBOX.equals(selectedCategory)) {
                comboBox2.setEnabled(false);
                modelComboBox2.removeAllElements();
                optionPanel.updatePanel(null);
            } else {
                comboBox2.setEnabled(true);
                MainScreen mainScreen = MainScreenSingleton.getMainScreen();
                updateComboBox2(dataOptions.getSecondComboBoxOptions(selectedCategory));

                mainScreen.changeCartesianPlaneScreen(selectedCategory);
            }
        });

        comboBox2.addActionListener(e -> {
            String selectedOption = (String) comboBox2.getSelectedItem();

            if (selectedOption != null && !Constants.DEFAULT_OPTION_CHECKBOX.equals(selectedOption)) {
                optionPanel.updatePanel(dataOptions.getPanelInputsForOption(selectedOption));

                JFrame inputsFrame = new JFrame();
                inputsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                inputsFrame.setSize(600, 300);
                inputsFrame.add(optionPanel);

                optionPanel.revalidate();
                optionPanel.repaint();

                inputsFrame.setVisible(true);

            }

        });

        updateComboBox2(
                dataOptions.getSecondComboBoxOptions(
                        Objects.requireNonNull(comboBox1.getSelectedItem()).toString())
        );
    }

    private void updateComboBox2(String[] items) {
        modelComboBox2.removeAllElements();
        modelComboBox2.addElement(Constants.DEFAULT_OPTION_CHECKBOX);

        for (String item : items) {
            modelComboBox2.addElement(item);
        }

        comboBox2.setSelectedIndex(0);
    }
}

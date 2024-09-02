package view.inputsPanel;

import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataOptions {

    private final Map<String, Map<String, JPanel>> data;

    public DataOptions() {
        data = new LinkedHashMap<>();
    }

    public void addOption(String category, String option, JPanel panelInputs) {
        data.computeIfAbsent(category, k -> new LinkedHashMap<>()).put(option, panelInputs);
    }

    public String[] getFirstComboBoxOptions() {
        return data.keySet().toArray(new String[0]);
    }

    public String[] getSecondComboBoxOptions(String category) {
        return data.containsKey(category) ? data.get(category).keySet().toArray(new String[0]) : new String[0];
    }

    public JPanel getPanelInputsForOption(String option) {

        for (Map<String, JPanel> options : data.values()) {
            if (options.containsKey(option)) {
                return options.get(option);
            }
        }

        return null;
    }
}


package view;


import primitives.CircleExplicit;
import view.inputsPanel.pixelInputs.PixelInputs;
import view.inputsPanel.primitivesInputs.CircleExplicitInputs;
import view.inputsPanel.primitivesInputs.DDALineInputs;
import view.inputsPanel.DataOptions;
import view.inputsPanel.primitivesInputs.TrigonometricCircleInputs;
import view.select.SelectOptions;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {

    public MainScreen() {
        setTitle("Figure Designer");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        DataOptions dataOptions = new DataOptions();

        dataOptions.addOption("Pixel", "Desenhar Pixel", new PixelInputs());
        dataOptions.addOption("Primitivas", "DDA", new DDALineInputs());
        dataOptions.addOption("Primitivas", "Equação explicita da circunferência ", new CircleExplicitInputs());
        dataOptions.addOption("Primitivas", "Ponto médio da circunferência", new TrigonometricCircleInputs());

        add(new SelectOptions(dataOptions), BorderLayout.CENTER);

        setVisible(true);
    }


}

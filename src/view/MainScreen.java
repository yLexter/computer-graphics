package view;


import primitives.CircleExplicit;
import view.inputsPanel.pixelInputs.PixelInputs;
import view.inputsPanel.primitivesInputs.*;
import view.inputsPanel.DataOptions;
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
        dataOptions.addOption("Primitivas", "Método trigonométrico da circunferência\n", new TrigonometricCircleInputs());

        dataOptions.addOption("Primitivas", "Ponto médio da circunferência", new MidpointCircleInputs());
        dataOptions.addOption("Primitivas", "Ponto médio das Retas", new MidpointLineInputs());
        dataOptions.addOption("Primitivas", "Ponto médio da Elipse", new MidpointElipseInputs());

        add(new SelectOptions(dataOptions), BorderLayout.CENTER);

        setVisible(true);
    }


}

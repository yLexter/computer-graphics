package view.mainScreen.mainScreenPanels;

import view.mainScreen.MainScreen;
import view.inputsPanel.DataOptions;
import view.inputsPanel.primitivesInputs.*;
import view.inputsPanel.transformations2dinputs.*;
import geomtry.planeCartesians.CartesianPlane2D;
import geomtry.planeCartesians.PixelCartesianPlane;
import view.select.SelectOptions;
import view.mainScreen.MainScreenSingleton;

import javax.swing.*;
import java.awt.*;

public class InputsPanel extends JPanel {
    private JButton clearButton = new JButton("Limpar");

    public InputsPanel() {
        MainScreen mainScreen = MainScreenSingleton.getMainScreen();

        setLayout(new BorderLayout());

        DataOptions dataOptions = new DataOptions();

        // Criação de novas instâncias de CartesianPlane2D e PixelCartesianPlane
        CartesianPlane2D primitivaPlane = new CartesianPlane2D();
        CartesianPlane2D transformacoesPlane = new CartesianPlane2D();
        PixelCartesianPlane pixelPlane = new PixelCartesianPlane();

        // Adicionando os planos cartesianos
        mainScreen.cartesianPlaneHandler.addCartesianPlane("Primitivas", primitivaPlane);
        mainScreen.cartesianPlaneHandler.addCartesianPlane("Transformações", transformacoesPlane);
        mainScreen.cartesianPlaneHandler.addCartesianPlane("Pixel", pixelPlane);

        // Criação de instâncias de inputs para transformações
        CreateCircleInputs circleInputs = new CreateCircleInputs();
        CreatePolygonInputs polygonInputs = new CreatePolygonInputs();
        CreateEllipseInputs ellipseInputs = new CreateEllipseInputs();
        RotationInputs rotationInputs = new RotationInputs();
        ScaleInputs scaleInputs = new ScaleInputs();
        ShearInputs shearInputs = new ShearInputs();
        TranslationInputs translationInputs = new TranslationInputs();
        ReflectionInputs reflectionInputs = new ReflectionInputs();

        // Adicionando as opções de transformação
        dataOptions.addOption("Transformações", "Desenhar Circulo", circleInputs);
        dataOptions.addOption("Transformações", "Desenhar Poligno", polygonInputs);
        dataOptions.addOption("Transformações", "Desenhar Ellipse", ellipseInputs);
        dataOptions.addOption("Transformações", "Aplicar Rotação", rotationInputs);
        dataOptions.addOption("Transformações", "Aplicar Escala", scaleInputs);
        dataOptions.addOption("Transformações", "Aplicar Cisalhamento", shearInputs);
        dataOptions.addOption("Transformações", "Aplicar Translação", translationInputs);
        dataOptions.addOption("Transformações", "Aplicar Reflexão", reflectionInputs);

        // Criação de instâncias de inputs para primitivas
        DDALineInputs ddaLineInputs = new DDALineInputs();
        CircleExplicitInputs circleExplicitInputs = new CircleExplicitInputs();
        TrigonometricCircleInputs trigonometricCircleInputs = new TrigonometricCircleInputs();
        MidpointCircleInputs midpointCircleInputs = new MidpointCircleInputs();
        MidpointLineInputs midpointLineInputs = new MidpointLineInputs();
        MidpointElipseInputs midpointElipseInputs = new MidpointElipseInputs();

        // Adicionando as opções de primitivas
        dataOptions.addOption("Primitivas", "DDA", ddaLineInputs);
        dataOptions.addOption("Primitivas", "Equação explicita da circunferência", circleExplicitInputs);
        dataOptions.addOption("Primitivas", "Método trigonométrico da circunferência", trigonometricCircleInputs);
        dataOptions.addOption("Primitivas", "Ponto médio da circunferência", midpointCircleInputs);
        dataOptions.addOption("Primitivas", "Ponto médio das Retas", midpointLineInputs);
        dataOptions.addOption("Primitivas", "Ponto médio da Elipse", midpointElipseInputs);


        add(new SelectOptions(dataOptions), BorderLayout.CENTER);

        add(clearButton, BorderLayout.SOUTH);

        clearButton.addActionListener(e-> {
             mainScreen.resetCartesianPlane();
        });

        setVisible(true);
    }


}

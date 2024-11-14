package view.mainScreen.mainScreenPanels;

import project_cg.ecgSimulator.ECGSimulation;
import project_cg.inputsPanel.ecgInputs.ECGSimulationInputs;
import project_cg.geometry.planeCartesians.windowViewport.WindowViewport;
import view.mainScreen.MainScreen;
import view.utils.DataOptions;
import project_cg.inputsPanel.primitivesInputs.*;
import project_cg.inputsPanel.transformations2dinputs.*;
import project_cg.geometry.planeCartesians.CartesianPlane2D;
import project_cg.geometry.planeCartesians.PixelCartesianPlane;
import view.select.SelectOptions;
import view.mainScreen.MainScreenSingleton;
import view.utils.GeometricFiguresHandler;

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
        WindowViewport windowViewport = new WindowViewport();
        ECGSimulation ecgSimulation = new ECGSimulation();

        // Adicionando os planos cartesianos
        mainScreen.JPanelHandler.addJPanel("Primitivas", primitivaPlane);
        mainScreen.JPanelHandler.addJPanel("Transformações", transformacoesPlane);
        mainScreen.JPanelHandler.addJPanel("Pixel", pixelPlane);
        mainScreen.JPanelHandler.addJPanel("Janela p/ Viewport", windowViewport);
        mainScreen.JPanelHandler.addJPanel("Simulador de Coracão", ecgSimulation);

        // ToDo fluxo, não mexer p n estourar o codigo
        mainScreen.setGeometricFiguresHandler(new GeometricFiguresHandler(mainScreen.getCartesianPlaneHandler()));

        // Criação de instâncias de inputs para transformações
        CreatePolygonInputs polygonInputs = new CreatePolygonInputs();
        RotationInputs rotationInputs = new RotationInputs();
        ScaleInputs scaleInputs = new ScaleInputs();
        ShearInputs shearInputs = new ShearInputs();
        TranslationInputs translationInputs = new TranslationInputs();
        ReflectionInputs reflectionInputs = new ReflectionInputs();

        // Criação de instâncias de inputs para primitivas
        DDALineInputs ddaLineInputs = new DDALineInputs();
        CircleExplicitInputs circleExplicitInputs = new CircleExplicitInputs();
        TrigonometricCircleInputs trigonometricCircleInputs = new TrigonometricCircleInputs();
        MidpointCircleInputs midpointCircleInputs = new MidpointCircleInputs();
        MidpointLineInputs midpointLineInputs = new MidpointLineInputs();
        MidpointElipseInputs midpointElipseInputs = new MidpointElipseInputs();

        // Input do ECG
        ECGSimulationInputs ecgSimulationInputs = new ECGSimulationInputs();

        // Adicioanr Lena Options

        // dataOptions.addOption("Lena Filtro", "Teste", midpointCircleInputs);
        // Adicionando as opções de transformação
        // dataOptions.addOption("Transformações", "Desenhar Circulo", midpointCircleInputs);
        //dataOptions.addOption("Transformações", "Desenhar Ellipse", midpointElipseInputs);


        dataOptions.addOption("Transformações", "Desenhar Reta", midpointLineInputs);

        dataOptions.addOption("Transformações", "Desenhar Quadrado", polygonInputs);
        dataOptions.addOption("Transformações", "Aplicar Rotação", rotationInputs);
        dataOptions.addOption("Transformações", "Aplicar Escala", scaleInputs);
        dataOptions.addOption("Transformações", "Aplicar Cisalhamento", shearInputs);
        dataOptions.addOption("Transformações", "Aplicar Translação", translationInputs);
        dataOptions.addOption("Transformações", "Aplicar Reflexão", reflectionInputs);

        // Adicionando as opções de primitivas
        dataOptions.addOption("Primitivas", "DDA", ddaLineInputs);
        dataOptions.addOption("Primitivas", "Equação explicita da circunferência", circleExplicitInputs);
        dataOptions.addOption("Primitivas", "Método trigonométrico da circunferência", trigonometricCircleInputs);
        dataOptions.addOption("Primitivas", "Ponto médio da circunferência", midpointCircleInputs);
        dataOptions.addOption("Primitivas", "Ponto médio das Retas", midpointLineInputs);
        dataOptions.addOption("Primitivas", "Ponto médio da Elipse", midpointElipseInputs);

        // Adicionando opçoes da janela-viewport
        dataOptions.addOption("Janela p/ Viewport", "Desenhar Círculo", midpointCircleInputs);
        dataOptions.addOption("Janela p/ Viewport", "Desenhar Reta", midpointLineInputs);
        dataOptions.addOption("Janela p/ Viewport", "Desenhar Ellipse", midpointElipseInputs);
        dataOptions.addOption("Janela p/ Viewport", "Aplicar Rotação", rotationInputs);
        dataOptions.addOption("Janela p/ Viewport", "Aplicar Escala", scaleInputs);
        dataOptions.addOption("Janela p/ Viewport", "Aplicar Cisalhamento", shearInputs);
        dataOptions.addOption("Janela p/ Viewport", "Aplicar Translação", translationInputs);
        dataOptions.addOption("Janela p/ Viewport", "Aplicar Reflexão", reflectionInputs);

        // Opções do ECG
        dataOptions.addOption("Simulador de Coracão", "Definir Tempo", ecgSimulationInputs);

        add(new SelectOptions(dataOptions), BorderLayout.CENTER);

        add(clearButton, BorderLayout.SOUTH);

        clearButton.addActionListener(e-> {
             mainScreen.resetCartesianPlane();
        });

        setVisible(true);
    }


}

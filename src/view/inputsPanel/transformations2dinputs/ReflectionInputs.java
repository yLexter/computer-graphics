package view.inputsPanel.transformations2dinputs;

import geometry.figures.BaseFigure;
import geometry.planeCartesians.CartesianPlane2D;
import geometry.points.Point2D;
import transformations2d.Reflection;
import view.inputsPanel.ShapePanel;
import view.mainScreen.MainScreen;
import view.mainScreen.MainScreenSingleton;

import javax.swing.JComboBox;
import java.util.Objects;
import java.util.function.Function;

public class ReflectionInputs extends ShapePanel {
    private JComboBox<String> reflectionTypeComboBox;
    private JComboBox<String> comboBoxFigures;


    @Override
    protected void initializeInputs() {
        reflectionTypeComboBox = new JComboBox<>(new String[]{"X", "Y", "Origem", "YX"});
        comboBoxFigures = MainScreenSingleton.getComboBoxGeometriFigures();

        addComboBox("Escolha uma figura", comboBoxFigures);
        addComboBox("Tipo de Reflexão:", reflectionTypeComboBox);
    }

    @Override
    protected void onCalculate() {
        String reflectionType = (String) reflectionTypeComboBox.getSelectedItem();

        MainScreen mainScreen = MainScreenSingleton.getMainScreen();

        Function<Point2D, Point2D> reflectionFunnction = switch (Objects.requireNonNull(reflectionType)) {
            case "X" -> Reflection::reflectPpintInX;
            case "Y" -> Reflection::reflectPpintInY;
            case "Origem" -> Reflection::reflectPpintInOrigin;
            default -> null;
        };

        String squareSelected = (String) comboBoxFigures.getSelectedItem();
        BaseFigure figure = mainScreen.geometricFiguresHandler.getFigureByID(squareSelected);

        figure.getVertex(
                point2D -> {
                    assert reflectionFunnction != null;
                    Point2D pointReflected = reflectionFunnction.apply(point2D);
                    point2D.updatePoint(pointReflected);
                }
        );

        mainScreen.updateFigures();
    }
}



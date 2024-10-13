package view.inputsPanel.transformations2dinputs;

import geomtry.figures.BaseFigure;
import geomtry.planeCartesians.CartesianPlane2D;
import geomtry.points.Point2D;
import transformations2d.Reflection;
import transformations2d.Shear;
import utils.Constants;
import view.inputsPanel.ShapePanel;
import view.mainScreen.MainScreen;
import view.mainScreen.MainScreenSingleton;

import javax.swing.*;
import java.util.Objects;
import java.util.function.Function;

public class ShearInputs extends ShapePanel {
    private JComboBox<String> shearTypeComboBox;
    private JComboBox<String> comboBoxFigures;
    private JTextField inputA, inputB;

    @Override
    protected void initializeInputs() {
        shearTypeComboBox = new JComboBox<>(new String[]{"X", "Y", "XY"});
        comboBoxFigures = MainScreenSingleton.getComboBoxGeometriFigures();

        addComboBox("Escolha uma figura", comboBoxFigures);
        addComboBox("Tipo de Cisalhamento:", shearTypeComboBox);

        inputA = new JTextField(10);
        inputB = new JTextField(10);

        addInputField("Valor de a:", inputA);
        addInputField("Valor de b:", inputB);
    }

    @Override
    protected void onCalculate() {

        double a = Double.parseDouble(inputA.getText());
        double b = Double.parseDouble(inputB.getText());

        String shearType = (String) shearTypeComboBox.getSelectedItem();

        MainScreen mainScreen = MainScreenSingleton.getMainScreen();
        CartesianPlane2D cartesianPlanePanel = MainScreenSingleton.getCartesianPlane2D();

        String squareSelected = (String) comboBoxFigures.getSelectedItem();
        BaseFigure figure = mainScreen.geometricFiguresHandler.getFigureByID(squareSelected);

        figure.iterateToPoints(
                point2D -> {
                    assert shearType != null;

                    Point2D pointReflected;

                    if (shearType.equals("X")) {
                       pointReflected = Shear.shearX(point2D, a);
                    } else if (shearType.equals("Y")) {
                        pointReflected = Shear.shearY(point2D, b);
                    } else {
                        pointReflected = Shear.shearXY(point2D, a, b);
                    }

                    point2D.updatePoint(pointReflected);
                }
        );

        mainScreen.updateFigures();
    }

}

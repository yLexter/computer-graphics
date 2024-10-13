package view.inputsPanel.transformations2dinputs;

import geomtry.figures.BaseFigure;
import geomtry.planeCartesians.CartesianPlane2D;
import geomtry.points.Point2D;
import transformations2d.Rotation;
import transformations2d.Scale;
import utils.Constants;
import view.inputsPanel.ShapePanel;
import view.mainScreen.MainScreen;
import view.mainScreen.MainScreenSingleton;

import javax.swing.*;

public class RotationInputs extends ShapePanel {
    private JTextField angleInput;

    private JComboBox<String> comboBoxFigures;

    @Override
    protected void initializeInputs() {
        angleInput = new JTextField(10);
        comboBoxFigures = MainScreenSingleton.getComboBoxGeometriFigures();

        addComboBox("Escolha uma figura", comboBoxFigures);
        addInputField("Ângulo de Rotação:", angleInput);
    }

    @Override
    protected void onCalculate() {
        int angle = Integer.parseInt(angleInput.getText());

        MainScreen mainScreen = MainScreenSingleton.getMainScreen();
        CartesianPlane2D cartesianPlanePanel = MainScreenSingleton.getCartesianPlane2D();

        String figureSelected = (String) comboBoxFigures.getSelectedItem();
        BaseFigure figure = mainScreen.geometricFiguresHandler.getFigureByID(figureSelected);

        figure.iterateToPoints(
                point2D -> {
                    Point2D pointRotated = Rotation.rotatePoint(point2D, angle);
                    point2D.updatePoint(pointRotated);
                }
        );

        mainScreen.updateFigures();
    }
}


package view.inputsPanel.transformations2dinputs;

import geomtry.figures.BaseFigure;
import geomtry.figures.Square;
import geomtry.planeCartesians.CartesianPlane2D;
import geomtry.points.Point2D;
import transformations2d.Scale;
import utils.Constants;
import view.inputsPanel.ShapePanel;
import view.mainScreen.MainScreen;
import view.mainScreen.MainScreenSingleton;

import javax.swing.*;

public class ScaleInputs extends ShapePanel {
    private JTextField scaleX, scaleY;

    private JComboBox<String> comboBoxFigures;

    @Override
    protected void initializeInputs() {
        scaleX = new JTextField(10);
        scaleY = new JTextField(10);

        comboBoxFigures = MainScreenSingleton.getComboBoxGeometriFigures();

        addComboBox("Escolha uma figura", comboBoxFigures);
        addInputField("Escala X:", scaleX);
        addInputField("Escala Y:", scaleY);
    }

    @Override
    protected void onCalculate() {
        double sx = Double.parseDouble(scaleX.getText());
        double sy = Double.parseDouble(scaleY.getText());

        MainScreen mainScreen = MainScreenSingleton.getMainScreen();

        String figureSelected = (String) comboBoxFigures.getSelectedItem();
        BaseFigure figure = mainScreen.geometricFiguresHandler.getFigureByID(figureSelected);

        figure.iterateToPoints(
                point2D -> {
                    Point2D pointScaled = Scale.scalePoint(point2D, sx, sy);
                    point2D.updatePoint(pointScaled);
                }
        );

        mainScreen.updateFigures();
    }
}


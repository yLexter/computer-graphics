package view.inputsPanel.transformations2dinputs;

import geomtry.figures.BaseFigure;
import geomtry.planeCartesians.CartesianPlane2D;
import geomtry.points.Point2D;
import transformations2d.Scale;
import transformations2d.Translation;
import utils.Constants;
import view.inputsPanel.ShapePanel;
import view.mainScreen.MainScreen;
import view.mainScreen.MainScreenSingleton;

import javax.swing.*;
import java.awt.*;

public class TranslationInputs extends ShapePanel {
    private JTextField translationX, translationY;

    private JComboBox<String> comboBoxFigures;
    @Override
    protected void initializeInputs() {
        translationX = new JTextField(10);
        translationY = new JTextField(10);
        comboBoxFigures = MainScreenSingleton.getComboBoxGeometriFigures();

        addComboBox("Escolha uma figura", comboBoxFigures);
        addInputField("Translação X:", translationX);
        addInputField("Translação Y:", translationY);
    }

    @Override
    protected void onCalculate() {
        int tx = Integer.parseInt(translationX.getText());
        int ty = Integer.parseInt(translationY.getText());

        MainScreen mainScreen = MainScreenSingleton.getMainScreen();

        String figureSelected = (String) comboBoxFigures.getSelectedItem();
        BaseFigure figure = mainScreen.geometricFiguresHandler.getFigureByID(figureSelected);

        figure.iterateToPoints(
                point2D -> {
                    Point2D pointTransladed = Translation.translatePoint(point2D, tx, ty);
                    point2D.updatePoint(pointTransladed);
                }
        );

        mainScreen.updateFigures();
    }

}

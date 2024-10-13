package view.inputsPanel.transformations2dinputs;

import geomtry.figures.Circle;
import geomtry.planeCartesians.CartesianPlane2D;
import utils.Constants;
import view.inputsPanel.ShapePanel;
import view.mainScreen.MainScreen;
import view.mainScreen.MainScreenSingleton;

import javax.swing.*;

public class CreateCircleInputs extends ShapePanel {
    private JTextField radiusField;
    private JTextField centerXField;
    private JTextField centerYField;

    @Override
    protected void initializeInputs() {
        centerXField = new JTextField(15);
        centerYField = new JTextField(15);

        radiusField = new JTextField(15);

        addInputField("Digite o raio da circunferência:", radiusField);
    }

    @Override
    protected void onCalculate() {
        int radius = Integer.parseInt(radiusField.getText());
        // String centerX = centerXField.getText();
        // String centerY = centerYField.getText();

        MainScreen mainScreen = MainScreenSingleton.getMainScreen();
        CartesianPlane2D cartesianPlanePanel = MainScreenSingleton.getCartesianPlane2D();

        Circle circle = new Circle(radius);

        mainScreen.geometricFiguresHandler.addFigure(circle);
        mainScreen.updateFigures();

    }

}

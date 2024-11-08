package view.inputsPanel.primitivesInputs;

import project_cg.geometry.figures.Circle;
import project_cg.geometry.planeCartesians.bases.BaseCartesianPlane2D;
import project_cg.primitives.CircleExplicit;
import project_cg.primitives.bases.BaseCircle;
import utils.Constants;
import view.inputsPanel.ShapePanel;
import view.mainScreen.MainScreen;
import view.mainScreen.MainScreenSingleton;
import project_cg.geometry.planeCartesians.CartesianPlane2D;
import project_cg.primitives.TrigometricCircle;

import javax.swing.*;

public class TrigonometricCircleInputs extends ShapePanel {
    private JTextField radiusField;

    @Override
    protected void initializeInputs() {
        radiusField = new JTextField(15);
        radiusField.setToolTipText("Digite o raio do círculo");
        addInputField("Digite o raio do círculo:", radiusField);
    }

    @Override
    protected void onCalculate() {
        try {
            int radius = Integer.parseInt(radiusField.getText());

            MainScreen mainScreen = MainScreenSingleton.getMainScreen();

            BaseCircle circleExplicit = new TrigometricCircle();
            Circle circle = new Circle(radius, circleExplicit);

            mainScreen.geometricFiguresHandler.addFigure(circle);
            mainScreen.updateFigures();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número válido.");
        }
    }
}

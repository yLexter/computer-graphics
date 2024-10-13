package view.inputsPanel.transformations2dinputs;

import geomtry.figures.Circle;
import geomtry.figures.Ellipse;
import geomtry.planeCartesians.CartesianPlane2D;
import utils.Constants;
import view.inputsPanel.ShapePanel;
import view.mainScreen.MainScreen;
import view.mainScreen.MainScreenSingleton;

import javax.swing.*;

public class CreateEllipseInputs extends ShapePanel {

    private JTextField centerXField;
    private JTextField centerYField;
    private JTextField majorAxisField;
    private JTextField minorAxisField;

    @Override
    protected void initializeInputs() {
        centerXField = new JTextField(15);
        centerYField = new JTextField(15);
        majorAxisField = new JTextField(15);
        minorAxisField = new JTextField(15);

        // addInputField("Digite a coordenada X do centro:", centerXField);
        // addInputField("Digite a coordenada Y do centro:", centerYField);

        addInputField("Digite o eixo A:", majorAxisField);
        addInputField("Digite o eixo B:", minorAxisField);
    }

    @Override
    protected void onCalculate() {
        //String centerX = centerXField.getText();
        // String centerY = centerYField.getText();

        int majorAxis = Integer.parseInt(majorAxisField.getText());
        int minorAxis = Integer.parseInt(minorAxisField.getText());

        MainScreen mainScreen = MainScreenSingleton.getMainScreen();
        CartesianPlane2D cartesianPlanePanel = MainScreenSingleton.getCartesianPlane2D();

        Ellipse ellipse = new Ellipse(majorAxis, minorAxis);

        mainScreen.geometricFiguresHandler.addFigure(ellipse);
        mainScreen.updateFigures();

    }


}

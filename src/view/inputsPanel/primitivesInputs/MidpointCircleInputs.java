package view.inputsPanel.primitivesInputs;

import primitives.MidpointCircle;
import utils.Constants;
import view.inputsPanel.ShapePanel;
import geomtry.planeCartesians.CartesianPlane2D;
import view.mainScreen.MainScreenSingleton;

import javax.swing.*;

public class MidpointCircleInputs extends ShapePanel {

    private JTextField radiusField;

    @Override
    protected void initializeInputs() {
        radiusField = new JTextField(10);

        addInputField("Raio:", radiusField);
    }

    @Override
    protected void onCalculate() {
        int radius = Integer.parseInt(radiusField.getText());

        CartesianPlane2D cartesianPlanePanel = MainScreenSingleton.getCartesianPlane2D();

        MidpointCircle panel = new MidpointCircle(
                (point2D -> cartesianPlanePanel.setPixel(point2D, Constants.COLOR_PRIMITEVES))
        );

        panel.drawCircle(radius);

        cartesianPlanePanel.repaint();
    }

}

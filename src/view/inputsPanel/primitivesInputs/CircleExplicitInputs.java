package view.inputsPanel.primitivesInputs;

import utils.Constants;
import view.inputsPanel.ShapePanel;
import view.mainScreen.MainScreenSingleton;
import geomtry.planeCartesians.CartesianPlane2D;
import primitives.CircleExplicit;

import javax.swing.*;

public class CircleExplicitInputs extends ShapePanel {

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

            CartesianPlane2D cartesianPlanePanel = MainScreenSingleton.getCartesianPlane2D();
            CircleExplicit circleExplicit = new CircleExplicit(
                    (point2D -> cartesianPlanePanel.setPixel(point2D, Constants.COLOR_PRIMITEVES))
            );

            circleExplicit.drawCircle(radius);
            cartesianPlanePanel.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número válido.");
        }
    }
}

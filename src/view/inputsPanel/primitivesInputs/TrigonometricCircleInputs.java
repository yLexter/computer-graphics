package view.inputsPanel.primitivesInputs;

import utils.Constants;
import view.inputsPanel.ShapePanel;
import view.mainScreen.MainScreenSingleton;
import geomtry.planeCartesians.CartesianPlane2D;
import primitives.TrigometricCircle;

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

            CartesianPlane2D cartesianPlanePanel = MainScreenSingleton.getCartesianPlane2D();
            TrigometricCircle trigometricCircle = new TrigometricCircle(
                    point2D -> cartesianPlanePanel.setPixel(point2D, Constants.COLOR_PRIMITEVES)
            );

            trigometricCircle.drawCircle(radius);

            cartesianPlanePanel.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número válido.");
        }
    }
}

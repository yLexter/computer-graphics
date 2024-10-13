package view.inputsPanel.primitivesInputs;

import primitives.MidpointElipse;
import utils.Constants;
import view.inputsPanel.ShapePanel;
import geomtry.planeCartesians.CartesianPlane2D;
import view.mainScreen.MainScreenSingleton;

import javax.swing.*;

public class MidpointElipseInputs extends ShapePanel {
    private JTextField cxField;
    private JTextField cyField;
    private JTextField aField;
    private JTextField bField;

    @Override
    protected void initializeInputs() {
        cxField = new JTextField(10);
        cyField = new JTextField(10);
        aField = new JTextField(10);
        bField = new JTextField(10);

        addInputField("Eixo A:", aField);
        addInputField("Eixo B:", bField);
    }

    @Override
    protected void onCalculate() {
        int a = Integer.parseInt(aField.getText());
        int b = Integer.parseInt(bField.getText());

        CartesianPlane2D cartesianPlanePanel = MainScreenSingleton.getCartesianPlane2D();

        MidpointElipse midPointElipse = new MidpointElipse(
                (point2D -> cartesianPlanePanel.setPixel(point2D, Constants.COLOR_PRIMITEVES))
        );

        midPointElipse.drawElipse(a, b);
        cartesianPlanePanel.repaint();
    }

}

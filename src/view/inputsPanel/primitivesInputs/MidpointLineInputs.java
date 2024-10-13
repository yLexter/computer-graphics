package view.inputsPanel.primitivesInputs;

import geomtry.points.Point2D;
import primitives.MidpointLine;
import utils.Constants;
import view.inputsPanel.ShapePanel;
import geomtry.planeCartesians.CartesianPlane2D;
import view.mainScreen.MainScreenSingleton;

import javax.swing.*;

public class MidpointLineInputs extends ShapePanel {
    private JTextField x1Field;
    private JTextField y1Field;
    private JTextField x2Field;
    private JTextField y2Field;

    @Override
    protected void initializeInputs() {
        x1Field = new JTextField(10);
        y1Field = new JTextField(10);
        x2Field = new JTextField(10);
        y2Field = new JTextField(10);

        addInputField("X1:", x1Field);
        addInputField("Y1:", y1Field);
        addInputField("X2:", x2Field);
        addInputField("Y2:", y2Field);
    }

    @Override
    protected void onCalculate() {
        int x1 = Integer.parseInt(x1Field.getText());
        int y1 = Integer.parseInt(y1Field.getText());

        int x2 = Integer.parseInt(x2Field.getText());
        int y2 = Integer.parseInt(y2Field.getText());

        CartesianPlane2D cartesianPlanePanel = MainScreenSingleton.getCartesianPlane2D();

        MidpointLine midPointLine = new MidpointLine(
                (point2D -> cartesianPlanePanel.setPixel(point2D, Constants.COLOR_PRIMITEVES))
        );

        midPointLine.desenhaLinha(new Point2D(x1, y1), new Point2D(x2, y2));
        cartesianPlanePanel.repaint();
    }


}


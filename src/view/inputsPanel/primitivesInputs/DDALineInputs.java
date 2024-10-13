package view.inputsPanel.primitivesInputs;

import view.inputsPanel.ShapePanel;
import view.mainScreen.MainScreenSingleton;
import geomtry.planeCartesians.CartesianPlane2D;
// import primitives.DDALine;

import javax.swing.*;

public class DDALineInputs extends ShapePanel {
    private JTextField inputP1;
    private JTextField inputP2;

    @Override
    protected void initializeInputs() {
        inputP1 = new JTextField(10);
        inputP1.setToolTipText("Digite o ponto P1");

        inputP2 = new JTextField(10);
        inputP2.setToolTipText("Digite o ponto P2");

        addInputField("Ponto P1:", inputP1);
        addInputField("Ponto P2:", inputP2);
    }

    @Override
    protected void onCalculate() {
        try {
            String[] point1 = inputP1.getText().split(" ");
            String[] point2 = inputP2.getText().split(" ");

            int x1 = Integer.parseInt(point1[0]);
            int y1 = Integer.parseInt(point1[1]);
            int x2 = Integer.parseInt(point2[0]);
            int y2 = Integer.parseInt(point2[1]);

            CartesianPlane2D cartesianPlanePanel = MainScreenSingleton.getCartesianPlane2D();
            // DDALine ddaLine = new DDALine(cartesianPlanePanel)
            // ddaLine.desenhaLinha(x1, y1, x2, y2);

            cartesianPlanePanel.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para P1 e P2.");
        }
    }
}

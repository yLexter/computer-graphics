package view.inputsPanel.primitivesInputs;

import primitives.MidpointCircle;

import javax.swing.*;

public class MidpointCircleInputs extends ShapePanel {
    private JTextField cxField;
    private JTextField cyField;
    private JTextField radiusField;

    @Override
    protected void initializeInputs() {
        cxField = new JTextField(10);
        cyField = new JTextField(10);
        radiusField = new JTextField(10);

        addInputField("Centro X:", cxField, 0);
        addInputField("Centro Y:", cyField, 1);
        addInputField("Raio:", radiusField, 2);
    }

    @Override
    protected void onCalculate() {
        int cx = Integer.parseInt(cxField.getText());
        int cy = Integer.parseInt(cyField.getText());
        int radius = Integer.parseInt(radiusField.getText());

        JFrame frame = new JFrame("Mid-Point Circle");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // toDo e mais números magicossss
        frame.setSize(700, 700);

        MidpointCircle panel = new MidpointCircle(700, 700);
        panel.drawCircle(cx, cy, radius); // Desenhar uma circunferência com raio 100

        frame.add(panel);
        frame.setVisible(true);
    }


}

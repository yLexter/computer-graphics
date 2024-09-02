package view.inputsPanel.primitivesInputs;

import primitives.MidpointElipse;

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

        addInputField("Centro X:", cxField, 0);
        addInputField("Centro Y:", cyField, 1);
        addInputField("Eixo A:", aField, 2);
        addInputField("Eixo B:", bField, 3);
    }

    @Override
    protected void onCalculate() {
        int cx = Integer.parseInt(cxField.getText());
        int cy = Integer.parseInt(cyField.getText());
        int a = Integer.parseInt(aField.getText());
        int b = Integer.parseInt(bField.getText());

        JFrame frame = new JFrame("Mid-Point Ellipse");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ToDo numeros mágicos
        frame.setSize(700, 700);

        MidpointElipse panel = new MidpointElipse(700, 700);
        panel.drawElipse(cx, cy, a, b);

        frame.add(panel);
        frame.setVisible(true);
    }


}


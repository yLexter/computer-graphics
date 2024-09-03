package view.inputsPanel.primitivesInputs;

import primitives.MidpointLine;

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

        addInputField("X1:", x1Field, 0);
        addInputField("Y1:", y1Field, 1);
        addInputField("X2:", x2Field, 2);
        addInputField("Y2:", y2Field, 3);
    }

    // ToDo mudar os Valores estão fixo (Colocar o do inputs)
    @Override
    protected void onCalculate() {
        int x1 = Integer.parseInt(x1Field.getText());
        int y1 = Integer.parseInt(y1Field.getText());
        int x2 = Integer.parseInt(x2Field.getText());
        int y2 = Integer.parseInt(y2Field.getText());

        JFrame frame = new JFrame("Mid-Point Line");
        MidpointLine panel = new MidpointLine(700, 700);
        frame.add(panel);
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        panel.drawAxes(); // Desenhar eixos X e Y
        panel.desenhaLinha(x1, y1, x2, y2);
        panel.repaint(); // Atualizar a interface para exibir os eixos
    }


}


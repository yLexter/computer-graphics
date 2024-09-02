package view.inputsPanel.primitivesInputs;

import primitives.DDALine;

import javax.swing.*;
import java.awt.*;


public class DDALineInputs extends JPanel {

    public DDALineInputs() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel labelP1 = new JLabel("Ponto P1:");
        JTextField inputP1 = new JTextField(10);
        inputP1.setToolTipText("Digite o ponto P1");

        JLabel labelP2 = new JLabel("Ponto P2:");
        JTextField inputP2 = new JTextField(10);
        inputP2.setToolTipText("Digite o ponto P2");

        JButton calculateButton = new JButton("Calcular");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(labelP1, gbc);

        gbc.gridx = 1;
        add(inputP1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelP2, gbc);

        gbc.gridx = 1;
        add(inputP2, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(calculateButton, gbc);

        calculateButton.addActionListener(e -> {
            String[] point1 = inputP1.getText().split(" ");
            String[] point2 = inputP2.getText().split(" ");

            int x1, x2, y1, y2;

            x1 =  Integer.parseInt(point1[0]);
            y1 =  Integer.parseInt(point1[1]);

            x2 =  Integer.parseInt(point2[0]);
            y2 =  Integer.parseInt(point2[1]);


            // ToDo arrumar variaveis de tamanho
            JFrame frame = new JFrame("Algoritmo DDA");
            DDALine ddaLinePanel = new DDALine(800, 600);

            ddaLinePanel.drawLine(x1, y1, x2, y2);

            frame.add(ddaLinePanel);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        });
    }

}

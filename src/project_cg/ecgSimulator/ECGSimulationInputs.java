package project_cg.ecgSimulator;

import view.inputsPanel.ShapePanel;
import view.mainScreen.MainScreen;
import view.mainScreen.MainScreenSingleton;

import javax.swing.*;

public class ECGSimulationInputs extends ShapePanel {

    private JTextField timeField;

    @Override
    protected void initializeInputs() {
        timeField = new JTextField(15);

        addInputField("Digite o tempo da animação", timeField);
    }


    @Override
    protected void onCalculate() {
        int time = Integer.parseInt(timeField.getText());

        MainScreen mainScreen = MainScreenSingleton.getMainScreen();
        ECGSimulation ecgSimulation = mainScreen.JPanelHandler.getECGSimulation();

        ecgSimulation.startAnimation(time);
    }


}

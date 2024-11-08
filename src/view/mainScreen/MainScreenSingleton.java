package view.mainScreen;

import project_cg.geometry.planeCartesians.bases.BaseCartesianPlane2D;
import view.mainScreen.mainScreenPanels.LogsOperationPanel;
import project_cg.geometry.planeCartesians.bases.BaseCartesianPlane;
import project_cg.geometry.planeCartesians.CartesianPlane2D;

import javax.swing.*;

public class MainScreenSingleton {
    private static MainScreen mainScreen;

    public static MainScreen getMainScreen() {

        if (mainScreen == null) {
            mainScreen = new MainScreen(
                new JPanel(),
                new LogsOperationPanel()
            );
        }

        return mainScreen;
    }


    public static JComboBox<String> getComboBoxGeometriFigures() {
        var mainScreen = getMainScreen();
        return mainScreen.geometricFiguresHandler.getComboBoxFigures();
    }

}

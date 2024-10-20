package view.mainScreen;

import geometry.planeCartesians.bases.BaseCartesianPlane2D;
import view.mainScreen.mainScreenPanels.LogsOperationPanel;
import geometry.planeCartesians.bases.BaseCartesianPlane;
import geometry.planeCartesians.CartesianPlane2D;

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

    public static BaseCartesianPlane2D getCartesianPlane2D() {
        var mainScreen = getMainScreen();
        return (BaseCartesianPlane2D) mainScreen.getCartesianPlane();
    }

    public static JComboBox<String> getComboBoxGeometriFigures() {
        var mainScreen = getMainScreen();
        return mainScreen.geometricFiguresHandler.getComboBoxFigures();
    }

}

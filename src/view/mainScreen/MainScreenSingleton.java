package view.mainScreen;

import geomtry.figures.BaseFigure;
import geomtry.figures.Square;
import view.mainScreen.mainScreenPanels.LogsOperationPanel;
import geomtry.planeCartesians.BaseCartesianPlane;
import geomtry.planeCartesians.CartesianPlane2D;

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

    public static CartesianPlane2D getCartesianPlane2D() {
        var mainScreen = getMainScreen();

        return (CartesianPlane2D) mainScreen.getCartesianPlane();
    }

    public static JComboBox<String> getComboBoxGeometriFigures() {
        var mainScreen = getMainScreen();
        return mainScreen.geometricFiguresHandler.getComboBoxFigures();
    }

    public static BaseCartesianPlane getCartesianPlane() {
        var mainScreen = getMainScreen();
        return mainScreen.getCartesianPlane();
    }


}

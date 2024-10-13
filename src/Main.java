import view.mainScreen.MainScreen;
import view.mainScreen.mainScreenPanels.InputsPanel;
import view.mainScreen.MainScreenSingleton;

public class Main {
    public static void main(String[] args) {
        MainScreen mainScreen = MainScreenSingleton.getMainScreen();
        mainScreen.setInputs(new InputsPanel());
        mainScreen.setLayoutPanel();

        mainScreen.setVisible(true);
    }
}

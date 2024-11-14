package view.utils;

import project_cg.geometry.planeCartesians.bases.BaseCartesianPlane;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JPanelHandler {

    private final Map<String, JPanel> cartesiansPlane;

    private String currentCartesianPlaneString;

    public JPanelHandler() {
        this.cartesiansPlane = new LinkedHashMap<>();
    }

    public String getCurrentCategory () {
        return currentCartesianPlaneString;
    }

    public void setCurrentCategory(String currentCartesianPlaneString) {
        this.currentCartesianPlaneString = currentCartesianPlaneString;
    }

    public List<String> getCategories() {
        return new ArrayList<>(cartesiansPlane.keySet().stream().toList());
    }

    public JPanel getPanelByCategory(String category) {
        return cartesiansPlane.get(category);
    }

    public JPanel getCurrentPanel() {
        return cartesiansPlane.get(currentCartesianPlaneString);
    }

    public void addJPanel(String category, JPanel JPanel) {
        cartesiansPlane.put(category, JPanel);
    }

    // Manipular os planos cartesianos
    public void resetCurrentCartesianPlane() {
        JPanel currentCartesianPlane = getCurrentCartesianPlane();

        if (currentCartesianPlane instanceof BaseCartesianPlane cast) {
            this.cartesiansPlane.put(currentCartesianPlaneString, cast.reset());
            return;
        }

        throw new RuntimeException("Função chamada indevidamente");
    }

    public BaseCartesianPlane getCurrentCartesianPlane() {
        JPanel currentPanel = cartesiansPlane.get(currentCartesianPlaneString);

        if (currentPanel instanceof BaseCartesianPlane) {
            return (BaseCartesianPlane) currentPanel;
        }

        throw new RuntimeException("Função chamada indevidamente");
    }


}

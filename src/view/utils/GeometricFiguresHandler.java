package view.utils;

import geometry.figures.BaseFigure;

import javax.swing.*;
import java.util.*;

public class GeometricFiguresHandler {


    public Map<String, List<BaseFigure>> hashFigures;

    public CartesianPlaneHandler cartesianPlaneHandler;

    public GeometricFiguresHandler(CartesianPlaneHandler cartesianPlaneHandler) {
        this.hashFigures = new LinkedHashMap<>();
        this.cartesianPlaneHandler = cartesianPlaneHandler;

        this.setCategorys();
    }

    public void addFigure(BaseFigure figure) {
        List<BaseFigure> figures = getFigures();

        figures.add(figure);
    }

    public void setCategorys() {
        List<String> categories = cartesianPlaneHandler.getCategories();

        for (String category : categories) {
            hashFigures.put(category, new ArrayList<>());
        }

    }

    public BaseFigure getFigureByID(String id) {
        List<BaseFigure> figures = getFigures();

        return figures
                .stream()
                .filter(figure -> figure.getID().equals(id))
                .findAny()
                .orElse(null);
    }

    public List<BaseFigure> getFigures() {
        return hashFigures.get(cartesianPlaneHandler.getCurrentCategory());
    }

    public void resetFigures() {
        hashFigures.put(cartesianPlaneHandler.getCurrentCategory(), new ArrayList<>());
    }

    public JComboBox<String> getComboBoxFigures() {
        List<BaseFigure> figures = getFigures();
        JComboBox<String> comboBox = new JComboBox<>();

        if (figures == null) {
            return comboBox;
        }

        for (BaseFigure figure : figures) {
            comboBox.addItem(figure.getID());
        }

        return comboBox;
    }

}

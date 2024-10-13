package view.utils;

import geomtry.figures.BaseFigure;
import geomtry.figures.Square;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GeometricFiguresHandler {


    public List<BaseFigure> figures ;

    public JComboBox<String> comboBox = new JComboBox<>();

    public GeometricFiguresHandler() {
        this.figures = new ArrayList<>();
    }

    public void addFigure(BaseFigure figure) {
        figures.add(figure);
    }

    public BaseFigure getFigureByID(String id) {
        return figures
                .stream()
                .filter(figure -> figure.getID().equals(id))
                .findAny()
                .orElse(null);
    }
    public List<BaseFigure> getFigures() {
        return figures;
    }

    public void resetFigures() {
        this.figures = new ArrayList<>();
    }

    public JComboBox<String> getComboBoxFigures() {
        JComboBox<String> comboBox = new JComboBox<>();

        for (BaseFigure figure : figures) {
            comboBox.addItem(figure.getID());
        }

        return comboBox;
    }

}

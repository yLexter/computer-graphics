package view.mainScreen;

import geomtry.figures.BaseFigure;
import geomtry.points.Point2D;
import utils.Constants;
import geomtry.planeCartesians.BaseCartesianPlane;
import view.utils.CartesianPlaneHandler;
import view.utils.GeometricFiguresHandler;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {

    private JPanel cartesianPlane;
    private JScrollPane operationLogsScroll;
    private JScrollPane inputsScroll;
    public final CartesianPlaneHandler cartesianPlaneHandler;
    public GeometricFiguresHandler geometricFiguresHandler;

    public MainScreen(JPanel cartesianPlane, JPanel operationLogs) {
        this.cartesianPlaneHandler = new CartesianPlaneHandler();
        this.operationLogsScroll = new JScrollPane(operationLogs);
        this.cartesianPlane = cartesianPlane;
        this.geometricFiguresHandler = new GeometricFiguresHandler();

        initializeScreen();
    }

    public void setInputs(JPanel inputs) {
        this.inputsScroll = new JScrollPane(inputs);
    }

    public void setLayoutPanel(JPanel mainPanel) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        // Adiciona o cartesianPlane (80% da largura, 80% da altura)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.7; // 80% da largura
        gbc.weighty = 0.7; // 80% da altura
        gbc.gridwidth = 1; // Ocupa uma coluna
        add(mainPanel, gbc);

        // Adiciona o painel operationLogs (20% da largura, 80% da altura)
        gbc.gridx = 1;
        gbc.weightx = 0.2; // 20% da largura
        gbc.gridwidth = 1; // Ocupa uma coluna
        add(operationLogsScroll, gbc);

        gbc.gridy = 1; // Coloca o painel abaixo dos outros dois
        gbc.gridx = 0; // Inicia na primeira coluna
        gbc.weightx = 1.0; // 100% da largura
        gbc.weighty = 0.02; // 20% da altura
        gbc.gridwidth = 2; // Ocupa as duas colunas
        add(inputsScroll, gbc);
    }

    public void setLayoutPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        // Adiciona o cartesianPlane (80% da largura, 80% da altura)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.8; // 80% da largura
        gbc.weighty = 0.8; // 80% da altura
        gbc.gridwidth = 1; // Ocupa uma coluna
        add(cartesianPlane, gbc);

        // Adiciona o painel operationLogs (20% da largura, 80% da altura)
        gbc.gridx = 1;
        gbc.weightx = 0.2; // 20% da largura
        gbc.gridwidth = 1; // Ocupa uma coluna
        add(operationLogsScroll, gbc);

        gbc.gridy = 1; // Coloca o painel abaixo dos outros dois
        gbc.gridx = 0; // Inicia na primeira coluna
        gbc.weightx = 1.0; // 100% da largura
        gbc.weighty = 0.02; // 20% da altura
        gbc.gridwidth = 2; // Ocupa as duas colunas
        add(inputsScroll, gbc);
    }

    private void initializeScreen() {
        setTitle("Figure Designer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.WIDTH_MAIN_SCREEN, Constants.HEIGHT_MAIN_SCREEN);
    }

    public BaseCartesianPlane getCartesianPlane() {
        return cartesianPlaneHandler.getCurrentCartesianPlane();
    }

    public void updateCartesianPlane(BaseCartesianPlane cartesianPlane) {
        cartesianPlaneHandler.setCartesianPlane(cartesianPlane);

        getContentPane().removeAll();
        setLayoutPanel(cartesianPlane);
        revalidate();
        repaint();
    }

    public void resetCartesianPlane() {
        BaseCartesianPlane cartesianPlane = cartesianPlaneHandler.getCurrentCartesianPlane();
        updateCartesianPlane(cartesianPlane.reset());
        geometricFiguresHandler.resetFigures();
    }

    public void updateFigures() {
        BaseCartesianPlane cartesianPlane1 = cartesianPlaneHandler.getCurrentCartesianPlane();

        cartesianPlane1.clearCartesianPlane();

        for(BaseFigure figure : geometricFiguresHandler.getFigures()) {
            for(Point2D point2D : figure.getPoints()) {
                cartesianPlane1.setPixel(point2D, figure.getColor());
            }
        }

        cartesianPlane1.repaint();
    }

    public JScrollPane getOperationLogsScroll() {
        return operationLogsScroll;
    }

    public JScrollPane getInputsScroll() {
        return inputsScroll;
    }

}

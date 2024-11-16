package view.mainScreen;

import geometry.figures.BaseFigure;
import geometry.planeCartesians.bases.BaseCartesianPlane2D;
import geometry.points.Point2D;
import utils.Constants;
import geometry.planeCartesians.bases.BaseCartesianPlane;
import view.utils.CartesianPlaneHandler;
import view.utils.GeometricFiguresHandler;
import viewportNew.ViewportWindow;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {

    private JPanel cartesianPlane;
    private JScrollPane operationLogsScroll;
    private JScrollPane inputsScroll;
    public final CartesianPlaneHandler cartesianPlaneHandler;
    public GeometricFiguresHandler geometricFiguresHandler;

    private ViewportWindow viewportWindow; // Nova janela para exibir a viewport

    public MainScreen(JPanel cartesianPlane, JPanel operationLogs) {
        this.cartesianPlaneHandler = new CartesianPlaneHandler();
        this.operationLogsScroll = new JScrollPane(operationLogs);
        this.cartesianPlane = cartesianPlane;

        initializeScreen();
        initializeViewportWindow(); // Inicializa a nova janela
    }

    private void initializeViewportWindow() {
        int viewportWidth = 400;  // Largura da viewport
        int viewportHeight = 400; // Altura da viewport
        this.viewportWindow = new ViewportWindow(viewportWidth, viewportHeight);
    }

    public CartesianPlaneHandler getCartesianPlaneHandler() {
        return cartesianPlaneHandler;
    }

    public void setInputs(JPanel inputs) {
        this.inputsScroll = new JScrollPane(inputs);
    }

    public void setGeometricFiguresHandler(GeometricFiguresHandler geometricFiguresHandler) {
        this.geometricFiguresHandler = geometricFiguresHandler;
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

    public void changeCartesianPlaneScreen(String categoryCatersianPlane) {
        this.cartesianPlaneHandler.setCurrentCategory(categoryCatersianPlane);
        this.cartesianPlane = cartesianPlaneHandler.getCurrentCartesianPlane();

        getContentPane().removeAll();
        setLayoutPanel(cartesianPlane);
        revalidate();
        repaint();
    }

    public void resetCartesianPlaneScreen() {
        this.cartesianPlaneHandler.resetCurrentCartesianPlane();
        this.cartesianPlane = this.cartesianPlaneHandler.getCurrentCartesianPlane();

        getContentPane().removeAll();
        setLayoutPanel(cartesianPlane);
        revalidate();
        repaint();
    }

    public void resetCartesianPlane() {
        this.resetCartesianPlaneScreen();
        this.geometricFiguresHandler.resetFigures();
    }

    public void updateFigures() {
        BaseCartesianPlane cartesianPlane1 = cartesianPlaneHandler.getCurrentCartesianPlane();
        cartesianPlane1.clearCartesianPlane();

        for (BaseFigure figure : geometricFiguresHandler.getFigures()) {
            for (Point2D point2D : figure.getPoints()) {
                cartesianPlane1.setPixel(point2D, figure.getColor());
            }
        }

        cartesianPlane1.repaint();
        updateViewport(); // Atualiza a viewport ao modificar figuras
    }

    public void updateViewport() {
        // Renderiza uma parte do plano cartesiano na nova janela
        int worldXMin = -500, worldYMin = -500, worldXMax = 500, worldYMax = 500; // Região da viewport
        viewportWindow.updateViewport((BaseCartesianPlane2D) cartesianPlaneHandler.getCurrentCartesianPlane(),
                worldXMin, worldYMin, worldXMax, worldYMax);
    }

    public JScrollPane getOperationLogsScroll() {
        return operationLogsScroll;
    }

    public JScrollPane getInputsScroll() {
        return inputsScroll;
    }
}

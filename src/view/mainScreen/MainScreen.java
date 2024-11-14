package view.mainScreen;

import project_cg.geometry.figures.BaseFigure;
import project_cg.geometry.points.Point2D;
import utils.Constants;
import project_cg.geometry.planeCartesians.bases.BaseCartesianPlane;
import view.utils.JPanelHandler;
import view.utils.GeometricFiguresHandler;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {

    private JPanel homePanel;
    private JScrollPane operationLogsScroll;
    private JScrollPane inputsScroll;
    public final JPanelHandler JPanelHandler;
    public GeometricFiguresHandler geometricFiguresHandler;

    public MainScreen(JPanel homePanel, JPanel operationLogs) {
        this.JPanelHandler = new JPanelHandler();
        this.operationLogsScroll = new JScrollPane(operationLogs);
        this.homePanel = homePanel;

        initializeScreen();
    }

    public JPanelHandler getCartesianPlaneHandler() {
        return JPanelHandler;
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
        add(homePanel, gbc);

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
        setTitle("Projeto Computação Gráfica/Processamento de Imagem");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.WIDTH_MAIN_SCREEN, Constants.HEIGHT_MAIN_SCREEN);
    }

    public void updateCurrentPanel(String categoryCatersianPlane) {
        this.JPanelHandler.setCurrentCategory(categoryCatersianPlane);
        JPanel currentPanel = JPanelHandler.getCurrentPanel();

        getContentPane().removeAll();
        setLayoutPanel(currentPanel);
        revalidate();
        repaint();
    }

    public void resetCartesianPlaneScreen() {
        this.JPanelHandler.resetCurrentCartesianPlane();
        JPanel currentPanel = this.JPanelHandler.getCurrentCartesianPlane();

        getContentPane().removeAll();
        setLayoutPanel(currentPanel);
        revalidate();
        repaint();
    }

    public void resetCartesianPlane() {
        this.resetCartesianPlaneScreen();
        this.geometricFiguresHandler.resetFigures();
    }

    public void updateFigures() {
        BaseCartesianPlane cartesianPlane1 = JPanelHandler.getCurrentCartesianPlane();
        cartesianPlane1.clearCartesianPlane();

        for(BaseFigure figure : geometricFiguresHandler.getFigures()) {
            for(Point2D point2D : figure.getPoints()) {
                cartesianPlane1.setPixel(point2D, figure.getColor());
            }
        }

        cartesianPlane1.repaint();
    }


}

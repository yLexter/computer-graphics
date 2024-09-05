package view.inputsPanel.transformations2dinputs;

import transformations2d.Reflection;
import transformations2d.Rotation;
import transformations2d.Scale;
import transformations2d.Shear;
import transformations2d.Translation;

import primitives.MidpointCircle;
import primitives.MidpointElipse;
import primitives.MidpointLine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Forms2d extends JPanel {

    private JComboBox<String> shapeSelector;
    private JPanel inputPanel;
    private JTextField sidesField;
    private JTextField[][] coordinateFields;
    private int[][] coordinates;
    private JFrame visualizationFrame;
    private DrawingPanel drawingPanel = new DrawingPanel();
    private int radius; // Para circunferência
    private int majorAxis, minorAxis; // Para elipse
    private Translation translation; // Instância da classe Translation para realizar translação
    private Scale scale;
    private Rotation rotation; // Instância da classe Rotation para realizar a rotação

    private boolean isCircle = false, isEllipse = false; // Flags para identificar o tipo de forma

    private MidpointCircle midpointCircle = new MidpointCircle(1200, 800);
    private MidpointElipse midpointElipse = new MidpointElipse(1200, 800);

    public Forms2d() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel label = new JLabel("Escolha a forma geométrica:");
        add(label, gbc);

        shapeSelector = new JComboBox<>(new String[]{"Polígono", "Circunferência", "Elipse"});
        gbc.gridy = 1;
        add(shapeSelector, gbc);

        shapeSelector.addActionListener(e -> updateInputs());

        inputPanel = new JPanel();
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(inputPanel, gbc);

        updateInputs(); // Chama para configurar os inputs iniciais
    }

    private void updateInputs() {
        inputPanel.removeAll();
        String selectedShape = (String) shapeSelector.getSelectedItem();
        isCircle = false;
        isEllipse = false;

        if ("Polígono".equals(selectedShape)) {
            createPolygonInputs();
        } else if ("Circunferência".equals(selectedShape)) {
            isCircle = true;
            createCircleInputs();
        } else if ("Elipse".equals(selectedShape)) {
            isEllipse = true;
            createEllipseInputs();
        }

        inputPanel.revalidate();
        inputPanel.repaint();
    }

    private void createPolygonInputs() {
        JLabel sidesLabel = new JLabel("Digite o número de lados:");
        inputPanel.add(sidesLabel);

        sidesField = new JTextField(10);
        inputPanel.add(sidesField);

        JButton generateButton = new JButton("Gerar Inputs");
        inputPanel.add(generateButton);

        generateButton.addActionListener(e -> generatePolygonInputs());
    }

    private void generatePolygonInputs() {
        try {
            int sides = Integer.parseInt(sidesField.getText());
            coordinates = new int[sides][2];  // Matriz para armazenar as coordenadas
            inputPanel.removeAll();

            coordinateFields = new JTextField[sides][2];

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            for (int i = 0; i < sides; i++) {
                JLabel xLabel = new JLabel("X" + (i + 1) + ":");
                coordinateFields[i][0] = new JTextField(5);
                JLabel yLabel = new JLabel("Y" + (i + 1) + ":");
                coordinateFields[i][1] = new JTextField(5);

                gbc.gridx = 0;
                gbc.gridy = i;
                inputPanel.add(xLabel, gbc);
                gbc.gridx = 1;
                inputPanel.add(coordinateFields[i][0], gbc);
                gbc.gridx = 2;
                inputPanel.add(yLabel, gbc);
                gbc.gridx = 3;
                inputPanel.add(coordinateFields[i][1], gbc);
            }

            JButton saveButton = new JButton("Salvar e Visualizar");
            gbc.gridx = 0;
            gbc.gridy = sides;
            gbc.gridwidth = 4;
            inputPanel.add(saveButton, gbc);

            saveButton.addActionListener(e -> saveCoordinatesAndVisualize());

            inputPanel.revalidate();
            inputPanel.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, insira um número válido.");
        }
    }

    private void saveCoordinatesAndVisualize() {
        for (int i = 0; i < coordinateFields.length; i++) {
            try {
                int x = Integer.parseInt(coordinateFields[i][0].getText());
                int y = Integer.parseInt(coordinateFields[i][1].getText());
                coordinates[i][0] = x;
                coordinates[i][1] = y;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos para as coordenadas.");
                return;
            }
        }

        // Criação da janela de visualização
        visualizationFrame = new JFrame("Visualização da Forma");
        drawingPanel = new DrawingPanel(coordinates);
        visualizationFrame.add(drawingPanel);
        visualizationFrame.setSize(1200, 800);
        visualizationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        visualizationFrame.setVisible(true);

        drawingPanel.revalidate();
        drawingPanel.repaint();

        // Abrir janela de transformações
        openTransformationWindow();
    }

    private void createCircleInputs() {
        JLabel radiusLabel = new JLabel("Digite o raio da circunferência:");
        inputPanel.add(radiusLabel);

        JTextField radiusField = new JTextField(15);
        inputPanel.add(radiusField);

        JLabel centerXLabel = new JLabel("Digite a coordenada X do centro:");
        inputPanel.add(centerXLabel);

        JTextField centerXField = new JTextField(15);
        inputPanel.add(centerXField);

        JLabel centerYLabel = new JLabel("Digite a coordenada Y do centro:");
        inputPanel.add(centerYLabel);

        JTextField centerYField = new JTextField(15);
        inputPanel.add(centerYField);

        JButton drawButton = new JButton("Desenhar Círculo");
        inputPanel.add(drawButton);

        drawButton.addActionListener(e -> drawCircle(centerXField.getText(), centerYField.getText(), radiusField.getText()));
    }

    private void drawCircle(String centerXText, String centerYText, String radiusText) {
        try {
            int cx = Integer.parseInt(centerXText);
            int cy = Integer.parseInt(centerYText);
            radius = Integer.parseInt(radiusText);

            // Criação da janela de visualização para o círculo
            JFrame circleFrame = new JFrame("Visualização do Círculo");
            midpointCircle.drawCircle(cx, cy, radius);
            circleFrame.add(midpointCircle);
            circleFrame.setSize(1200, 800);
            circleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            circleFrame.setVisible(true);

            // Armazena as informações da circunferência para transformações
            coordinates = new int[][]{{cx, cy}};  // Somente o centro é relevante para transformações

            // Abre a janela de transformações
            openTransformationWindow();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos para o centro e o raio.");
        }
    }

    private void createEllipseInputs() {
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Coordenada X do centro
        JLabel centerXLabel = new JLabel("Digite a coordenada X do centro:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        inputPanel.add(centerXLabel, gbc);

        JTextField centerXField = new JTextField(15);
        gbc.gridx = 1;
        inputPanel.add(centerXField, gbc);

        // Coordenada Y do centro
        JLabel centerYLabel = new JLabel("Digite a coordenada Y do centro:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(centerYLabel, gbc);

        JTextField centerYField = new JTextField(15);
        gbc.gridx = 1;
        inputPanel.add(centerYField, gbc);

        // Eixo maior
        JLabel majorAxisLabel = new JLabel("Digite o eixo maior:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(majorAxisLabel, gbc);

        JTextField majorAxisField = new JTextField(15);
        gbc.gridx = 1;
        inputPanel.add(majorAxisField, gbc);

        // Eixo menor
        JLabel minorAxisLabel = new JLabel("Digite o eixo menor:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(minorAxisLabel, gbc);

        JTextField minorAxisField = new JTextField(15);
        gbc.gridx = 1;
        inputPanel.add(minorAxisField, gbc);

        // Botão para desenhar a elipse
        JButton drawButton = new JButton("Desenhar Elipse");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        inputPanel.add(drawButton, gbc);

        drawButton.addActionListener(e -> drawEllipse(centerXField.getText(), centerYField.getText(), majorAxisField.getText(), minorAxisField.getText()));

        // Atualiza a interface para refletir as mudanças
        inputPanel.revalidate();
        inputPanel.repaint();
    }

    private void drawEllipse(String centerXText, String centerYText, String majorAxisText, String minorAxisText) {
        try {
            int cx = Integer.parseInt(centerXText);
            int cy = Integer.parseInt(centerYText);
            majorAxis = Integer.parseInt(majorAxisText);
            minorAxis = Integer.parseInt(minorAxisText);

            // Criação da janela de visualização para a elipse
            JFrame ellipseFrame = new JFrame("Visualização da Elipse");
            midpointElipse.drawElipse(cx, cy, majorAxis, minorAxis);
            ellipseFrame.add(midpointElipse);
            ellipseFrame.setSize(1200, 800);
            ellipseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ellipseFrame.setVisible(true);

            // Armazena as informações da elipse para transformações
            coordinates = new int[][]{{cx, cy}};  // O centro é relevante para as transformações

            // Abre a janela de transformações
            openTransformationWindow();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos para o centro e os eixos.");
        }
    }

    private void openTransformationWindow() {
        JFrame transformationFrame = new JFrame("Transformações");
        transformationFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel transformationLabel = new JLabel("Escolha a transformação:");
        transformationFrame.add(transformationLabel, gbc);

        JComboBox<String> transformationSelector = new JComboBox<>(new String[]{"Translation", "Scale", "Rotation", "Reflection", "Shear"});
        gbc.gridy = 1;
        transformationFrame.add(transformationSelector, gbc);

        JLabel input1Label = new JLabel("Input 1:");
        gbc.gridy = 2;
        transformationFrame.add(input1Label, gbc);

        JTextField input1Field = new JTextField(10);
        gbc.gridy = 3;
        transformationFrame.add(input1Field, gbc);

        JLabel input2Label = new JLabel("Input 2:");
        gbc.gridy = 4;
        transformationFrame.add(input2Label, gbc);

        JTextField input2Field = new JTextField(10);
        gbc.gridy = 5;
        transformationFrame.add(input2Field, gbc);

        JButton applyButton = new JButton("Aplicar Transformação");
        gbc.gridy = 6;
        transformationFrame.add(applyButton, gbc);

        applyButton.addActionListener(e -> applyTransformation(transformationSelector.getSelectedItem().toString(),
                input1Field.getText(), input2Field.getText()));

        transformationFrame.pack();
        transformationFrame.setVisible(true);
    }

    private void applyTransformation(String transformation, String input1, String input2) {
        try {
            int value1 = Integer.parseInt(input1); // valor para transformação
            int value2 = Integer.parseInt(input2); // valor para transformação

            if ("Translation".equals(transformation)) {
                // Usar a classe Translation para realizar a translação
                translation = new Translation(1200, 800);

                if (isCircle || isEllipse) {
                    // Para circunferência ou elipse, apenas o centro é transladado
                    double[] newCoords = translation.translatingPoint(coordinates[0][0], coordinates[0][1], value1, value2);
                    coordinates[0][0] = (int) newCoords[0];
                    coordinates[0][1] = (int) newCoords[1];

                    if (isCircle)
                        midpointCircle.drawCircle(coordinates[0][0], coordinates[0][1], radius);
                    else
                        midpointElipse.drawElipse(coordinates[0][0], coordinates[0][1], majorAxis, minorAxis);
                } else {
                    // Para polígonos, transladar todos os pontos
                    double[][] coordsDouble = new double[coordinates.length][2];

                    // Converte coordenadas de int para double
                    for (int i = 0; i < coordinates.length; i++) {
                        coordsDouble[i][0] = coordinates[i][0];
                        coordsDouble[i][1] = coordinates[i][1];
                    }

                    double[][] newCoords = translation.translation(coordsDouble, value1, value2);

                    // Converte coordenadas de volta para int
                    for (int i = 0; i < newCoords.length; i++) {
                        coordinates[i][0] = (int) newCoords[i][0];
                        coordinates[i][1] = (int) newCoords[i][1];
                    }
                }
            } else if ("Scale".equals(transformation)) {
                // Usar a classe Scale para realizar a escala
                scale = new Scale(1200, 800);

                if (isCircle || isEllipse) {
                    // Para circunferências e elipses, escalar o centro e as dimensões (raio ou eixos)
                    double[][] newCoords = scale.scalation(new double[][]{{coordinates[0][0], coordinates[0][1]}}, value1, value2);
                    coordinates[0][0] = (int) newCoords[0][0];
                    coordinates[0][1] = (int) newCoords[0][1];

                    if (isCircle) {
                        radius *= value1;
                        midpointCircle.drawCircle(coordinates[0][0], coordinates[0][1], radius);
                    } else {
                        majorAxis *= value1;
                        minorAxis *= value2;
                        midpointElipse.drawElipse(coordinates[0][0], coordinates[0][1], majorAxis, minorAxis);
                    }
                } else {
                    // Para polígonos, escalar todos os pontos
                    double[][] coordsDouble = new double[coordinates.length][2];
                    for (int i = 0; i < coordinates.length; i++) {
                        coordsDouble[i][0] = coordinates[i][0];
                        coordsDouble[i][1] = coordinates[i][1];
                    }

                    double[][] newCoords = scale.scalation(coordsDouble, value1, value2);

                    // Atualizar as coordenadas com os valores escalados
                    for (int i = 0; i < newCoords.length; i++) {
                        coordinates[i][0] = (int) newCoords[i][0];
                        coordinates[i][1] = (int) newCoords[i][1];
                    }
                }
            } else if ("Rotation".equals(transformation)) {
                // Usar a classe Rotation para realizar a rotação
                rotation = new Rotation(1200, 800);
                double angle = Math.toRadians(value1); // Converte o ângulo para radianos
                    
                if (isCircle) {
                    if (coordinates[0][0] == 0 && coordinates[0][1] == 0) {
                        // Rotaciona diretamente no ponto (0, 0)
                        midpointCircle.drawCircle(0, 0, radius);
                    } else {
                        // Aplicar rotação no centro deslocado
                        double[][] newCoords = rotation.rotation(new double[][]{{coordinates[0][0], coordinates[0][1]}}, angle);
                        coordinates[0][0] = (int) newCoords[0][0];
                        coordinates[0][1] = (int) newCoords[0][1];
            
                        midpointCircle.drawCircle(coordinates[0][0], coordinates[0][1], radius);
                    }
                } else if (isEllipse) {
                    if (coordinates[0][0] == 0 && coordinates[0][1] == 0) {
                        // Rotaciona diretamente no ponto (0, 0)
                        midpointElipse.drawElipse(0, 0, majorAxis, minorAxis);
                    } else {
                        // Aplicar rotação no centro deslocado
                        double[][] newCoords = rotation.rotation(new double[][]{{coordinates[0][0], coordinates[0][1]}}, angle);
                        coordinates[0][0] = (int) newCoords[0][0];
                        coordinates[0][1] = (int) newCoords[0][1];
            
                        midpointElipse.drawElipse(coordinates[0][0], coordinates[0][1], majorAxis, minorAxis);
                    }
                } else {
                    // Para polígonos, rotacionar todos os pontos
                    double[][] coordsDouble = new double[coordinates.length][2];
                    for (int i = 0; i < coordinates.length; i++) {
                        coordsDouble[i][0] = coordinates[i][0];
                        coordsDouble[i][1] = coordinates[i][1];
                    }
            
                    double[][] newCoords = rotation.rotation(coordsDouble, angle);
            
                    // Atualizar as coordenadas com os valores rotacionados
                    for (int i = 0; i < newCoords.length; i++) {
                        coordinates[i][0] = (int) newCoords[i][0];
                        coordinates[i][1] = (int) newCoords[i][1];
                    }
                }
            } else if ("Reflection".equals(transformation)) {
                // Usar a classe Reflection para realizar a reflexão
                Reflection reflection = new Reflection(1200, 800);
                
                String reflectionType = JOptionPane.showInputDialog(null, "Digite o tipo de reflexão (X, Y, Origem, YX):");
        
                if (isCircle || isEllipse) {
                    // Circulos e Elipses são simétricos, logo a reflexão será aplicada ao centro apenas
                    if (reflectionType.equalsIgnoreCase("X")) {
                        double[][] newCoords = reflection.reflectionInX(new double[][]{{coordinates[0][0], coordinates[0][1]}});
                        coordinates[0][0] = (int) newCoords[0][0];
                        coordinates[0][1] = (int) newCoords[0][1];

                    } else if (reflectionType.equalsIgnoreCase("Y")) {
                        double[][] newCoords = reflection.reflectionInY(new double[][]{{coordinates[0][0], coordinates[0][1]}});
                        coordinates[0][0] = (int) newCoords[0][0];
                        coordinates[0][1] = (int) newCoords[0][1];

                    } else if (reflectionType.equalsIgnoreCase("Origem")) {
                        double[][] newCoords = reflection.reflectingPointInOrigin(new double[][]{{coordinates[0][0], coordinates[0][1]}});
                        coordinates[0][0] = (int) newCoords[0][0];
                        coordinates[0][1] = (int) newCoords[0][1];

                    } else if (reflectionType.equalsIgnoreCase("YX")) {
                        double[][] newCoords = reflection.reflectingPointInLineYX(new double[][]{{coordinates[0][0], coordinates[0][1]}});
                        coordinates[0][0] = (int) newCoords[0][0];
                        coordinates[0][1] = (int) newCoords[0][1];
                    }

                    // Desenhar circunferência ou elipse refletida
                    if (isCircle) {
                        midpointCircle.drawCircle(coordinates[0][0], coordinates[0][1], radius);
                    } else {
                        midpointElipse.drawElipse(coordinates[0][0], coordinates[0][1], majorAxis, minorAxis);
                    }

                } else {
                    // Para polígonos, refletir todos os pontos
                    double[][] coordsDouble = new double[coordinates.length][2];
                    for (int i = 0; i < coordinates.length; i++) {
                        coordsDouble[i][0] = coordinates[i][0];
                        coordsDouble[i][1] = coordinates[i][1];
                    }

                    double[][] newCoords = null;

                    if (reflectionType.equalsIgnoreCase("X")) {
                        newCoords = reflection.reflectionInX(coordsDouble);
                    } else if (reflectionType.equalsIgnoreCase("Y")) {
                        newCoords = reflection.reflectionInY(coordsDouble);
                    } else if (reflectionType.equalsIgnoreCase("Origem")) {
                        newCoords = reflection.reflectingPointInOrigin(coordsDouble);
                    } else if (reflectionType.equalsIgnoreCase("YX")) {
                        newCoords = reflection.reflectingPointInLineYX(coordsDouble);
                    }

                    // Atualizar as coordenadas com os valores refletidos
                    for (int i = 0; i < newCoords.length; i++) {
                        coordinates[i][0] = (int) newCoords[i][0];
                        coordinates[i][1] = (int) newCoords[i][1];
                    }
                }
            } else if ("Shear".equals(transformation)) {
                // Usar a classe Shear para realizar o cisalhamento
                Shear shear = new Shear(1200, 800);

                String shearType = JOptionPane.showInputDialog(null, "Digite o tipo de cisalhamento (X, Y, XY):");
                double a = 0;
                double b = 0;

                if (shearType.equalsIgnoreCase("X") || shearType.equalsIgnoreCase("XY")) {
                    b = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor de b (cisalhamento em X):"));
                }
                if (shearType.equalsIgnoreCase("Y") || shearType.equalsIgnoreCase("XY")) {
                    a = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor de a (cisalhamento em Y):"));
                }

                if (isCircle || isEllipse) {
                    // Aplicar o cisalhamento apenas ao centro do círculo ou elipse (seu formato não muda com cisalhamento)
                    if (shearType.equalsIgnoreCase("X")) {
                        double[][] newCoords = shear.shearInX(new double[][]{{coordinates[0][0], coordinates[0][1]}}, b);
                        coordinates[0][0] = (int) newCoords[0][0];
                        coordinates[0][1] = (int) newCoords[0][1];
                    } else if (shearType.equalsIgnoreCase("Y")) {
                        double[][] newCoords = shear.shearInY(new double[][]{{coordinates[0][0], coordinates[0][1]}}, a);
                        coordinates[0][0] = (int) newCoords[0][0];
                        coordinates[0][1] = (int) newCoords[0][1];
                    } else if (shearType.equalsIgnoreCase("XY")) {
                        double[][] newCoords = shear.shearInXY(new double[][]{{coordinates[0][0], coordinates[0][1]}}, b, a);
                        coordinates[0][0] = (int) newCoords[0][0];
                        coordinates[0][1] = (int) newCoords[0][1];
                    }

                    // Desenhar circunferência ou elipse cisalhada (apenas o centro se move)
                    if (isCircle) {
                        midpointCircle.drawCircle(coordinates[0][0], coordinates[0][1], radius);
                    } else {
                        midpointElipse.drawElipse(coordinates[0][0], coordinates[0][1], majorAxis, minorAxis);
                    }

                } else {
                    // Para polígonos, aplicar o cisalhamento em todos os pontos
                    double[][] coordsDouble = new double[coordinates.length][2];
                    for (int i = 0; i < coordinates.length; i++) {
                        coordsDouble[i][0] = coordinates[i][0];
                        coordsDouble[i][1] = coordinates[i][1];
                    }

                    double[][] newCoords = null;

                    if (shearType.equalsIgnoreCase("X")) {
                        newCoords = shear.shearInX(coordsDouble, b);
                    } else if (shearType.equalsIgnoreCase("Y")) {
                        newCoords = shear.shearInY(coordsDouble, a);
                    } else if (shearType.equalsIgnoreCase("XY")) {
                        newCoords = shear.shearInXY(coordsDouble, b, a);
                    }

                    // Atualizar as coordenadas com os valores cisalhados
                    for (int i = 0; i < newCoords.length; i++) {
                        coordinates[i][0] = (int) newCoords[i][0];
                        coordinates[i][1] = (int) newCoords[i][1];
                    }
                }
            }
            
            // Atualiza a visualização
            drawingPanel.setCoordinates(coordinates);
            drawingPanel.revalidate(); // Revalida o layout do painel
            drawingPanel.repaint();    // Repinta o painel

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos para os inputs.");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Forms2d");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Forms2d());
        frame.pack();
        frame.setVisible(true);
    }
}

// Classe para desenhar a forma na janela de visualização
class DrawingPanel extends JPanel {
    private int[][] coordinates;
    private MidpointLine midpointLine;

    public DrawingPanel() {}

    public DrawingPanel(int[][] coordinates) {
        this.coordinates = coordinates;
        this.midpointLine = new MidpointLine(1200, 800);
    }



    public void setCoordinates(int[][] coordinates) {
        this.coordinates = coordinates;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        midpointLine.paintComponent(g);

        // Desenhar o polígono usando as coordenadas
        if (coordinates != null && coordinates.length > 1) {
            for (int i = 1; i < coordinates.length; i++) {
                midpointLine.desenhaLinha(coordinates[i - 1][0], coordinates[i - 1][1], coordinates[i][0], coordinates[i][1]);
            }

            // para fechar o polígono
            midpointLine.desenhaLinha(coordinates[coordinates.length - 1][0], coordinates[coordinates.length - 1][1],
                    coordinates[0][0], coordinates[0][1]);
        }
    }
}

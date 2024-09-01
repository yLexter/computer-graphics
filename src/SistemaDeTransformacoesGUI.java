import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaDeTransformacoesGUI {

    private static final int SCREEN_WIDTH = 1200;
    private static final int SCREEN_HEIGHT = 800;

    public static int getScreenWidth(){
        return SCREEN_WIDTH;
    }

    public static int getScreenHeight(){
        return SCREEN_HEIGHT;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema de Transformações de Coordenadas");
        frame.setSize(500, 600);  // Aumenta o tamanho da janela principal
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Fonte maior para os labels
        Font labelFont = new Font("Arial", Font.BOLD, 16);

        // Labels and text fields for inputs
        JLabel labelX = new JLabel("Digite o valor de X:");
        labelX.setFont(labelFont);
        labelX.setPreferredSize(new Dimension(200, 30));
        JTextField fieldX = new JTextField();
        fieldX.setPreferredSize(new Dimension(200, 30));

        JLabel labelXm = new JLabel("Digite o valor de Xm:");
        labelXm.setFont(labelFont);
        labelXm.setPreferredSize(new Dimension(200, 30));
        JTextField fieldXm = new JTextField();
        fieldXm.setPreferredSize(new Dimension(200, 30));

        JLabel labelXM = new JLabel("Digite o valor de XM:");
        labelXM.setFont(labelFont);
        labelXM.setPreferredSize(new Dimension(200, 30));
        JTextField fieldXM = new JTextField();
        fieldXM.setPreferredSize(new Dimension(200, 30));

        JLabel labelY = new JLabel("Digite o valor de Y:");
        labelY.setFont(labelFont);
        labelY.setPreferredSize(new Dimension(200, 30));
        JTextField fieldY = new JTextField();
        fieldY.setPreferredSize(new Dimension(200, 30));

        JLabel labelYm = new JLabel("Digite o valor de Ym:");
        labelYm.setFont(labelFont);
        labelYm.setPreferredSize(new Dimension(200, 30));
        JTextField fieldYm = new JTextField();
        fieldYm.setPreferredSize(new Dimension(200, 30));

        JLabel labelYM = new JLabel("Digite o valor de YM:");
        labelYM.setFont(labelFont);
        labelYM.setPreferredSize(new Dimension(200, 30));
        JTextField fieldYM = new JTextField();
        fieldYM.setPreferredSize(new Dimension(200, 30));

        JLabel labelInicialX = new JLabel("Digite o valor inicial NDC de X:");
        labelInicialX.setFont(labelFont);
        labelInicialX.setPreferredSize(new Dimension(200, 30));
        JTextField fieldInicialX = new JTextField();
        fieldInicialX.setPreferredSize(new Dimension(200, 30));

        JLabel labelFinalX = new JLabel("Digite o valor final NDC de X:");
        labelFinalX.setFont(labelFont);
        labelFinalX.setPreferredSize(new Dimension(200, 30));
        JTextField fieldFinalX = new JTextField();
        fieldFinalX.setPreferredSize(new Dimension(200, 30));

        JLabel labelInicialY = new JLabel("Digite o valor inicial NDC de Y:");
        labelInicialY.setFont(labelFont);
        labelInicialY.setPreferredSize(new Dimension(200, 30));
        JTextField fieldInicialY = new JTextField();
        fieldInicialY.setPreferredSize(new Dimension(200, 30));

        JLabel labelFinalY = new JLabel("Digite o valor final NDC de Y:");
        labelFinalY.setFont(labelFont);
        labelFinalY.setPreferredSize(new Dimension(200, 30));
        JTextField fieldFinalY = new JTextField();
        fieldFinalY.setPreferredSize(new Dimension(200, 30));

        JButton calcularButton = new JButton("Calcular");
        calcularButton.setFont(new Font("Arial", Font.BOLD, 16));
        calcularButton.setPreferredSize(new Dimension(150, 40));

        // Positioning components in the grid
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(labelX, gbc);
        gbc.gridx = 1;
        frame.add(fieldX, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(labelXm, gbc);
        gbc.gridx = 1;
        frame.add(fieldXm, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(labelXM, gbc);
        gbc.gridx = 1;
        frame.add(fieldXM, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(labelY, gbc);
        gbc.gridx = 1;
        frame.add(fieldY, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(labelYm, gbc);
        gbc.gridx = 1;
        frame.add(fieldYm, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        frame.add(labelYM, gbc);
        gbc.gridx = 1;
        frame.add(fieldYM, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        frame.add(labelInicialX, gbc);
        gbc.gridx = 1;
        frame.add(fieldInicialX, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        frame.add(labelFinalX, gbc);
        gbc.gridx = 1;
        frame.add(fieldFinalX, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        frame.add(labelInicialY, gbc);
        gbc.gridx = 1;
        frame.add(fieldInicialY, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        frame.add(labelFinalY, gbc);
        gbc.gridx = 1;
        frame.add(fieldFinalY, gbc);

        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(calcularButton, gbc);

        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double X = Double.parseDouble(fieldX.getText());
                double Xm = Double.parseDouble(fieldXm.getText());
                double XM = Double.parseDouble(fieldXM.getText());
                double Y = Double.parseDouble(fieldY.getText());
                double Ym = Double.parseDouble(fieldYm.getText());
                double YM = Double.parseDouble(fieldYM.getText());
                double coordenadaInicialX = Double.parseDouble(fieldInicialX.getText());
                double coordenadaFinalX = Double.parseDouble(fieldFinalX.getText());
                double coordenadaInicialY = Double.parseDouble(fieldInicialY.getText());
                double coordenadaFinalY = Double.parseDouble(fieldFinalY.getText());

                Coordenadas coordenadas = new Coordenadas();
                double[] ndcValues = coordenadas.inpToNdc(X, Xm, XM, Y, Ym, YM, coordenadaInicialX, coordenadaFinalX, coordenadaInicialY, coordenadaFinalY);
                double NDCX = ndcValues[0];
                double NDCY = ndcValues[1];

                double[] dcValues = coordenadas.ndcToDc(NDCX, NDCY, SCREEN_WIDTH, SCREEN_HEIGHT, coordenadaInicialX, coordenadaFinalX, coordenadaInicialY, coordenadaFinalY);
                double DCX = dcValues[0];
                double DCY = dcValues[1];

                // Open a new window to display the result with a Cartesian plane and results
                JFrame resultFrame = new JFrame("Resultado da Transformação");
                resultFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
                resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                // Adiciona o painel do plano cartesiano com labels
                PlanoCartesianoPanel panel = new PlanoCartesianoPanel((int) DCX, (int) (SCREEN_HEIGHT - DCY), NDCX, NDCY, DCX, DCY);
                resultFrame.add(panel, BorderLayout.CENTER);

                // Adiciona os resultados na parte inferior da janela
                JPanel resultPanel = new JPanel();
                resultPanel.setLayout(new GridLayout(3, 1));

                JLabel ndcLabel = new JLabel(String.format("NDCX: %.3f, NDCY: %.3f", NDCX, NDCY));
                ndcLabel.setFont(labelFont);
                JLabel dcLabel = new JLabel(String.format("DCX: %.3f, DCY: %.3f", DCX, DCY));
                dcLabel.setFont(labelFont);

                resultPanel.add(ndcLabel);
                resultPanel.add(dcLabel);

                resultFrame.add(resultPanel, BorderLayout.SOUTH);
                
                resultFrame.setVisible(true);
            }
        });

        frame.setVisible(true);
    }
}

class Coordenadas {

    public double calcularNdcx(double x, double xMin, double xMax, double coordenadaInicialX, double coordenadaFinalX) {
        double ndcx = ((x - xMin) / (xMax - xMin)) * (coordenadaFinalX - coordenadaInicialX) + coordenadaInicialX;
        return ndcx;
    }

    public double calcularNdcy(double y, double yMin, double yMax, double coordenadaInicialY, double coordenadaFinalY) {
        double ndcy = ((y - yMin) / (yMax - yMin)) * (coordenadaFinalY - coordenadaInicialY) + coordenadaInicialY;
        return ndcy;
    }

    public double[] inpToNdc(double x, double xMin, double xMax, double y, double yMin, double yMax, double coordenadaInicialX, double coordenadaFinalX, double coordenadaInicialY, double coordenadaFinalY) {
        double ndcx = calcularNdcx(x, xMin, xMax, coordenadaInicialX, coordenadaFinalX);
        double ndcy = calcularNdcy(y, yMin, yMax, coordenadaInicialY, coordenadaFinalY);
        double[] coords = {ndcx, ndcy};
        return coords;
    }

    public double calcularDcx(double ndcx, double ndh, double coordenadaInicialX, double coordenadaFinalX) {
        double dcx = Math.round(((ndcx - coordenadaInicialX) * (ndh - 1)) / (coordenadaFinalX - coordenadaInicialX));
        return dcx;
    }

    public double calcularDcy(double ndcy, double ndv, double coordenadaInicialY, double coordenadaFinalY) {
        double dcy = Math.round(((ndcy - coordenadaInicialY) * (ndv - 1)) / (coordenadaFinalY - coordenadaInicialY));
        return dcy;
    }

    public double[] ndcToDc(double ndcx, double ndcy, double ndh, double ndv, double coordenadaInicialX, double coordenadaFinalX, double coordenadaInicialY, double coordenadaFinalY) {
        double dcx = calcularDcx(ndcx, ndh, coordenadaInicialX, coordenadaFinalX);
        double dcy = calcularDcy(ndcy, ndv, coordenadaInicialY, coordenadaFinalY);
        double[] coords = {dcx, dcy};
        return coords;
    }

    public double[] userToNdc(double X, double Xm, double XM, double Y, double Ym, double YM) {
        double NDCX = (X - Xm) / (XM - Xm);
        double NDCY = (Y - Ym) / (YM - Ym);
        return new double[]{NDCX, NDCY};
    }

    public double[] ndcToUser(double NDCX, double Xm, double XM, double NDCY, double Ym, double YM) {
        double X = NDCX * (XM - Xm) + Xm;
        double Y = NDCY * (YM - Ym) + Ym;
        return new double[]{X, Y};
    }
}

class PlanoCartesianoPanel extends JPanel {
    private int DCX;
    private int DCY;
    private double NDCX;
    private double NDCY;
    private double DCXDisplay;
    private double DCYDisplay;

    public PlanoCartesianoPanel(int DCX, int DCY, double NDCX, double NDCY, double DCXDisplay, double DCYDisplay) {
        this.DCX = DCX;
        this.DCY = DCY;
        this.NDCX = NDCX;
        this.NDCY = NDCY;
        this.DCXDisplay = DCXDisplay;
        this.DCYDisplay = DCYDisplay;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlanoCartesiano(g);
        drawPoint(g, DCX, DCY);
    }

    private void drawPlanoCartesiano(Graphics g) {
        // Desenha o eixo X e Y
        int centerX = SistemaDeTransformacoesGUI.getScreenWidth() / 2;
        int centerY = SistemaDeTransformacoesGUI.getScreenHeight() / 2;

        g.setColor(Color.BLACK);
        g.drawLine(0, centerY, SistemaDeTransformacoesGUI.getScreenWidth(), centerY); // Eixo X
        g.drawLine(centerX, 0, centerX, SistemaDeTransformacoesGUI.getScreenHeight()); // Eixo Y

        // Desenha as linhas do grid com base em 100 pixels de intervalo
        g.setColor(Color.LIGHT_GRAY);

        // Linhas horizontais
        for (int i = centerY; i >= 0; i -= 100) {
            g.drawLine(0, i, getWidth(), i);
        }
        for (int i = centerY; i <= getHeight(); i += 100) {
            g.drawLine(0, i, getWidth(), i);
        }

        // Linhas verticais
        for (int i = centerX; i >= 0; i -= 100) {
            g.drawLine(i, 0, i, getHeight());
        }
        for (int i = centerX; i <= getWidth(); i += 100) {
            g.drawLine(i, 0, i, getHeight());
        }

        // Marcadores nos eixos
        g.setColor(Color.RED);
        for (int i = centerX; i <= getWidth(); i += 100) {
            g.drawLine(i, centerY - 5, i, centerY + 5); // Marcadores no eixo X (positivo)
        }
        for (int i = centerX; i >= 0; i -= 100) {
            g.drawLine(i, centerY - 5, i, centerY + 5); // Marcadores no eixo X (negativo)
        }
        for (int i = centerY; i <= getHeight(); i += 100) {
            g.drawLine(centerX - 5, i, centerX + 5, i); // Marcadores no eixo Y (positivo)
        }
        for (int i = centerY; i >= 0; i -= 100) {
            g.drawLine(centerX - 5, i, centerX + 5, i); // Marcadores no eixo Y (negativo)
        }
    }

    private void drawPoint(Graphics g, int x, int y) {
        g.setColor(Color.BLUE);
        g.fillRect(x - 2, y - 2, 5, 5); // Desenha um pequeno quadrado de 7x7 pixels para representar o ponto
    }
}
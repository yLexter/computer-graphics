package tudo3D.geometry3d.planeCartesians3d;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cartesian3D extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int AXIS_LENGTH = 200;

    // Coordenadas 3D do ponto que queremos adicionar
    private int pointX = 50; // Valor de exemplo
    private int pointY = 50; // Valor de exemplo
    private int pointZ = 50; // Valor de exemplo

    public Cartesian3D() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
    }

    // Função para projetar um ponto 3D em uma tela 2D usando uma simples transformação em perspectiva
    private Point projectPoint(int x, int y, int z) {
        int perspectiveFactor = 500; // Quanto maior, menor o efeito de perspectiva
        int screenX = WIDTH / 2 + x * perspectiveFactor / (z + perspectiveFactor);
        int screenY = HEIGHT / 2 - y * perspectiveFactor / (z + perspectiveFactor);
        return new Point(screenX, screenY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Desenhar o eixo X
        Point xStart = projectPoint(-AXIS_LENGTH, 0, 0);
        Point xEnd = projectPoint(AXIS_LENGTH, 0, 0);
        g2d.setColor(Color.RED);
        g2d.drawLine(xStart.x, xStart.y, xEnd.x, xEnd.y);
        g2d.drawString("X", xEnd.x, xEnd.y);

        // Desenhar o eixo Y
        Point yStart = projectPoint(0, -AXIS_LENGTH, 0);
        Point yEnd = projectPoint(0, AXIS_LENGTH, 0);
        g2d.setColor(Color.GREEN);
        g2d.drawLine(yStart.x, yStart.y, yEnd.x, yEnd.y);
        g2d.drawString("Y", yEnd.x, yEnd.y);

        // Desenhar o eixo Z
        Point zStart = projectPoint(0, 0, -AXIS_LENGTH);
        Point zEnd = projectPoint(0, 0, AXIS_LENGTH);
        g2d.setColor(Color.BLUE);
        g2d.drawLine(zStart.x, zStart.y, zEnd.x, zEnd.y);
        g2d.drawString("Z", zEnd.x, zEnd.y);

        // Desenhar o ponto no espaço 3D
        Point projectedPoint = projectPoint(pointX, pointY, pointZ);
        g2d.setColor(Color.BLACK);
        g2d.fillOval(projectedPoint.x - 3, projectedPoint.y - 3, 6, 6); // Ponto de tamanho 6x6 pixels
        g2d.drawString("P(" + pointX + ", " + pointY + ", " + pointZ + ")", projectedPoint.x + 5, projectedPoint.y - 5);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Plano Cartesiano 3D");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Cartesian3D());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}


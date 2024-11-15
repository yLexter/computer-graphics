import geometry.figures.Square;
import geometry.planeCartesians.CartesianPlane2D;
import viewportNew.Viewport2D;
import geometry.points.Point2D;
import primitives.MidpointLine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Viewport2DTest {

    public static void main(String[] args) {
        // Janela principal para o plano cartesiano
        JFrame mainFrame = new JFrame("Plano Cartesiano");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700, 500);

        CartesianPlane2D plane = new CartesianPlane2D();

        // Painel para exibir o plano cartesiano
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(plane.getImage(), 0, 0, null);
            }
        };

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);

        // Definir os vértices do quadrado
        Point2D[] vertices = {
                new Point2D(-10, -10),
                new Point2D(-10, 10),
                new Point2D(10, 10),
                new Point2D(10, -10)
        };

        // Criar o quadrado e desenhá-lo no plano cartesiano
        Square square = new Square(vertices, new MidpointLine());
        square.iterateToPoints(point -> plane.setPixel(point, Color.RED.getRGB()));
        mainPanel.repaint();

        // Janela separada para a viewport
        JFrame viewportFrame = new JFrame("Viewport");
        viewportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewportFrame.setSize(250, 250);

        Viewport2D viewport = new Viewport2D(0, 0, 200, 200);

        // Painel para exibir a viewport sem os eixos
        JPanel viewportPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                viewport.clearViewport();

                // Lista para os vértices normalizados na viewport
                List<Point2D> viewportVertices = new ArrayList<>();

                // Normalizar cada vértice do quadrado para a viewport usando o método mapToViewport
                for (Point2D vertex : vertices) {
                    Point2D viewportPoint = viewport.mapToViewport((int) vertex.x, (int) vertex.y, -50, -50, 50, 50);
                    if (viewportPoint != null) {
                        viewportVertices.add(viewportPoint);
                    }
                }

                // Instancia a linha midpoint sem construtor e define o callback com a cor
                MidpointLine lineAlgorithm = new MidpointLine();
                lineAlgorithm.setCallback(point -> viewport.setPixel(point, Color.RED.getRGB()));

                // Desenhar as linhas do quadrado usando os vértices normalizados
                for (int i = 0; i < viewportVertices.size(); i++) {
                    Point2D start = viewportVertices.get(i);
                    Point2D end = viewportVertices.get((i + 1) % viewportVertices.size());
                    lineAlgorithm.desenhaLinha(start, end);
                }

                // Renderizar a imagem da viewport
                viewport.draw(g);
            }
        };

        viewportFrame.add(viewportPanel);
        viewportFrame.setVisible(true);

        // Atualizar a viewport sem rotação e sem os eixos
        viewportPanel.repaint();
    }
}


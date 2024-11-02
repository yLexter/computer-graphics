package tudo3D.geometry3d.planeCartesians3d;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.function.Consumer;

import tudo3D.geometry3d.points3d.Point3D;
import geometry.points.Point2D;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Plano Cartesiano 3D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        // Painel para desenhar o plano 3D
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                // Define o callback para desenhar pontos no painel
                Consumer<Point3D> callback = point -> {
                    Point2D projected = projectPoint(point, getWidth(), getHeight());
                    g.drawRect((int) projected.getX(), (int) projected.getY(), 1, 1);
                };
                
                // Cria o plano cartesiano 3D e desenha os eixos com comprimento adaptado ao tamanho da tela
                CartesianPlane3D plane3D = new CartesianPlane3D(callback);
                plane3D.drawAxes(g, getWidth(), getHeight());
            }
            
            // Função de projeção (igual ao método em CartesianPlane3DExample)
            private Point2D projectPoint(Point3D point, int width, int height) {
                double scale = 100.0; // Define uma escala para a projeção
                double x = point.getX() / (point.getZ() / scale + scale);
                double y = point.getY() / (point.getZ() / scale + scale);
                return new Point2D(x + width / 2, -y + height / 2); // Ajusta para o centro da tela
            }
        };

        
        
        panel.setPreferredSize(new Dimension(800, 600));
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela
        frame.setVisible(true);

    }
}

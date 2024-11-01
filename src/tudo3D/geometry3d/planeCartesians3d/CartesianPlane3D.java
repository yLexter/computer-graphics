package tudo3D.geometry3d.planeCartesians3d;

import tudo3D.geometry3d.points3d.Point3D;
import geometry.points.Point2D;
import tudo3D.transformations3d.MidpointLine3D;
import primitives.MidpointLine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.function.Consumer;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CartesianPlane3D extends JPanel {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int CUBE_SIZE = 100;
    protected Consumer<Point3D> callback;

    public CartesianPlane3D(Consumer<Point3D> callback) {
        this.callback = callback;
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Define o fundo do plano cartesiano
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        
        // Desenha os eixos 3D
        drawAxes(g2d, FRAME_WIDTH, FRAME_HEIGHT);
        
        // Desenha o cubo 3D no plano cartesiano
        drawCube(g2d);
    }

    public void drawAxes(Graphics g, int width, int height) {
        Point3D origin = new Point3D(0, 0, 0);
        int axisLength = Math.min(width, height) * 90;

        Point3D zPositive = new Point3D(0, axisLength, 0);
        Point3D zNegative = new Point3D(0, -axisLength, 0);
        Point3D xPositive = new Point3D((int) (axisLength * Math.cos(Math.toRadians(0))),
                (int) (-axisLength * Math.sin(Math.toRadians(0))), 0);
        Point3D xNegative = new Point3D((int) (-axisLength * Math.cos(Math.toRadians(0))),
                (int) (axisLength * Math.sin(Math.toRadians(0))), 0);
        Point3D yPositive = new Point3D((int) (-axisLength * Math.cos(Math.toRadians(45))),
                (int) (-axisLength * Math.sin(Math.toRadians(45))), 0);
        Point3D yNegative = new Point3D((int) (axisLength * Math.cos(Math.toRadians(45))),
                (int) (axisLength * Math.sin(Math.toRadians(45))), 0);

        MidpointLine3D lineDrawer = new MidpointLine3D(point -> {
            Point2D projected = projectPoint(point, width, height);
            g.drawRect((int) projected.getX(), (int) projected.getY(), 1, 1);
        });

        g.setColor(Color.RED);
        lineDrawer.desenhaLinha(origin, xPositive);
        lineDrawer.desenhaLinha(origin, xNegative);

        g.setColor(Color.BLUE);
        lineDrawer.desenhaLinha(origin, yPositive);
        lineDrawer.desenhaLinha(origin, yNegative);

        g.setColor(Color.GREEN);
        lineDrawer.desenhaLinha(origin, zPositive);
        lineDrawer.desenhaLinha(origin, zNegative);
    }

    private Point2D projectPoint(Point3D point, int width, int height) {
        double scale = 100.0;
        double x = point.getX() / (point.getZ() / scale + scale);
        double y = point.getY() / (point.getZ() / scale + scale);
        return new Point2D(x + width / 2, -y + height / 2);
    }

    private void drawCube(Graphics2D g2d) {
        int x = (FRAME_WIDTH - CUBE_SIZE) / 2;
        int y = (FRAME_HEIGHT - CUBE_SIZE) / 2;

        g2d.setColor(Color.black);

        // Inicializa o MidpointLine para desenhar as linhas do cubo
        MidpointLine lineDrawer = new MidpointLine(p -> {
            int px = (int) p.getX();
            int py = (int) p.getY();
            g2d.drawRect(px, py, 1, 1);
        });

        drawRectangularFace(lineDrawer, new Point2D(x, y), CUBE_SIZE, CUBE_SIZE);
        drawRectangularFace(lineDrawer, new Point2D(x + CUBE_SIZE / 2, y - CUBE_SIZE / 2), CUBE_SIZE, CUBE_SIZE);
        connectFaces(lineDrawer, new Point2D(x, y), new Point2D(x + CUBE_SIZE / 2, y - CUBE_SIZE / 2), CUBE_SIZE, CUBE_SIZE);
    }

    private void drawRectangularFace(MidpointLine lineDrawer, Point2D topLeft, int width, int height) {
        lineDrawer.desenhaLinha(topLeft, new Point2D(topLeft.x + width, topLeft.y));
        lineDrawer.desenhaLinha(new Point2D(topLeft.x + width, topLeft.y), new Point2D(topLeft.x + width, topLeft.y + height));
        lineDrawer.desenhaLinha(new Point2D(topLeft.x + width, topLeft.y + height), new Point2D(topLeft.x, topLeft.y + height));
        lineDrawer.desenhaLinha(new Point2D(topLeft.x, topLeft.y + height), topLeft);
    }

    private void connectFaces(MidpointLine lineDrawer, Point2D frontTopLeft, Point2D backTopLeft, int width, int height) {
        lineDrawer.desenhaLinha(frontTopLeft, backTopLeft);
        lineDrawer.desenhaLinha(new Point2D(frontTopLeft.x + width, frontTopLeft.y), new Point2D(backTopLeft.x + width, backTopLeft.y));
        lineDrawer.desenhaLinha(new Point2D(frontTopLeft.x + width, frontTopLeft.y + height), new Point2D(backTopLeft.x + width, backTopLeft.y + height));
        lineDrawer.desenhaLinha(new Point2D(frontTopLeft.x, frontTopLeft.y + height), new Point2D(backTopLeft.x, backTopLeft.y + height));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("3D Cartesian Plane with Cube");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new CartesianPlane3D(point -> {}));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

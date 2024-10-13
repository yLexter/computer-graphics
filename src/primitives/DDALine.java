package primitives;

import geomtry.points.Point2D;
import pixel.Coordinates;
import utils.BasePrimitives;
import geomtry.planeCartesians.CartesianPlane2D;

// TODo consertar as coordenadas depois

/*
public class DDALine extends BasePrimitives {

    public DDALine(CartesianPlane2D cartesianPlane) {
        super(cartesianPlane);
    }

    public void desenhaLinha(int x1, int y1, int x2, int y2) {
        Coordinates coords = new Coordinates();

        int xMin = -250;
        int xMax = 250;
        int yMin = -250;
        int yMax = 250;
        int coordenadaInicialX = -1;
        int coordenadaFinalX = 1;
        int coordenadaInicialY = -1;
        int coordenadaFinalY = 1;

        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            int height = cartesianPlane.getHeight();
            int width = cartesianPlane.getWidth();

            float[] coordsNdcP1 = coords.inpToNdc(x, xMin, xMax, y, yMin, yMax, coordenadaInicialX, coordenadaFinalX, coordenadaInicialY, coordenadaFinalY);
            float[] coordsDcP1 = coords.ndcToDc(coordsNdcP1[0], coordsNdcP1[1], width, height, coordenadaInicialX, coordenadaFinalX, coordenadaInicialY, coordenadaFinalY);

            double xPixel = Math.round(coordsDcP1[0]);
            double yPixel = Math.round(height - coordsDcP1[1]);

            cartesianPlane.setPixel(new Point2D(xPixel, yPixel), getColor()); // Define o tamanho do retângulo

            x += xIncrement;
            y += yIncrement;
        }
        
    }
}
*/

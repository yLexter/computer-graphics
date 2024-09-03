package tranformations2d;

import utils.BasePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Translation extends BasePanel {
    private int width, height;

    public Translation(int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.width = width;
        this.height = height;
    }

    // Algoritmo de translação de cada ponto
    private double[] translatingPoint(double x, double y, double tx, double ty){
        double xnew =  x + tx;
        double ynew = y + ty;

        return new double[]{xnew, ynew};
    }

    // Algoritmo que realiza a translação de todos os pontos
    public double[][] translation(double[][] sides, double tx, double ty){
        double[][] newCoords = new double[sides.length][2]; // matriz das coordenadas dos pontos

        for(int i = 0; i < sides.length; i++){
            newCoords[i][0] = translatingPoint(sides[i][0], sides[i][1], tx, ty)[0];
            newCoords[i][1] = translatingPoint(sides[i][0], sides[i][1], tx, ty)[1];
        }

        return newCoords;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

package transformations2d;

import utils.BasePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Scale extends BasePanel{
    private int width, height;

    public Scale(int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.width = width;
        this.height = height;
    }

    // Algoritmo de escala de cada ponto
    private double[][] scalingPoint(double x, double y, double sx, double sy){
        double[][] newCoords = new double[2][1]; //sempre vai resultar em uma matriz 2x1 por se tratar de um ponto

        for(int i = 0; i < newCoords.length; i++){
            newCoords[i][0] = sx * x;
            newCoords[i][1] = sy * y;
        }

        return newCoords;
    }

    // Algoritmo que realiza a escala de todos os pontos
    public double[][] scalation(double[][] sides, double sx, double sy){
        double[][] newCoords = new double[sides.length][2]; // matriz das coordenadas dos pontos

        for(int i = 0; i < sides.length; i++){
            newCoords[i][0] = scalingPoint(sides[i][0], sides[i][1], sx, sy)[i][0];
            newCoords[i][1] = scalingPoint(sides[i][0], sides[i][1], sx, sy)[i][1];
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

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
    private double[] scalingPoint(double x, double y, double sx, double sy){
        double[] newCoords = new double[2]; // Coordenadas resultantes para o ponto (x, y)
    
        newCoords[0] = sx * x; // Escala a coordenada X
        newCoords[1] = sy * y; // Escala a coordenada Y
    
        return newCoords;
    }

    // Algoritmo que realiza a escala de todos os pontos
    public double[][] scalation(double[][] sides, double sx, double sy){
        double[][] newCoords = new double[sides.length][2]; // Matriz das coordenadas dos pontos escalados
    
        for(int i = 0; i < sides.length; i++){
            double[] scaledPoint = scalingPoint(sides[i][0], sides[i][1], sx, sy); // Escala o ponto atual
            newCoords[i][0] = scaledPoint[0]; // Atualiza a coordenada X
            newCoords[i][1] = scaledPoint[1]; // Atualiza a coordenada Y
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

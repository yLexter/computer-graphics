package tranformations2d;

import utils.BasePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Shear extends BasePanel{ //Cisalhamento
    private int width, height;

    public Shear(int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.width = width;
        this.height = height;
    }

    private double[] shearingInX(double x, double y, double b){ // b = grau de distorção
        return new double[]{x + b * y, y};
    }

    private double[] shearingInY(double x, double y, double a){ // a = grau de distorção
        return new double[]{x, y + a * x};
    }

    private double[] shearingInXY(double x, double y, double b, double a){ // a e b = grau de distorção
        return new double[]{x + b * y, y + a * x};
    }

    public double[][] shearInX(double[][] sides, double b){
        double[][] newCoords = new double[sides.length][2];

        for(int i = 0; i < sides.length; i++){
            newCoords[i][0] = shearingInX(sides[i][0], sides[i][1], b)[0];
            newCoords[i][1] = shearingInX(sides[i][0], sides[i][1], b)[1];
        }
        return newCoords;
    }

    public double[][] shearInY(double[][] sides, double a){
        double[][] newCoords = new double[sides.length][2];

        for(int i = 0; i < sides.length; i++){
            newCoords[i][0] = shearingInY(sides[i][0], sides[i][1], a)[0];
            newCoords[i][1] = shearingInY(sides[i][0], sides[i][1], a)[1];
        }
        return newCoords;
    }

    public double[][] shearInXY(double[][] sides, double b, double a){
        double[][] newCoords = new double[sides.length][2];

        for(int i = 0; i < sides.length; i++){
            newCoords[i][0] = shearingInXY(sides[i][0], sides[i][1], b, a)[0];
            newCoords[i][1] = shearingInXY(sides[i][0], sides[i][1], b, a)[1];
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

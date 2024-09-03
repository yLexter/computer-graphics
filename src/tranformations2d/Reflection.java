package tranformations2d;

import utils.BasePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Reflection extends BasePanel{
    private int width, height;

    public Reflection(int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.width = width;
        this.height = height;
    }

    private double[] reflectingPointInX(double x, double y){
        return new double[]{x, -y};
    }
 
    private double[] reflectingPointInY(double x, double y){
        return new double[]{-x, y};
    }

    private double[] reflectingPointInOrigin(double x, double y){
        return new double[]{-x, -y};
    }

    private double[] reflectingPointInLineYX(double x, double y){
        return new double[]{y, x};
    }

    public double[][] reflectionInX(double[][] sides){
        double[][] newCoords = new double[sides.length][2];

        for(int i = 0; i < sides.length; i++){
            newCoords[i][0] = reflectingPointInX(sides[i][0], sides[i][1])[0];
            newCoords[i][1] = reflectingPointInX(sides[i][0], sides[i][1])[1];
        }
        return newCoords;
    }

    public double[][] reflectionInY(double[][] sides){
        double[][] newCoords = new double[sides.length][2];

        for(int i = 0; i < sides.length; i++){
            newCoords[i][0] = reflectingPointInY(sides[i][0], sides[i][1])[0];
            newCoords[i][1] = reflectingPointInY(sides[i][0], sides[i][1])[1];
        }
        return newCoords;
    }

    public double[][] reflectingPointInOrigin(double[][] sides){
        double[][] newCoords = new double[sides.length][2];

        for(int i = 0; i < sides.length; i++){
            newCoords[i][0] = reflectingPointInOrigin(sides[i][0], sides[i][1])[0];
            newCoords[i][1] = reflectingPointInOrigin(sides[i][0], sides[i][1])[1];
        }
        return newCoords;
    }

    public double[][] reflectingPointInLineYX(double[][] sides){
        double[][] newCoords = new double[sides.length][2];

        for(int i = 0; i < sides.length; i++){
            newCoords[i][0] = reflectingPointInLineYX(sides[i][0], sides[i][1])[0];
            newCoords[i][1] = reflectingPointInLineYX(sides[i][0], sides[i][1])[1];
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

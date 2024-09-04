package transformations2d;

import utils.BasePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Rotation extends BasePanel{
    private int width, height;

    public Rotation(int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.width = width;
        this.height = height;
    }

    private double[] rotatingPoint(double x, double y, double angulo){
        double xnew = x * Math.cos(angulo) - y * Math.sin(angulo);
        double ynew = x * Math.sin(angulo) + y * Math.cos(angulo);
        
        return new double[]{xnew, ynew};
    }

    public double[][] rotation(double[][] sides, double angulo){
        double[][] newCoords = new double[sides.length][2];

        for(int i = 0; i < sides.length; i++){
            newCoords[i][0] = rotatingPoint(sides[i][0], sides[i][1], angulo)[0];
            newCoords[i][1] = rotatingPoint(sides[i][0], sides[i][1], angulo)[1];
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

package primitives;

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

    // Algoritmo de translação dos pontos
    public int[] translatingPoints(int x, int y, int tx, int ty){
        int xnew =  x + tx;
        int ynew = y + ty;

        return new int[]{xnew, ynew};
    }

    // Algoritmo 
    public void translation(int[][] sides, int tx, int ty){
        int[][] newCoords = new int[sides.length][2];

        for(int i = 0; i < sides.length; i++){
            translatingPoints(sides[i][0], sides[i][1], tx, ty);
        }
    }
}

package geomtry.planeCartesians;

import geomtry.points.Point2D;
import javax.swing.*;

public abstract class BaseCartesianPlane extends JPanel {
    public abstract void setPixel(Point2D coordinates, int rgb);
    public abstract void drawCartesianPlane();
    public abstract BaseCartesianPlane reset();
    public abstract void clearCartesianPlane();
}





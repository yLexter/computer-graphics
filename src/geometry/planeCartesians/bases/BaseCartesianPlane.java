package geometry.planeCartesians.bases;

import geometry.points.Point2D;
import javax.swing.*;

public abstract class BaseCartesianPlane extends JPanel {
    public abstract void setPixel(Point2D coordinates, int rgb);
    public abstract void drawCartesianPlane();
    public abstract BaseCartesianPlane reset();
    public abstract void clearCartesianPlane();
}

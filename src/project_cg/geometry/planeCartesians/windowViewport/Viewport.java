package project_cg.geometry.planeCartesians.windowViewport;

public class Viewport {
    public int xMin, xMax, yMin, yMax;

    public Viewport(int xMin, int xMax, int yMin, int yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    public int getXMin() {
        return xMin;
    }

    public int getXMax() {
        return xMax;
    }

    public int getYMin() {
        return yMin;
    }

    public int getYMax() {
        return yMax;
    }

    @Override
    public String toString() {
        return "Viewport{" +
                "xMin=" + xMin +
                ", xMax=" + xMax +
                ", yMin=" + yMin +
                ", yMax=" + yMax +
                '}';
    }
}

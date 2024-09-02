package pixel;

public class Coordinates {

    public double calcularNdcx(double x, double xMin, double xMax, double coordenadaInicialX, double coordenadaFinalX) {
        double ndcx = ((x - xMin) / (xMax - xMin)) * (coordenadaFinalX - coordenadaInicialX) + coordenadaInicialX;
        return ndcx;
    }

    public double calcularNdcy(double y, double yMin, double yMax, double coordenadaInicialY, double coordenadaFinalY) {
        double ndcy = ((y - yMin) / (yMax - yMin)) * (coordenadaFinalY - coordenadaInicialY) + coordenadaInicialY;
        return ndcy;
    }

    public double[] inpToNdc(double x, double xMin, double xMax, double y, double yMin, double yMax, double coordenadaInicialX, double coordenadaFinalX, double coordenadaInicialY, double coordenadaFinalY) {
        double ndcx = calcularNdcx(x, xMin, xMax, coordenadaInicialX, coordenadaFinalX);
        double ndcy = calcularNdcy(y, yMin, yMax, coordenadaInicialY, coordenadaFinalY);
        double[] coords = {ndcx, ndcy};
        return coords;
    }

    public double calcularDcx(double ndcx, double ndh, double coordenadaInicialX, double coordenadaFinalX) {
        double dcx = Math.round(((ndcx - coordenadaInicialX) * (ndh - 1)) / (coordenadaFinalX - coordenadaInicialX));
        return dcx;
    }

    public double calcularDcy(double ndcy, double ndv, double coordenadaInicialY, double coordenadaFinalY) {
        double dcy = Math.round(((ndcy - coordenadaInicialY) * (ndv - 1)) / (coordenadaFinalY - coordenadaInicialY));
        return dcy;
    }

    public double[] ndcToDc(double ndcx, double ndcy, double ndh, double ndv, double coordenadaInicialX, double coordenadaFinalX, double coordenadaInicialY, double coordenadaFinalY) {
        double dcx = calcularDcx(ndcx, ndh, coordenadaInicialX, coordenadaFinalX);
        double dcy = calcularDcy(ndcy, ndv, coordenadaInicialY, coordenadaFinalY);
        double[] coords = {dcx, dcy};
        return coords;
    }

    public double[] userToNdc(double X, double Xm, double XM, double Y, double Ym, double YM) {
        double NDCX = (X - Xm) / (XM - Xm);
        double NDCY = (Y - Ym) / (YM - Ym);
        return new double[]{NDCX, NDCY};
    }

    public double[] ndcToUser(double NDCX, double Xm, double XM, double NDCY, double Ym, double YM) {
        double X = NDCX * (XM - Xm) + Xm;
        double Y = NDCY * (YM - Ym) + Ym;
        return new double[]{X, Y};
    }

}

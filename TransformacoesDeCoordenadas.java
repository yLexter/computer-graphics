
class TranformacoesDeCoordenadas {
    public static double[] inp_to_ndc(double x, double y, double xMin, double xMax, double yMin, double yMax) {
        double ndcX = (x - xMin) / (xMax - xMin);
        double ndcY = (y - yMin) / (yMax - yMin);
        return new double[]{ndcX, ndcY};
    }

    public static double[] ndcToUser(double ndcU, double ndcV, double uMin, double uMax, double vMin, double vMax) {
        double u = ndcU * (uMax - uMin) + uMin;
        double v = ndcV * (vMax - vMin) + vMin;
        return new double[]{u, v};
    }

    public static double[] userToNdc(double u, double v, double uMin, double uMax, double vMin, double vMax) {
        double ndcU = (u - uMin) / (uMax - uMin);
        double ndcV = (v - vMin) / (vMax - vMin);
        return new double[]{ndcU, ndcV};
    }

    public static double[] ndcToDc(double ndcX, double ndcY, double xMin, double xMax, double yMin, double yMax) {
        double x = ndcX * (xMax - xMin) + xMin;
        double y = ndcY * (yMax - yMin) + yMin;
        return new double[]{x, y};
    }
}
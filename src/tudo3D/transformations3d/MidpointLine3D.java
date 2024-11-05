package tudo3D.transformations3d;

import tudo3D.geometry3d.points3d.Point3D;
import java.util.function.Consumer;

public class MidpointLine3D extends BaseLine3D {

    public MidpointLine3D(Consumer<Point3D> callback) {
        super(callback);
    }

    public MidpointLine3D() {}

    // Função para desenhar a linha em 3D usando pontos Point3D
    @Override
    public void desenhaLinha(Point3D start, Point3D end) {
        int x1 = (int) start.getX();
        int y1 = (int) start.getY();
        int z1 = (int) start.getZ();
        int x2 = (int) end.getX();
        int y2 = (int) end.getY();
        int z2 = (int) end.getZ();

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int dz = Math.abs(z2 - z1);

        int xs = (x2 > x1) ? 1 : -1;
        int ys = (y2 > y1) ? 1 : -1;
        int zs = (z2 > z1) ? 1 : -1;

        if (dx >= dy && dx >= dz) {
            // Eixo X é dominante
            desenhaEixoX(x1, y1, z1, dx, dy, dz, xs, ys, zs);
        } else if (dy >= dx && dy >= dz) {
            // Eixo Y é dominante
            desenhaEixoY(x1, y1, z1, dx, dy, dz, xs, ys, zs);
        } else {
            // Eixo Z é dominante
            desenhaEixoZ(x1, y1, z1, dx, dy, dz, xs, ys, zs);
        }
    }

    private void desenhaEixoX(int x1, int y1, int z1, int dx, int dy, int dz, int xs, int ys, int zs) {
        int p1 = 2 * dy - dx;
        int p2 = 2 * dz - dx;
        int y = y1;
        int z = z1;

        for (int x = x1; x != x1 + dx * xs; x += xs) {
            callback.accept(new Point3D(x, y, z));
            if (p1 >= 0) {
                y += ys;
                p1 -= 2 * dx;
            }
            if (p2 >= 0) {
                z += zs;
                p2 -= 2 * dx;
            }
            p1 += 2 * dy;
            p2 += 2 * dz;
        }
    }

    private void desenhaEixoY(int x1, int y1, int z1, int dx, int dy, int dz, int xs, int ys, int zs) {
        int p1 = 2 * dx - dy;
        int p2 = 2 * dz - dy;
        int x = x1;
        int z = z1;

        for (int y = y1; y != y1 + dy * ys; y += ys) {
            callback.accept(new Point3D(x, y, z));
            if (p1 >= 0) {
                x += xs;
                p1 -= 2 * dy;
            }
            if (p2 >= 0) {
                z += zs;
                p2 -= 2 * dy;
            }
            p1 += 2 * dx;
            p2 += 2 * dz;
        }
    }

    private void desenhaEixoZ(int x1, int y1, int z1, int dx, int dy, int dz, int xs, int ys, int zs) {
        int p1 = 2 * dy - dz;
        int p2 = 2 * dx - dz;
        int y = y1;
        int x = x1;

        for (int z = z1; z != z1 + dz * zs; z += zs) {
            callback.accept(new Point3D(x, y, z));
            if (p1 >= 0) {
                y += ys;
                p1 -= 2 * dz;
            }
            if (p2 >= 0) {
                x += xs;
                p2 -= 2 * dz;
            }
            p1 += 2 * dy;
            p2 += 2 * dx;
        }
    }
}


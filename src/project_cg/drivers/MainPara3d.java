package project_cg.drivers;

import project_cg.drivers.tudo3D.geometry3d.planeCartesians3d.CartesianPlane3D;
import project_cg.drivers.tudo3D.geometry3d.points3d.Point3D;
import project_cg.drivers.tudo3D.transformations3d.Rotation3D;

public class MainPara3d {

    public static void main(String[] args) {

        // Cria uma instância do plano cartesiano 3D
        CartesianPlane3D plane = new CartesianPlane3D();

        // Define os vértices de um cubo no primeiro quadrante com um vértice na origem
        Point3D[] cubeVertices = {
            new Point3D(0, 0, 0), new Point3D(1, 0, 0),
            new Point3D(1, 1, 0), new Point3D(0, 1, 0),
            new Point3D(0, 0, 1), new Point3D(1, 0, 1),
            new Point3D(1, 1, 1), new Point3D(0, 1, 1)
        };

        // Exibe os pontos antes da rotação
        System.out.println("Pontos do cubo antes da rotação:");
        for (Point3D point : cubeVertices) {
            System.out.println(point);
        }

        // Ângulo de rotação em graus
        double angle = 45.0;

        // Aplica rotação em torno do eixo Y para cada vértice do cubo
        Point3D[] rotatedVertices = new Point3D[cubeVertices.length];

        for (int i = 0; i < cubeVertices.length; i++) {
            rotatedVertices[i] = Rotation3D.rotateZ(cubeVertices[i], angle);
        }

        // Exibe os pontos após a rotação
        System.out.println("\nPontos do cubo após a rotação em torno do eixo Y:");
        for (Point3D point : rotatedVertices) {
            System.out.println(point);
        }

        // Passa os vértices rotacionados para o plano cartesiano e inicia o contexto OpenGL
        plane.update(rotatedVertices);
    }


}


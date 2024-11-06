import tudo3D.geometry3d.planeCartesians3d.CartesianPlane3D;
import tudo3D.geometry3d.points3d.Point3D;
import tudo3D.transformations3d.Reflection3D;

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

        // Exibe os pontos antes da reflexão
        System.out.println("Pontos do cubo antes da reflexão:");
        for (Point3D point : cubeVertices) {
            System.out.println(point);
        }

        // Escolha o tipo de reflexão: XY, XZ, YZ ou Origin
        String reflectionPlane = "Origin"; // Pode ser "XY", "XZ", "YZ", ou "Origin"

        // Aplica reflexão para cada vértice do cubo
        Point3D[] reflectedVertices = new Point3D[cubeVertices.length];
        for (int i = 0; i < cubeVertices.length; i++) {
            switch (reflectionPlane) {
                case "XY":
                    reflectedVertices[i] = Reflection3D.reflectInXY(cubeVertices[i]);
                    break;
                case "XZ":
                    reflectedVertices[i] = Reflection3D.reflectInXZ(cubeVertices[i]);
                    break;
                case "YZ":
                    reflectedVertices[i] = Reflection3D.reflectInYZ(cubeVertices[i]);
                    break;
                case "Origin":
                    reflectedVertices[i] = Reflection3D.reflectInOrigin(cubeVertices[i]);
                    break;
                default:
                    reflectedVertices[i] = cubeVertices[i]; // Sem reflexão
            }
        }

        // Exibe os pontos após a reflexão
        System.out.println("\nPontos do cubo após a reflexão no plano " + reflectionPlane + ":");
        for (Point3D point : reflectedVertices) {
            System.out.println(point);
        }

        // Passa os vértices refletidos para o plano cartesiano e inicia o contexto OpenGL
        plane.run(reflectedVertices);
    }
}

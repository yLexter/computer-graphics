import tudo3D.geometry3d.planeCartesians3d.CartesianPlane3D;
import tudo3D.geometry3d.points3d.Point3D;
import tudo3D.transformations3d.Translation3D;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class MainPara3d {
    public static void main(String[] args) {
        // Configura a interface gráfica Swing
        JFrame controlFrame = new JFrame("Controles");
        JButton translateButton = new JButton("Transladar Cubo");
        controlFrame.setSize(200, 100);
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.add(translateButton);
        controlFrame.setVisible(true);

        // Cria uma instância do plano cartesiano 3D
        CartesianPlane3D plane = new CartesianPlane3D();

        // Define os vértices de um cubo no primeiro quadrante com um vértice na origem
        Point3D[] cubeVertices = {
            new Point3D(0, 0, 0), new Point3D(1, 0, 0),
            new Point3D(1, 1, 0), new Point3D(0, 1, 0),
            new Point3D(0, 0, 1), new Point3D(1, 0, 1),
            new Point3D(1, 1, 1), new Point3D(0, 1, 1)
        };

        // Listener para o botão de translação
        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Define a translação (por exemplo, 3 unidades em x, 2 em y e 1 em z)
                Translation3D translation = new Translation3D(3, 2, 1);

                // Aplica a translação aos vértices do cubo permanentemente
                for (int i = 0; i < cubeVertices.length; i++) {
                    cubeVertices[i] = translation.apply(cubeVertices[i]);
                }
            }
        });

        // Inicializa o plano cartesiano e configura o contexto OpenGL
        plane.run();

        // Loop de renderização para desenhar o cubo e aplicar a translação quando o botão for pressionado
        while (!GLFW.glfwWindowShouldClose(plane.getWindow())) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GL11.glLoadIdentity();

            // Aplica uma rotação para ver os eixos e o cubo
            GL11.glRotatef(45, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(45, 0.0f, 1.0f, 0.0f);

            // Desenha os eixos
            plane.drawAxes();

            // Desenha o cubo na posição atual (transladada, se o botão foi pressionado)
            plane.drawCube(cubeVertices);

            // Troca os buffers para atualizar a tela
            GLFW.glfwSwapBuffers(plane.getWindow());

            // Processa eventos da janela
            GLFW.glfwPollEvents();
        }

        // Libera os recursos
        GLFW.glfwDestroyWindow(plane.getWindow());
        GLFW.glfwTerminate();
    }
}

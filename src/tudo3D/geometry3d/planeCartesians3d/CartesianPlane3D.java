package tudo3D.geometry3d.planeCartesians3d;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import tudo3D.geometry3d.points3d.Point3D;

public class CartesianPlane3D {
    private long window;
    private Point3D[] cubeVertices; // Armazena os vértices do cubo

    public void run(Point3D[] cubeVertices) {
        this.cubeVertices = cubeVertices; // Armazena os vértices fornecidos
        init();
        loop();
        
        // Libera recursos e encerra o GLFW
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    private void init() {
        // Inicializa o GLFW
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Falha ao inicializar GLFW");
        }

        // Cria a janela
        window = GLFW.glfwCreateWindow(800, 600, "Plano Cartesiano 3D com Cubo", 0, 0);
        if (window == 0) {
            throw new RuntimeException("Falha ao criar a janela GLFW");
        }

        // Configura o contexto da janela e o torna atual
        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwSwapInterval(1);

        // Inicializa o contexto OpenGL (LWJGL)
        GL.createCapabilities();

        // Configurações de perspectiva e projeção
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(-10, 10, -10, 10, -10, 10);  // Define uma projeção ortogonal
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    private void loop() {
        while (!GLFW.glfwWindowShouldClose(window)) {
            // Limpa a tela
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GL11.glLoadIdentity();

            // Aplica uma rotação para ver os três eixos e o cubo
            GL11.glRotatef(45, 1.0f, 0.0f, 0.0f);  // Rotaciona a visão para baixo
            GL11.glRotatef(45, 0.0f, 1.0f, 0.0f);  // Rotaciona a visão para o lado

            // Desenha os eixos X, Y e Z
            drawAxes();

            // Desenha o cubo com os vértices fornecidos
            drawCube(cubeVertices);

            // Atualiza a janela
            GLFW.glfwSwapBuffers(window);

            // Processa eventos
            GLFW.glfwPollEvents();
        }
    }

    public void drawAxes() {
        GL11.glBegin(GL11.GL_LINES);
        
        // Eixo X (Vermelho)
        GL11.glColor3f(1.0f, 0.0f, 0.0f);
        GL11.glVertex3f(-10.0f, 0.0f, 0.0f);
        GL11.glVertex3f(10.0f, 0.0f, 0.0f);

        // Eixo Y (Verde)
        GL11.glColor3f(0.0f, 1.0f, 0.0f);
        GL11.glVertex3f(0.0f, -10.0f, 0.0f);
        GL11.glVertex3f(0.0f, 10.0f, 0.0f);

        // Eixo Z (Azul)
        GL11.glColor3f(0.0f, 0.0f, 1.0f);
        GL11.glVertex3f(0.0f, 0.0f, -10.0f);
        GL11.glVertex3f(0.0f, 0.0f, 10.0f);

        GL11.glEnd();
    }

    public void drawCube(Point3D[] vertices) {
        if (vertices.length != 8) {
            throw new IllegalArgumentException("O array de vértices deve conter exatamente 8 pontos para formar um cubo.");
        }

        GL11.glColor3f(1.0f, 1.0f, 1.0f);  // Cor do cubo (branco)
        GL11.glBegin(GL11.GL_LINES);

        // Frente
        desenhaLinhaNo3d(vertices[0], vertices[1]);
        desenhaLinhaNo3d(vertices[1], vertices[2]);
        desenhaLinhaNo3d(vertices[2], vertices[3]);
        desenhaLinhaNo3d(vertices[3], vertices[0]);

        // Traseira
        desenhaLinhaNo3d(vertices[4], vertices[5]);
        desenhaLinhaNo3d(vertices[5], vertices[6]);
        desenhaLinhaNo3d(vertices[6], vertices[7]);
        desenhaLinhaNo3d(vertices[7], vertices[4]);

        // Conexões entre frente e traseira
        desenhaLinhaNo3d(vertices[0], vertices[4]);
        desenhaLinhaNo3d(vertices[1], vertices[5]);
        desenhaLinhaNo3d(vertices[2], vertices[6]);
        desenhaLinhaNo3d(vertices[3], vertices[7]);

        GL11.glEnd();
    }

    private void desenhaLinhaNo3d(Point3D start, Point3D end) {
        // Removeu o arredondamento, agora usa as coordenadas originais
        GL11.glVertex3f((float) start.getX(), (float) start.getY(), (float) start.getZ());
        GL11.glVertex3f((float) end.getX(), (float) end.getY(), (float) end.getZ());
    }

    public long getWindow() {
        return window;
    }
}

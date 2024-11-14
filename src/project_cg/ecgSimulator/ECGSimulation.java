package project_cg.ecgSimulator;

import project_cg.geometry.points.Point2D;
import project_cg.primitives.MidpointLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class ECGSimulation extends JPanel implements ActionListener {

    private List<Integer> ecgData = new ArrayList<>(); // Lista para armazenar valores do ECG
    private int maxDataPoints = 300;
    private Timer timer;
    private int time = 0;
    private MidpointLine midpointLine; // Instância de MidpointLine para desenhar as linhas
    private BufferedImage image; // Imagem onde os pixels serão desenhados

    public ECGSimulation() {
        midpointLine = new MidpointLine();
        timer = new Timer(15, this); // Intervalo de atualização da animação
        timer.start();

        // Inicializa a imagem com o mesmo tamanho do painel
        image = new BufferedImage(800, 400, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Limpa a imagem redefinindo cada pixel com a cor de fundo
        int backgroundColor = new Color(0, 50, 0).getRGB();
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                image.setRGB(x, y, backgroundColor);
            }
        }

        // Define a cor para a linha do ECG (branco)
        int lineColor = Color.WHITE.getRGB();

        // Desenha a linha do ECG usando MidpointLine
        int previousX = 0;
        int previousY = getHeight() / 2;
        List<Point2D> pointsToDraw = new ArrayList<>(); // Armazena pontos calculados pelo MidpointLine

        midpointLine.setCallback(pointsToDraw::add); // Callback para adicionar pontos ao 'pointsToDraw'

        for (int i = 0; i < ecgData.size(); i++) {
            int x = i * (getWidth() / maxDataPoints);
            int y = getHeight() / 2 - ecgData.get(i);

            Point2D start = new Point2D(previousX, previousY);
            Point2D end = new Point2D(x, y);

            // Desenha a linha do ECG entre 'start' e 'end' usando MidpointLine
            pointsToDraw.clear(); // Limpa os pontos antes de cada linha
            midpointLine.desenhaLinha(start, end);

            // Renderiza cada ponto gerado pelo MidpointLine usando setPixel
            for (Point2D point : pointsToDraw) {
                setPixel(point, lineColor);
            }

            previousX = x;
            previousY = y;
        }

        // Desenha a imagem na tela
        g.drawImage(image, 0, 0, null);
    }

    public void setPixel(Point2D point, int rgb) {
        int screenX = (int) (point.getX());
        int screenY = (int) (getHeight() - point.getY());

        if (screenX >= 0 && screenX < image.getWidth() && screenY >= 0 && screenY < image.getHeight()) {
            image.setRGB(screenX, screenY, rgb);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Função para simular o padrão de um batimento cardíaco
        int ecgValue;
        if (time % 100 < 15) {
            ecgValue = (int) (100 * Math.sin(0.2 * time)); // Pico alto do batimento
        } else if (time % 100 < 30) {
            ecgValue = (int) (20 * Math.sin(0.4 * time)); // Oscilação após o pico
        } else {
            ecgValue = 0; // Linha reta até o próximo pico
        }

        // Adiciona o novo valor do ECG ao final da lista
        ecgData.add(ecgValue);

        // Remove o ponto mais antigo para manter o gráfico em movimento
        if (ecgData.size() > maxDataPoints) {
            ecgData.remove(0);
        }

        time++;
        repaint(); // Atualiza a tela para o próximo quadro
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ECG Simulation");
        ECGSimulation ecgSimulation = new ECGSimulation();
        frame.add(ecgSimulation);
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

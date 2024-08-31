import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

class DDALineDrawing extends JPanel {
    private BufferedImage image;

    public DDALineDrawing(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        drawLine(10, 10, 200, 100);
    }

    private void drawLine(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));
        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            setPixel(Math.round(x), Math.round(y), Color.BLUE.getRGB());
            x += xIncrement;
            y += yIncrement;
        }
    }

    private void setPixel(int x, int y, int rgb) {
        if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
            image.setRGB(x, y, rgb);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Circle Drawing Explicit");
        CircleDrawingExplicit panel = new CircleDrawingExplicit(800, 600);
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class CircleDrawing_2 extends JPanel {
    private BufferedImage image;

    public CircleDrawing_2(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        drawCircle(400, 300, 100); // Exemplo: círculo com centro (400, 300) e raio 100
    }

    private void drawCircle(int centerX, int centerY, int radius) {
        // Desenha a circunferência usando o método trigonométrico
        int diameter = radius * 2;
        for (int angle = 0; angle < 360; angle++) {
            double radians = Math.toRadians(angle);
            int x = (int) (centerX + radius * Math.cos(radians));
            int y = (int) (centerY + radius * Math.sin(radians));
            setPixel(x, y, Color.RED.getRGB());
        }
    }

    private void setPixel(int x, int y, int rgb) {
        if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
            image.setRGB(x, y, rgb);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

}

class CircleDrawingExplicit extends JPanel {
    private BufferedImage image;

    public CircleDrawingExplicit(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        drawCircle(400, 300, 100); // Exemplo: círculo com centro (400, 300) e raio 100
    }

    private void drawCircle(int centerX, int centerY, int radius) {
        int rSquared = radius * radius;
        for (int x = centerX - radius; x <= centerX + radius; x++) {
            int dx = x - centerX;
            double dxSquared = dx * dx;
            double ySquared = rSquared - dxSquared;

            if (ySquared >= 0) {
                int y1 = (int) (centerY + Math.sqrt(ySquared));
                int y2 = (int) (centerY - Math.sqrt(ySquared));
                setPixel(x, y1, Color.RED.getRGB());
                setPixel(x, y2, Color.RED.getRGB());
            }
        }
    }

    private void setPixel(int x, int y, int rgb) {
        if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
            image.setRGB(x, y, rgb);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }


}

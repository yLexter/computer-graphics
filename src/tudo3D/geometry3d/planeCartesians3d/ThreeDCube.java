package tudo3D.geometry3d.planeCartesians3d;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;


// PROVAVELMENTE APAGAR TUDO (NN ESTÁ SENDO UTILIZADO)

public class ThreeDCube extends JPanel{

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int CUBE_SIZE = 200;
    
    public ThreeDCube(){
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    }
    
    @Override
    protected void paintComponent(Graphics g){
        
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // Set the stroke to a thicker line
        g2d.setStroke(new BasicStroke(5));
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        // Calculate the position to center the cube
        int x = (FRAME_WIDTH - CUBE_SIZE) / 2;
        int y = (FRAME_HEIGHT - CUBE_SIZE) / 2;
        // Draw front face
        drawRectangularFace(g2d, x, y, CUBE_SIZE, CUBE_SIZE);
        // Draw back face
        drawRectangularFace(g2d, x+CUBE_SIZE/2, y-CUBE_SIZE/2, CUBE_SIZE, CUBE_SIZE);
        // Connect front and back faces
        connectFaces(g2d, x, y, x+CUBE_SIZE/2, y-CUBE_SIZE/2, CUBE_SIZE, CUBE_SIZE);
        
    }
    
    
    // Method to draw a lines
    private void drawLine(Graphics2D g2d, int x1, int y1, int x2, int y2){
        
        g2d.setColor(Color.ORANGE);
        g2d.drawLine(x1, y1, x2, y2);
        
    }
    
    
    // Method to draw a rectangular face
    private void drawRectangularFace(Graphics2D g2d, int x, int y, int width, int height)
    {
        drawLine(g2d, x, y, x+width, y);
        drawLine(g2d, x+width, y, x+width, y+height);
        drawLine(g2d, x+width, y+height, x, y+height);
        drawLine(g2d, x, y+height, x, y);
    }
    
    
    // Method to connect the front and back faces
        private void connectFaces(Graphics2D g2d, int x1, int y1, int x2, int y2, int width, int height)
    {
        drawLine(g2d, x1, y1, x2, y2);
        drawLine(g2d, x1+width, y1, x2+width, y2);
        drawLine(g2d, x1+width, y1+height, x2+width, y2+height);
        drawLine(g2d, x1, y1+height, x2, y2+height);
    }
    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("3 D Cube");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ThreeDCube());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}

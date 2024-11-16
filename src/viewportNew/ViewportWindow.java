package viewportNew;

import geometry.planeCartesians.bases.BaseCartesianPlane2D;

import javax.swing.*;
import java.awt.*;

public class ViewportWindow extends JFrame {

    private Viewport2D viewport;
    private JPanel viewportPanel;

    public ViewportWindow(int width, int height) {
        setTitle("Viewport Window");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(width, height);
        setLayout(new BorderLayout());

        // Inicializa a viewport
        viewport = new Viewport2D(0, 0, width, height);

        // Configura o painel de exibição da viewport
        viewportPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                viewport.draw(g);
            }
        };
        viewportPanel.setPreferredSize(new Dimension(width, height));
        add(viewportPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void updateViewport(BaseCartesianPlane2D plane, int worldXMin, int worldYMin, int worldXMax, int worldYMax) {
        viewport.renderFromCartesian(plane, worldXMin, worldYMin, worldXMax, worldYMax);
        viewportPanel.repaint();
    }
}

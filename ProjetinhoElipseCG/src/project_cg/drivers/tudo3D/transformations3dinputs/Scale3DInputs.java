package project_cg.drivers.tudo3D.transformations3dinputs;

import project_cg.drivers.tudo3D.geometry3d.planeCartesians3d.CartesianPlane3D;
import project_cg.drivers.tudo3D.geometry3d.points3d.Point3D;
import project_cg.drivers.tudo3D.transformations3d.Scale3D;
import view.utils.ShapePanel;

import javax.swing.*;

public class Scale3DInputs extends ShapePanel {
    private JTextField scaleXInput;
    private JTextField scaleYInput;
    private JTextField scaleZInput;
    private CartesianPlane3D plane3D;

    public Scale3DInputs(CartesianPlane3D plane3D) {
        this.plane3D = plane3D; // Referência ao plano cartesiano 3D
    }

    @Override
    protected void initializeInputs() {
        // Campos de entrada para os fatores de escala em X, Y e Z
        scaleXInput = new JTextField(10);
        scaleYInput = new JTextField(10);
        scaleZInput = new JTextField(10);

        addInputField("Fator de escala em X:", scaleXInput);
        addInputField("Fator de escala em Y:", scaleYInput);
        addInputField("Fator de escala em Z:", scaleZInput);
    }

    @Override
    protected void onCalculate() {
        try {
            // Obtém os fatores de escala fornecidos pelo usuário
            double sx = Double.parseDouble(scaleXInput.getText());
            double sy = Double.parseDouble(scaleYInput.getText());
            double sz = Double.parseDouble(scaleZInput.getText());

            // Obtém os vértices do cubo
            Point3D[] vertices = plane3D.getCubeVertices();

            if (vertices != null && vertices.length == 8) {
                // Aplica a escala a cada vértice no array
                for (int i = 0; i < vertices.length; i++) {
                    vertices[i] = Scale3D.scalePoint(vertices[i], sx, sy, sz);
                }

                // Atualiza os vértices no plano cartesiano 3D
                plane3D.setCubeVertices(vertices);

                // Reinicia a renderização do plano para refletir as mudanças
                new Thread(() -> plane3D.update(vertices)).start();
            } else {
                JOptionPane.showMessageDialog(this, "Vértices inválidos ou ausentes.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Fator de escala inválido. Insira valores numéricos válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

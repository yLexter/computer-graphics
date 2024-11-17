package view.mainScreen.mainScreenPanels;

import utils.Constants;

import javax.swing.*;
import java.awt.*;

public class LogsOperationPanel extends JPanel {

    public LogsOperationPanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Outputs");

        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(10, 100));

        add(spacer, BorderLayout.NORTH);
        add(label, BorderLayout.NORTH);

        setBackground(Constants.BACKGROUND_LOGS_OPERATION);
    }

}

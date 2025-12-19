package homepage;

import javax.swing.*;
import java.awt.*;

public class StatusTimePanel extends JPanel {

    private JLabel timeLabel;

    public StatusTimePanel(String initialTime) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 8));
        setOpaque(false);

        timeLabel = new JLabel(initialTime);
        timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        timeLabel.setForeground(Color.WHITE);

        add(timeLabel);
    }

    public void updateTime(String newTime) {
        timeLabel.setText(newTime);
    }
}


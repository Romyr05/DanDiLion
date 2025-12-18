package homepage;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel {
    private StatusTimePanel timePanel;
    private Timer clockTimer;

    public StatusBar() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 35));
        setBackground(new Color(130, 0, 220));
        setOpaque(true);

        // Time display on left
        timePanel = new StatusTimePanel(TimeDate.getFormattedTime());
        add(timePanel, BorderLayout.WEST);

        // Icons on right
        add(createIconPanel(), BorderLayout.EAST);

        startClockTimer();
    }

    private JPanel createIconPanel() {
        JPanel icons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 10));
        icons.setOpaque(false);
        icons.add(new StatusIcons.WifiIcon());
        icons.add(new StatusIcons.BatteryIcon());
        return icons;
    }

    private void startClockTimer() {
        clockTimer = new Timer(1000, e -> {
            String now = TimeDate.getFormattedTime();
            timePanel.updateTime(now);
        });
        clockTimer.start();
    }
}
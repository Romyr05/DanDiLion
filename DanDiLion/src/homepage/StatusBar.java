package homepage;

import javax.swing.*;

import homepage.newGUI.BatteryIcon;
import homepage.newGUI.WifiIcon;

import java.awt.*;

public class StatusBar extends JPanel {

    private StatusTimePanel timePanel;
    private Timer clockTimer;

    public StatusBar() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 35));
        setBackground(new Color(130, 0, 220));
        setOpaque(true);

        timePanel = new StatusTimePanel(TimeDate.getFormattedTime());
        add(timePanel, BorderLayout.WEST);

        JPanel icons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 10));
        icons.setOpaque(false);
        icons.add(new WifiIcon());
        icons.add(new BatteryIcon());
        add(icons, BorderLayout.EAST);

        startClockTimer();
    }

    private void startClockTimer() {
        clockTimer = new Timer(1000, e -> {
            String now = TimeDate.getFormattedTime();
            timePanel.updateTime(now);
        });
        clockTimer.start();
    }
}

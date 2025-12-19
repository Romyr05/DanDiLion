package homepage;

import javax.swing.*;
import java.awt.*;

//Wifi panel bar on the above (with icons -> time, batt and wifi)

public class StatusBar extends JPanel {
    private StatusTimePanel timePanel;
    private Timer clockTimer;

    public StatusBar() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 45));
        setOpaque(true); // Make it visible
        setBackground(new Color(0, 0, 0, 180)); // Semi-transparent black background

        // Time display on left
        timePanel = new StatusTimePanel(TimeDate.getFormattedTime());
        add(timePanel, BorderLayout.WEST);

        // Icons on right
        add(createIconPanel(), BorderLayout.EAST);

        startClockTimer();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw semi-transparent dark background with slight blur effect
        g2d.setColor(new Color(0, 0, 0, 200));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        // Add subtle bottom border
        g2d.setColor(new Color(255, 255, 255, 30));
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
        
        g2d.dispose();
    }

    private JPanel createIconPanel() {
        JPanel icons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 10));
        icons.setOpaque(false);
        icons.add(new StatusIcons.WifiIcon());
        icons.add(new StatusIcons.BatteryIcon());
        return icons;
    }

    //time
    private void startClockTimer() {
        clockTimer = new Timer(1000, e -> {
            String now = TimeDate.getFormattedTime();
            timePanel.updateTime(now);
        });
        clockTimer.start();
    }
}
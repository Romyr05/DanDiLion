package homepage.newGUI;

import javax.swing.*;
import java.awt.*;

public class BatteryIcon extends JPanel {

    public BatteryIcon() {
        setPreferredSize(new Dimension(24, 16));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);

        g2d.setStroke(new BasicStroke(1.5f));
        g2d.drawRoundRect(1, 3, 18, 10, 2, 2);
        g2d.fillRect(20, 6, 2, 4);

        // 80% battery fill
        g2d.fillRect(3, 5, 13, 6);
    }
}
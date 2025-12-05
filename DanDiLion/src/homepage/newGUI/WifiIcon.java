package homepage.newGUI;

import javax.swing.*;
import java.awt.*;

public class WifiIcon extends JPanel {

    public WifiIcon() {
        setPreferredSize(new Dimension(18, 16));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);

        int[] heights = {4, 7, 10, 13};

        for (int i = 0; i < 4; i++) {
            g2d.fillRect(i * 4, 13 - heights[i], 3, heights[i]);
        }
    }
}